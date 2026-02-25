package dev.animedia.bookservice.app.config;

import dev.animedia.contentservice.app.exception.AppExceptionMessageService;
import io.grpc.ServerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.grpc.server.GlobalServerInterceptor;

@Configuration
public class GrpcServerConfiguration {

    @Bean
    @Order(100)
    @GlobalServerInterceptor
    ServerInterceptor getLanguageInterceptor() {
        return new LanguageInterceptor();
    }

    @Bean
    @Order(101)
    @GlobalServerInterceptor
    ServerInterceptor getGlobalExceptionInterceptor(AppExceptionMessageService appExceptionMessageService) {
        return new GlobalExceptionInterceptor(appExceptionMessageService);
    }
}
