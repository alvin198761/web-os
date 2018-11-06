package org.alvin.opsdev.desktop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class AppConfiguration {

    @Bean
    @Autowired
    public Boolean isDev(@Value("${spring.profiles.active}") String model) {
        return "dev".equalsIgnoreCase(model);
    }

//    @Bean
//    @Autowired
//    public UndertowServletWebServerFactory embeddedServletContainerFactory(@Value("${http.port}") Integer port, @Value("${server.port}") Integer httpsPort) {
//        UndertowServletWebServerFactory factory = new UndertowServletWebServerFactory();
//        factory.addBuilderCustomizers(builder ->
//                builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true)
//                .setServerOption(UndertowOptions.HTTP2_SETTINGS_ENABLE_PUSH, true));
//        factory.addBuilderCustomizers((Undertow.Builder builder) -> builder.addHttpListener(port, "0.0.0.0"));
//        factory.addDeploymentInfoCustomizers(deploymentInfo -> {
//            // 开启HTTP自动跳转至HTTPS
//            deploymentInfo.addSecurityConstraint(new SecurityConstraint()
//                    .addWebResourceCollection(new WebResourceCollection().addUrlPattern("/*"))
//                    .setTransportGuaranteeType(TransportGuaranteeType.CONFIDENTIAL)
//                    .setEmptyRoleSemantic(SecurityInfo.EmptyRoleSemantic.PERMIT))
//                    .setConfidentialPortManager(exchange -> httpsPort);
//        });
//        return factory;
//    }

}
