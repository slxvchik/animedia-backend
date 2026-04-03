package dev.animedia.contentservice.old.content.dto.response;

import dev.animedia.contentservice.old.content.model.ContentType;
import dev.animedia.contentservice.old.genre.dto.response.GenreWithTranslationResponseDto;
import dev.animedia.contentservice.old.status.dto.response.ContentStatusWithTranslationResponseDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ContentWithTranslationListResponseDto(
	String uuid,
	String alias,
	ContentType type,
	Integer season,
	ContentStatusWithTranslationResponseDto status,
	String coverUrl,
	String trailerUrl,
	LocalDate releaseDate,
	LocalDateTime createdAt,
	LocalDateTime updatedAt,
	Boolean active,
	Integer sort,
	List<String> languageCodes,
	List<GenreWithTranslationResponseDto> genres,

	List<ContentTranslationResponseDto> translations
) {}
