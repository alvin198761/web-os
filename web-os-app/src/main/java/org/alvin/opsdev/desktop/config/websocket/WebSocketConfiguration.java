package org.alvin.opsdev.desktop.config.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Created by tangzhichao on 2017/3/6.
 */
@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
      //  registry.addHandler(echoWebSocketHandler(), "/echo").withSockJS();
      //  registry.addHandler(snakeWebSocketHandler(), "/snake").withSockJS();
    }
}
