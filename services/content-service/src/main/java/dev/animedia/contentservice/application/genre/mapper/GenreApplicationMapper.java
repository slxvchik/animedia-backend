package dev.animedia.contentservice.application.genre.mapper;

import dev.animedia.contentservice.application.genre.dto.GenreDto;
import dev.animedia.contentservice.application.genre.dto.GenreSearchDto;
import dev.animedia.contentservice.application.genre.dto.GenreTranslationDto;
import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.genre.model.GenreSearchCriteria;
import dev.animedia.contentservice.domain.genre.model.GenreTranslation;

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

	public GenreSearchCriteria toGenreSearchCriteria(GenreSearchDto searchGenreDto) {
		return new GenreSearchCriteria(
			searchGenreDto.onlyActive(),
			searchGenreDto.alias(),
			searchGenreDto.name(),
			searchGenreDto.description(),
			searchGenreDto.languageCode()
		);
	}
}
