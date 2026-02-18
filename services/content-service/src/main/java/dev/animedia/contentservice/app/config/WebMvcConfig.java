package dev.animedia.contentservice.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import dev.animedia.contentservice.app.config.interceptor.LanguageInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    
    private final LanguageInterceptor languageInterceptor;

    @Autowired
    public WebMvcConfig(LanguageInterceptor languageInterceptor) {
        this.languageInterceptor = languageInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(languageInterceptor)
            .addPathPatterns("/api/**")
            .excludePathPatterns("/actuator/**");
    }
}
