package dev.animedia.contentservice.application.genre.mapper;

import dev.animedia.contentservice.application.genre.dto.GenreDto;
import dev.animedia.contentservice.application.genre.dto.GenreTranslationDto;
import dev.animedia.contentservice.application.genre.dto.GenreSearchDto;
import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.genre.model.GenreSearchCriteria;
import dev.animedia.contentservice.domain.genre.model.GenreTranslation;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class GenreApplicationMapper {
	public GenreDto toGenreDto(Genre genre) {
		if (genre == null) return null;
		return new GenreDto(
			genre.getId(),
			genre.getAlias(),
			genre.getSortOrder(),
			genre.getTranslationSet().stream()
				.map(this::toGenreTranslationDto)
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
			genreDto.translationSet().stream()
				.map(this::toGenreTranslation)
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
			searchGenreDto.aliasList(),
			searchGenreDto.nameList(),
			searchGenreDto.description(),
			searchGenreDto.languageCodeList()
		);
	}
}
