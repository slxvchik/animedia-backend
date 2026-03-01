package dev.animedia.contentservice.content.mapper;

import dev.animedia.contentservice.content.dto.request.ContentRequestDto;
import dev.animedia.contentservice.content.dto.response.ContentResponseDto;
import dev.animedia.contentservice.content.dto.response.ContentTranslationResponseDto;
import dev.animedia.contentservice.content.dto.response.ContentWithTranslationResponseDto;
import dev.animedia.contentservice.content.dto.response.ContentWithTranslationsResponseDto;
import dev.animedia.contentservice.content.model.Content;
import dev.animedia.contentservice.content.model.ContentType;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationResponseDto;
import dev.animedia.contentservice.genre.model.Genre;
import dev.animedia.contentservice.status.dto.response.ContentStatusWithTranslationResponseDto;
import dev.animedia.contentservice.status.model.ContentStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ContentMapper {

	@PersistenceContext
	private EntityManager entityManager;

	public Content toContent(ContentRequestDto dto) {
		Content content = new Content();

		content.setAlias(dto.alias());
		content.setType(dto.type());
		content.setSeason(dto.season());

		ContentStatus status = entityManager.getReference(ContentStatus.class, dto.contentStatusId());
		content.setStatus(status);

		content.setCoverUrl(dto.coverUrl());
		content.setTrailerUrl(dto.trailerUrl());
		content.setReleaseDate(dto.releaseDate());
		content.setActive(dto.active());
		content.setSort(dto.sort());
		content.setLanguageCodes(dto.languageCodes());

		Set<Genre> genres = new HashSet<>();
		for (var genreId : dto.genreIds()) {
			genres.add(entityManager.getReference(Genre.class, genreId));
		}
		content.setGenres(genres);

		return content;
	}

	public ContentResponseDto toContentResponseDto(
		Content content,
		ContentStatusWithTranslationResponseDto contentStatusWithTranslation,
		List<GenreWithTranslationResponseDto> genresWithTranslation
	) {
		return new ContentResponseDto(
			content.getUuid().toString(),
			content.getAlias(),
			content.getType(),
			content.getSeason(),
			contentStatusWithTranslation,
			content.getCoverUrl(),
			content.getTrailerUrl(),
			content.getReleaseDate(),
			content.getCreatedAt(),
			content.getUpdatedAt(),
			content.getActive(),
			content.getSort(),
			new ArrayList<>(content.getLanguageCodes()),
			genresWithTranslation
		);
	}

	public List<ContentWithTranslationsResponseDto> toContentsWithTranslationsResponseDto(
		List<Content> contents,
		List<ContentTranslationResponseDto> contentsTranslation,
		List<ContentStatusWithTranslationResponseDto> contentStatusesWithTranslation,
		List<GenreWithTranslationResponseDto> genresWithTranslation
	) {
		return null;
	}

	public List<ContentWithTranslationResponseDto> toContentsWithTranslationResponseDto(
		List<Content> contents,
		List<ContentTranslationResponseDto> contentsTranslation,
		List<ContentStatusWithTranslationResponseDto> contentStatusesWithTranslation,
		List<GenreWithTranslationResponseDto> genresWithTranslation
	) {
		return null;
	}

	public ContentWithTranslationResponseDto toContentWithTranslationResponseDto(
		ContentResponseDto contentResponseDto,
		ContentTranslationResponseDto translationResponseDto
	) {
		return new ContentWithTranslationResponseDto(
			contentResponseDto.uuid(),
			contentResponseDto.alias(),
			contentResponseDto.type(),
			contentResponseDto.season(),
			contentResponseDto.status(),
			contentResponseDto.coverUrl(),
			contentResponseDto.trailerUrl(),
			contentResponseDto.releaseDate(),
			contentResponseDto.createdAt(),
			contentResponseDto.updatedAt(),
			contentResponseDto.active(),
			contentResponseDto.sort(),
			contentResponseDto.languageCodes(),
			contentResponseDto.genres(),

			translationResponseDto.uuid(),
			translationResponseDto.languageCode(),
			translationResponseDto.title(),
			translationResponseDto.description()
		);
	}

	public void updateEntity(ContentRequestDto dto, Content content) {
		content.setAlias(dto.alias());
		content.setType(dto.type());
		content.setSeason(dto.season());

		content.setStatus(entityManager.getReference(ContentStatus.class, dto.contentStatusId()));

		content.setCoverUrl(dto.coverUrl());
		content.setTrailerUrl(dto.trailerUrl());
		content.setReleaseDate(dto.releaseDate());
		content.setActive(dto.active());

		content.getLanguageCodes().clear();
		dto.languageCodes().forEach(code ->
			content.getLanguageCodes().add(code)
		);

		content.getGenres().clear();
		dto.genreIds().forEach(id ->
			content.getGenres().add(entityManager.getReference(Genre.class, id))
		);
	}
}