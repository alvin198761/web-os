package org.springboot.guacamole.websocket;

import org.apache.guacamole.GuacamoleClientException;
import org.apache.guacamole.GuacamoleConnectionClosedException;
import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.io.GuacamoleReader;
import org.apache.guacamole.io.GuacamoleWriter;
import org.apache.guacamole.net.GuacamoleTunnel;
import org.apache.guacamole.protocol.GuacamoleStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public abstract class GuacamoleWebSocketTunnelHandler implements WebSocketHandler {

	/**
	 * The default, minimum buffer size for instructions.
	 */
	private static final int BUFFER_SIZE = 8192;

	/**
	 * Logger for this class.
	 */
	private final Logger logger = LoggerFactory.getLogger(GuacamoleWebSocketTunnelHandler.class);

	/**
	 * The underlying GuacamoleTunnel. WebSocket reads/writes will be handled as
	 * reads/writes to this tunnel.
	 */
	private GuacamoleTunnel tunnel;

	/**
	 * Returns a new tunnel for the given session. How this tunnel is created or
	 * retrieved is implementation-dependent.
	 *
	 * @param session
	 *            The session associated with the active WebSocket connection.
	 * @return A connected tunnel, or null if no such tunnel exists.
	 * @throws GuacamoleException
	 *             If an error occurs while retrieving the tunnel, or if access
	 *             to the tunnel is denied.
	 */
	abstract GuacamoleTunnel createTunnel(WebSocketSession session) throws GuacamoleException;

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.debug("afterConnectionEstablished");
		try {

			// Get tunnel
			tunnel = createTunnel(session);
			if (tunnel == null) {
				String message = Integer.toString(GuacamoleStatus.RESOURCE_NOT_FOUND.getGuacamoleStatusCode());
				afterConnectionClosed(session,
						new CloseStatus(GuacamoleStatus.RESOURCE_NOT_FOUND.getWebSocketCode(), message));
				return;
			}

		} catch (GuacamoleException e) {
			logger.error("Creation of WebSocket tunnel to guacd failed: {}", e.getMessage());
			logger.debug("Error connecting WebSocket tunnel.", e);
			String message = Integer.toString(e.getStatus().getGuacamoleStatusCode());
			afterConnectionClosed(session, new CloseStatus(e.getStatus().getWebSocketCode(), message));
			return;
		}
		Thread readThread = new Thread() {

			@Override
			public void run() {

				StringBuilder buffer = new StringBuilder(BUFFER_SIZE);
				GuacamoleReader reader = tunnel.acquireReader();
				char[] readMessage;

				try {

					try {

						// Attempt to read
						while ((readMessage = reader.read()) != null) {

							// Buffer message
							buffer.append(readMessage);

							// Flush if we expect to wait or buffer is getting
							// full
							if (!reader.available() || buffer.length() >= BUFFER_SIZE) {
								session.sendMessage(new TextMessage(buffer));
								buffer.setLength(0);
							}

						}

						// No more data
						String message = Integer.toString(GuacamoleStatus.SUCCESS.getGuacamoleStatusCode());
						afterConnectionClosed(session,
								new CloseStatus(GuacamoleStatus.SUCCESS.getWebSocketCode(), message));
					}

					// Catch any thrown guacamole exception and attempt
					// to pass within the WebSocket connection, logging
					// each error appropriately.
					catch (GuacamoleClientException e) {
						logger.info("WebSocket connection terminated: {}", e.getMessage());
						logger.debug("WebSocket connection terminated due to client error.", e);
						String message = Integer.toString(e.getStatus().getGuacamoleStatusCode());
						afterConnectionClosed(session, new CloseStatus(e.getStatus().getWebSocketCode(), message));
					} catch (GuacamoleConnectionClosedException e) {
						logger.debug("Connection to guacd closed.", e);
						String message = Integer.toString(GuacamoleStatus.SUCCESS.getGuacamoleStatusCode());
						afterConnectionClosed(session,
								new CloseStatus(GuacamoleStatus.SUCCESS.getWebSocketCode(), message));
					} catch (GuacamoleException e) {
						logger.error("Connection to guacd terminated abnormally: {}", e.getMessage());
						logger.debug("Internal error during connection to guacd.", e);
						String message = Integer.toString(e.getStatus().getGuacamoleStatusCode());
						afterConnectionClosed(session, new CloseStatus(e.getStatus().getWebSocketCode(), message));
					}

				} catch (Exception e) {
					logger.debug("I/O error prevents further reads.", e);
				}

			}

		};

		readThread.start();
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		logger.debug("handleMessage");
		// Ignore inbound messages if there is no associated tunnel
		if (tunnel == null)
			return;

		GuacamoleWriter writer = tunnel.acquireWriter();

		try {
			// Write received message
			writer.write(message.getPayload().toString().toCharArray());
		} catch (GuacamoleConnectionClosedException e) {
			logger.debug("Connection to guacd closed.", e);
		} catch (GuacamoleException e) {
			logger.debug("WebSocket tunnel write failed.", e);
		}

		tunnel.releaseWriter();
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		logger.debug("handleTransportError");
	}

	/**
	 * Sends the given status on the given WebSocket connection and closes the
	 * connection.
	 *
	 * @param session
	 *            The outbound WebSocket connection to close.
	 * @param closeStatus
	 *            The status to send.
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		logger.debug("afterConnectionClosed");
		session.close(closeStatus);
		try {
			if (tunnel != null)
				tunnel.close();
		} catch (GuacamoleException e) {
			logger.debug("Unable to close WebSocket tunnel.", e);
		}
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

}
