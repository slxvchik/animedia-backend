package dev.animedia.languageservice.infrastructure.config;

import dev.animedia.languageservice.application.mapper.LanguageApplicationMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LanguageMapperConfig {
	@Bean
	public LanguageApplicationMapper languageApplicationMapper() {
		return new LanguageApplicationMapper();
	}
}
