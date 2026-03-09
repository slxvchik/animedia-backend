package dev.animedia.languageservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.grpc.server.GlobalServerInterceptor;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import dev.animedia.languageservice.exception.AppExceptionMessageService;
import dev.animedia.languageservice.mapper.AppExceptionStatusMapper;
import io.grpc.ServerInterceptor;

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
		AppExceptionMessageService appExceptionMessageService,
		AppExceptionStatusMapper appExceptionStatusMapper
	) {
		return new GlobalExceptionInterceptor(appExceptionMessageService, appExceptionStatusMapper);
	}

	@Bean
	public LocalValidatorFactoryBean validator() {
		return new LocalValidatorFactoryBean();
	}
}
