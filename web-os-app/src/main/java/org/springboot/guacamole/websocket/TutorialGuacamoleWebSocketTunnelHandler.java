package org.springboot.guacamole.websocket;

import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.net.GuacamoleSocket;
import org.apache.guacamole.net.GuacamoleTunnel;
import org.apache.guacamole.net.InetGuacamoleSocket;
import org.apache.guacamole.net.SimpleGuacamoleTunnel;
import org.apache.guacamole.protocol.ConfiguredGuacamoleSocket;
import org.apache.guacamole.protocol.GuacamoleConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.socket.WebSocketSession;

public class TutorialGuacamoleWebSocketTunnelHandler extends GuacamoleWebSocketTunnelHandler {

//	@Autowired
//	private TunnelRequestService tunnelRequestService;

	@Value("${guacamole.server.ip}")
    private String guacamoleServer;
    @Value("${guacamole.server.port}")
    private int guacamoleServerPort;

	/**
	 * Logger for this class.
	 */
	private final Logger logger = LoggerFactory.getLogger(TutorialGuacamoleWebSocketTunnelHandler.class);

	@Override
	GuacamoleTunnel createTunnel(WebSocketSession session) throws GuacamoleException {
		logger.debug("createTunnel");
		// Create our configuration
		GuacamoleConfiguration config = new GuacamoleConfiguration();
		config.setProtocol("vnc");
		config.setParameter("hostname", "192.168.0.104");
		config.setParameter("port", "5900");
//		config.setParameter("user", "root");
//		config.setParameter("password", "111111");

		// Connect to guacd - everything is hard-coded here.
		GuacamoleSocket socket = new ConfiguredGuacamoleSocket(new InetGuacamoleSocket(guacamoleServer, guacamoleServerPort),
				config);
		// Return a new tunnel which uses the connected socket
		return new SimpleGuacamoleTunnel(socket);
	}

}
