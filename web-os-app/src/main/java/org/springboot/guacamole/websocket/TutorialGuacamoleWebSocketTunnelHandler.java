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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Properties;

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
		System.out.println(session.getUri());
		Properties properties = new Properties();
        try {
            properties.load(new ByteArrayInputStream(session.getUri().getQuery().replace('&','\n').getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(properties);


        logger.debug("createTunnel");
		// Create our configuration
		GuacamoleConfiguration config = new GuacamoleConfiguration();
//        properties.getProperty("id");
//        properties.getProperty("type")
		config.setProtocol("rdp");
		config.setParameter("hostname", "192.168.0.103");
		config.setParameter("port", "3389");
		config.setParameter("user", "administrator");
		config.setParameter("password", "11");
        config.setParameter("width",properties.getProperty("width"));
        config.setParameter("height",properties.getProperty("height"));

		// Connect to guacd - everything is hard-coded here.
		GuacamoleSocket socket = new ConfiguredGuacamoleSocket(new InetGuacamoleSocket(guacamoleServer, guacamoleServerPort),
				config);
		// Return a new tunnel which uses the connected socket
		return new SimpleGuacamoleTunnel(socket);
	}

}
