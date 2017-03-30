package org.springboot.guacamole.websocket;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

public class GuacamoleHandshakeInterceptor implements HandshakeInterceptor {

	/**
	 * Logger for this class.
	 */
	private final Logger logger = LoggerFactory.getLogger(GuacamoleHandshakeInterceptor.class);

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		// Always return true
		return true;
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {
		logger.debug("Adding 'Sec-WebSocket-Protocol' header");
		response.getHeaders().add("Sec-WebSocket-Protocol", "guacamole");

	}

}
