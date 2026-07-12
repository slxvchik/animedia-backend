package dev.animedia.contentservice.genre.application.mapper;

import dev.animedia.contentservice.genre.application.dto.GenreDto;
import dev.animedia.contentservice.genre.application.dto.GenreTranslationDto;
import dev.animedia.contentservice.genre.domain.model.Genre;
import dev.animedia.contentservice.genre.domain.model.GenreTranslation;

import java.util.Objects;
import java.util.stream.Collectors;

public class GenreApplicationMapper {
	public GenreDto toGenreDto(Genre genre) {
		if (genre == null) return null;
		return new GenreDto(
			genre.getId(),
			genre.getAlias(),
			genre.getSortOrder(),
			genre.getActive(),
			genre.getTranslationSet().stream()
				.map(this::toGenreTranslationDto)
				.filter(Objects::nonNull)
				.collect(Collectors.toSet())
		);
	}

	public GenreTranslationDto toGenreTranslationDto(GenreTranslation genreTranslation) {
		if (genreTranslation == null) return null;
		return new GenreTranslationDto(
			genreTranslation.getId(),
			genreTranslation.getLanguageCode(),
			genreTranslation.getName(),
			genreTranslation.getDescription()
		);
	}

	public Genre toGenre(GenreDto genreDto) {
		if (genreDto == null) return null;
		return new Genre(
			genreDto.id(),
			genreDto.alias(),
			genreDto.sortOrder(),
			genreDto.active(),
			genreDto.translationSet().stream()
				.map(this::toGenreTranslation)
				.filter(Objects::nonNull)
				.collect(Collectors.toSet())
		);
	}

	public GenreTranslation toGenreTranslation(GenreTranslationDto genreTranslationDto) {
		if (genreTranslationDto == null) return null;
		return new GenreTranslation(
			genreTranslationDto.id(),
			genreTranslationDto.languageCode(),
			genreTranslationDto.name(),
			genreTranslationDto.description()
		);
	}
}
