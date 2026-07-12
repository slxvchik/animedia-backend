package dev.animedia.contentservice.content.application.dto.content;

import dev.animedia.contentservice.content.application.dto.genre.GenreDto;
import dev.animedia.contentservice.content.application.dto.status.StatusDto;
import dev.animedia.contentservice.content.domain.model.ContentType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record ContentResponseDto(
	UUID id,
	String alias,
	ContentType type,
	Integer season,
	StatusDto status,
	String coverImageId,
	String trailerVideoId,
	LocalDate releaseDate,
	LocalDateTime createdAt,
	LocalDateTime updatedAt,
	Boolean active,
	Integer sortOrder,
	Set<String> languageCodeSet,
	Set<GenreDto> genreDtoSet,
	Set<ContentTranslationDto> translationSet
) {}