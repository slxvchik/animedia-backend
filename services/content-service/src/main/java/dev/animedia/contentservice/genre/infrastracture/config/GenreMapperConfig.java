package dev.animedia.contentservice.genre.infrastracture.config;

import dev.animedia.contentservice.genre.application.mapper.GenreApplicationMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenreMapperConfig {
	@Bean
	public GenreApplicationMapper genreApplicationMapper() {
		return new GenreApplicationMapper();
	}
}
