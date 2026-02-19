package dev.animedia.languageservice.app.config;

import io.grpc.ServerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.grpc.server.GlobalServerInterceptor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcServerConfiguration {
	@Bean
	@Order(100)
	@GlobalServerInterceptor
	ServerInterceptor languageInterceptor() {
		return new LanguageInterceptor();
	}
}
