//package org.alvin.opsdev.desktop.config.servlet;
//
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServletRequest;
//
//import org.alvin.opsdev.desktop.system.domain.Protocol;
//import org.alvin.opsdev.desktop.system.service.SettingService;
//import org.apache.guacamole.GuacamoleException;
//import org.apache.guacamole.net.GuacamoleSocket;
//import org.apache.guacamole.net.GuacamoleTunnel;
//import org.apache.guacamole.net.InetGuacamoleSocket;
//import org.apache.guacamole.net.SimpleGuacamoleTunnel;
//import org.apache.guacamole.protocol.ConfiguredGuacamoleSocket;
//import org.apache.guacamole.protocol.GuacamoleConfiguration;
//import org.apache.guacamole.servlet.GuacamoleHTTPTunnelServlet;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//
///**
// * Simple tunnel example with hard-coded configuration parameters.
// *
// * @author Michael Jumper
// */
////@WebServlet(urlPatterns = "/tunnel.do")
//public class DummyGuacamoleTunnelServlet extends GuacamoleHTTPTunnelServlet {
//
//    @Value("${guacamole.server.ip}")
//    private String guacamoleServer;
//    @Value("${guacamole.server.port}")
//    private int guacamoleServerPort;
//
//    @Autowired
//    private SettingService settingService;
//
//    @Override
//    protected GuacamoleTunnel doConnect(HttpServletRequest request)
//            throws GuacamoleException {
//        String id = request.getParameter("id");
//        Protocol protocol = this.settingService.getRDP(Long.parseLong(id));
//        GuacamoleConfiguration config = new GuacamoleConfiguration();
//        config.setProtocol(request.getParameter("type"));
//        config.setParameter("hostname", protocol.getIp());
//        config.setParameter("port", protocol.getPort().toString());
//        config.setParameter("username", protocol.getUser());
//        config.setParameter("password", protocol.getPassword());
//        config.setParameter("width", request.getParameter("width"));
//        config.setParameter("height", request.getParameter("height"));
//        GuacamoleSocket socket = new ConfiguredGuacamoleSocket(
//                new InetGuacamoleSocket(this.guacamoleServer, this.guacamoleServerPort), config);
//        return new SimpleGuacamoleTunnel(socket);
//    }
//
//
//
//
//}