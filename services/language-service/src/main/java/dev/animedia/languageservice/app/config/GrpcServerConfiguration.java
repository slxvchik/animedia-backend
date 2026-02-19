package dev.animedia.languageservice.app.config;

import io.grpc.ServerInterceptor;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

	@Bean
	ServerInterceptor languageInterceptor() {

	}
}
