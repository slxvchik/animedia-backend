package dev.animedia.contentservice.content.infrastracture.resolver.genre;

import dev.animedia.contentservice.content.application.dto.genre.GenreDto;
import dev.animedia.contentservice.content.application.dto.genre.GenreTranslationDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class GenreResolverMapper {
	public GenreDto toContentGenreDto(dev.animedia.contentservice.genre.application.dto.response.GenreDto genreDto) {
		if (genreDto == null) return null;
		return new GenreDto(
			genreDto.id(),
			genreDto.alias(),
			genreDto.sortOrder(),
			genreDto.active(),
			genreDto.translations().stream().map(this::toContentGenreTranslationDto).collect(Collectors.toSet())
		);
	}

	public GenreTranslationDto toContentGenreTranslationDto(dev.animedia.contentservice.genre.application.dto.response.GenreTranslationDto genreTranslationDto) {
		if (genreTranslationDto == null) return null;
		return new GenreTranslationDto(
			genreTranslationDto.id(),
			genreTranslationDto.languageCode(),
			genreTranslationDto.name(),
			genreTranslationDto.description()
		);
	}
}
