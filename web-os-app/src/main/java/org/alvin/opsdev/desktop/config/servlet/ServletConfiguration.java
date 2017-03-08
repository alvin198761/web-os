package org.alvin.opsdev.desktop.config.servlet;

import org.alvin.opsdev.desktop.config.websocket.WebSocketConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tangzhichao on 2017/3/6.
 */
@Configuration
@ServletComponentScan
public class ServletConfiguration extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebSocketConfiguration.class);
    }
}
