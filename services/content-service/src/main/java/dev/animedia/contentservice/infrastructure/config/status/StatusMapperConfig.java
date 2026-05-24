package dev.animedia.contentservice.infrastructure.config.status;

import dev.animedia.contentservice.application.status.mapper.StatusApplicationMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StatusMapperConfig {
	@Bean
	public StatusApplicationMapper statusApplicationMapper() {
		return new StatusApplicationMapper();
	}
}
