package org.alvin.opsdev.desktop.config;

import org.springboot.guacamole.http.TutorialGuacamoleTunnelServlet;
import org.springboot.guacamole.websocket.GuacamoleHandshakeInterceptor;
import org.springboot.guacamole.websocket.TutorialGuacamoleWebSocketTunnelHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tangzhichao on 2017/3/6.
 */
@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    @Autowired
    private TutorialGuacamoleTunnelServlet tutorialGuacamoleTunnelServlet;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(guacamoleWebSocketTunnel(), "/websocket-tunnel").addInterceptors(myIntecerptor());
    }

    private HandshakeInterceptor myIntecerptor() {
        // TODO Auto-generated method stub
        return new GuacamoleHandshakeInterceptor();
    }

    @Bean
    public WebSocketHandler guacamoleWebSocketTunnel() {
        return new TutorialGuacamoleWebSocketTunnelHandler();
    }

//
//    @Bean
//    public ServletRegistrationBean servletRegistrationBean() {
//        ServletRegistrationBean bean = new ServletRegistrationBean(tutorialGuacamoleTunnelServlet, "/tunnel");
//        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        return bean;
//    }

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain filterChain) throws ServletException, IOException {
                if ("POST".equals(request.getMethod()) && request.getRequestURI().startsWith("/tunnel")) {
                    filterChain.doFilter(request, response);
                } else {
                    super.doFilterInternal(request, response, filterChain);
                }
            }
        };
    }

}
