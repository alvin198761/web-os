package org.alvin.opsdev.desktop.system.cms;

import freemarker.template.Version;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FreemarkerConfig {

    @Bean
    public freemarker.template.Configuration freeMarkerConfig() {
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(new Version("2.3.23"));
        configuration.setClassForTemplateLoading(FreemarkerConfig.class, "/cms");
        configuration.setDefaultEncoding("UTF-8");
        return configuration;
    }


}
