package dev.animedia.contentservice.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GrpcServerConfiguration {
    
    private final LanguageInterceptor languageInterceptor;

    @Autowired
    public GrpcServerConfiguration(LanguageInterceptor languageInterceptor) {
        this.languageInterceptor = languageInterceptor;
    }
}
