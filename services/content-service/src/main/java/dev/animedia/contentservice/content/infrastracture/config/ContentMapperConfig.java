package dev.animedia.contentservice.content.infrastracture.config;

import dev.animedia.contentservice.content.application.mapper.ContentApplicationMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContentMapperConfig {
	@Bean
	public ContentApplicationMapper contentApplicationMapper() {
		return new ContentApplicationMapper();
	}
}
