package dev.animedia.contentservice.content.dto.response;

import dev.animedia.contentservice.content.model.ContentType;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationResponseDto;
import dev.animedia.contentservice.status.dto.response.ContentStatusWithTranslationResponseDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ContentResponseDto(
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
	List<GenreWithTranslationResponseDto> genres
) {}
