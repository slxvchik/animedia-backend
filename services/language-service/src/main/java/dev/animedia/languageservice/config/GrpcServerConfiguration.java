package dev.animedia.languageservice.config;

import dev.animedia.languageservice.exception.AppExceptionMessageService;
import io.grpc.ServerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.grpc.server.GlobalServerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class GrpcServerConfiguration {

	@Bean
	@Order(100)
	@GlobalServerInterceptor
	ServerInterceptor languageInterceptor() {
		return new LanguageInterceptor();
	}

	@Bean
	@Order(101)
	@GlobalServerInterceptor
	ServerInterceptor globalExceptionInterceptor(
		AppExceptionMessageService appExceptionMessageService
	) {
		return new GlobalExceptionInterceptor(appExceptionMessageService);
	}

	@Bean
	public LocalValidatorFactoryBean validator() {
		return new LocalValidatorFactoryBean();
	}
}
