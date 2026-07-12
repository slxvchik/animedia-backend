package dev.animedia.contentservice.status.infrastracture.config;

import dev.animedia.contentservice.status.application.mapper.StatusApplicationMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StatusMapperConfig {
	@Bean
	public StatusApplicationMapper statusApplicationMapper() {
		return new StatusApplicationMapper();
	}
}
