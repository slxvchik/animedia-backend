package dev.animedia.contentservice.app.config;

import dev.animedia.contentservice.app.exception.AppExceptionMessageService;
import dev.animedia.contentservice.app.exception.AppExceptionStatusMapper;
import io.grpc.ServerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
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
    ServerInterceptor getGlobalExceptionInterceptor(
        AppExceptionMessageService appExceptionMessageService,
        AppExceptionStatusMapper appExceptionStatusMapper
    ) {
        return new GlobalExceptionInterceptor(appExceptionMessageService, appExceptionStatusMapper);
    }
}
