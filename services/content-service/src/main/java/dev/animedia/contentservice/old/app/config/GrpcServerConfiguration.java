package dev.animedia.contentservice.old.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.grpc.server.GlobalServerInterceptor;

import dev.animedia.contentservice.old.app.exception.AppExceptionMessageService;
import dev.animedia.contentservice.old.app.exception.AppExceptionStatusMapper;
import io.grpc.ServerInterceptor;

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
    ServerInterceptor getGlobalExceptionInterceptor(
        AppExceptionMessageService appExceptionMessageService,
        AppExceptionStatusMapper appExceptionStatusMapper
    ) {
        return new GlobalExceptionInterceptor(appExceptionMessageService, appExceptionStatusMapper);
    }
}
