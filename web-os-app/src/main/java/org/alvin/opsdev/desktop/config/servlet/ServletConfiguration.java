//package org.alvin.opsdev.desktop.config.servlet;
//
//import org.alvin.opsdev.desktop.system.controller.DummyGuacamoleTunnelServlet;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.servlet.ServletComponentScan;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.boot.web.support.SpringBootServletInitializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by tangzhichao on 2017/3/6.
// */
//@Configuration
//@ServletComponentScan
//public class ServletConfiguration extends SpringBootServletInitializer {
//
//    @Autowired
//    private DummyGuacamoleTunnelServlet dummyGuacamoleTunnelServlet;
//
//    @Bean
//    public ServletRegistrationBean dispatcherServletRegistration() {
//        ServletRegistrationBean registration = new ServletRegistrationBean(dummyGuacamoleTunnelServlet, "/tunnel/*");// ServletName默认值为首字母小写，即demoServlet
//        Map<String, String> params = new HashMap<>();
//        params.put("org.atmosphere.servlet", "org.springframework.web.servlet.DispatcherServlet");
//        params.put("contextClass", "org.springframework.web.context.support.AnnotationConfigWebApplicationContext");
//        params.put("contextConfigLocation", "net.org.selector.animals.config.ComponentConfiguration");
//        registration.setInitParameters(params);
//        return registration;
//    }
//
////
////    @Override
////    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
////        return application.sources(WebSocketConfiguration.class);
////    }
//}
