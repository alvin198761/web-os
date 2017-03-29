package org.alvin.opsdev.desktop.system.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.alvin.opsdev.desktop.system.domain.Protocol;
import org.alvin.opsdev.desktop.system.service.SettingService;
import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.net.GuacamoleSocket;
import org.apache.guacamole.net.GuacamoleTunnel;
import org.apache.guacamole.net.InetGuacamoleSocket;
import org.apache.guacamole.net.SimpleGuacamoleTunnel;
import org.apache.guacamole.protocol.ConfiguredGuacamoleSocket;
import org.apache.guacamole.protocol.GuacamoleConfiguration;
import org.apache.guacamole.servlet.GuacamoleHTTPTunnelServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Simple tunnel example with hard-coded configuration parameters.
 *
 * @author Michael Jumper
 */
@WebServlet(urlPatterns = "/tunnel")
public class DummyGuacamoleTunnelServlet extends GuacamoleHTTPTunnelServlet {

    @Value("${guacamole.server.ip}")
    private String guacamoleServer;
    @Value("${guacamole.server.port}")
    private int guacamoleServerPort;

    @Autowired
    private SettingService settingService;

    @Override
    protected GuacamoleTunnel doConnect(HttpServletRequest request) throws GuacamoleException {

        // guacd connection information
        String hostname = "192.168.0.104";
        int port = 4822;

        // VNC connection information
        GuacamoleConfiguration config = new GuacamoleConfiguration();
        config.setProtocol("vnc");
        config.setParameter("hostname", "192.168.0.104");
        config.setParameter("port", "5900");
        // config.setParameter("password", "potato");

        // Connect to guacd, proxying a connection to the VNC server above
        GuacamoleSocket socket = new ConfiguredGuacamoleSocket(
                new InetGuacamoleSocket(hostname, port),
                config
        );

        // Create tunnel from now-configured socket
        GuacamoleTunnel tunnel = new SimpleGuacamoleTunnel(socket);
        return tunnel;

    }


}