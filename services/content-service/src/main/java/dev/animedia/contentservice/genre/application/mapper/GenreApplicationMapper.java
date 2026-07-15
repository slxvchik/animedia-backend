package dev.animedia.contentservice.genre.application.mapper;

import dev.animedia.contentservice.genre.application.dto.request.CreateGenreDto;
import dev.animedia.contentservice.genre.application.dto.request.CreateGenreTranslationDto;
import dev.animedia.contentservice.genre.application.dto.request.UpdateGenreDto;
import dev.animedia.contentservice.genre.application.dto.request.UpdateGenreTranslationDto;
import dev.animedia.contentservice.genre.application.dto.response.GenreDto;
import dev.animedia.contentservice.genre.application.dto.response.GenreTranslationDto;
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
			genre.getTranslations().stream()
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

	public Genre toGenre(CreateGenreDto genreDto) {
		if (genreDto == null) return null;
		return new Genre(
			null,
			genreDto.alias(),
			genreDto.sortOrder(),
			genreDto.active(),
			genreDto.translations().stream()
				.map(this::toGenreTranslation)
				.filter(Objects::nonNull)
				.collect(Collectors.toSet())
		);
	}

	public GenreTranslation toGenreTranslation(CreateGenreTranslationDto genreTranslationDto) {
		if (genreTranslationDto == null) return null;
		return new GenreTranslation(
			null,
			genreTranslationDto.languageCode(),
			genreTranslationDto.name(),
			genreTranslationDto.description()
		);
	}

	public GenreTranslation toGenreTranslation(UpdateGenreTranslationDto genreTranslationDto) {
		if (genreTranslationDto == null) return null;
		return new GenreTranslation(
			genreTranslationDto.id(),
			genreTranslationDto.languageCode(),
			genreTranslationDto.name(),
			genreTranslationDto.description()
		);
	}
}
