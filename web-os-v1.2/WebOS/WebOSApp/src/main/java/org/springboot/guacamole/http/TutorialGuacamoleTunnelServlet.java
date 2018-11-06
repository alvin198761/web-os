package org.springboot.guacamole.http;


import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.net.GuacamoleSocket;
import org.apache.guacamole.net.GuacamoleTunnel;
import org.apache.guacamole.net.InetGuacamoleSocket;
import org.apache.guacamole.net.SimpleGuacamoleTunnel;
import org.apache.guacamole.protocol.ConfiguredGuacamoleSocket;
import org.apache.guacamole.protocol.GuacamoleConfiguration;
import org.apache.guacamole.servlet.GuacamoleHTTPTunnelServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class TutorialGuacamoleTunnelServlet extends GuacamoleHTTPTunnelServlet {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Value("${guacamole.server.ip}")
	private String guacamoleServer;
	@Value("${guacamole.server.port}")
	private int guacamoleServerPort;

	@Override
	protected GuacamoleTunnel doConnect(HttpServletRequest request) throws GuacamoleException {
		// Create our configuration
		GuacamoleConfiguration config = new GuacamoleConfiguration();
		config.setProtocol(request.getParameter("type"));
		config.setParameter("hostname", request.getParameter("hostname"));
		config.setParameter("port", request.getParameter("port"));
		config.setParameter("user",  request.getParameter("user"));
		config.setParameter("password",  request.getParameter("password"));

		// Connect to guacd - everything is hard-coded here.
		GuacamoleSocket socket = new ConfiguredGuacamoleSocket(new InetGuacamoleSocket(guacamoleServer, guacamoleServerPort),
				config);

		// Return a new tunnel which uses the connected socket
		return new SimpleGuacamoleTunnel(socket);

	}

}