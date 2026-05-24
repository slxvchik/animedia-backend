package dev.animedia.contentservice.infrastructure.config.genre;

import dev.animedia.contentservice.application.genre.mapper.GenreApplicationMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenreMapperConfig {
	@Bean
	public GenreApplicationMapper genreApplicationMapper() {
		return new GenreApplicationMapper();
	}
}
