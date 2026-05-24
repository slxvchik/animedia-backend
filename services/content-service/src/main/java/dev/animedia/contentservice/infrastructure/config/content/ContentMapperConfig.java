package dev.animedia.contentservice.infrastructure.config.content;

import dev.animedia.contentservice.application.content.mapper.ContentApplicationMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContentMapperConfig {
	@Bean
	public ContentApplicationMapper contentApplicationMapper() {
		return new ContentApplicationMapper();
	}
}
