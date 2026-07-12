package dev.animedia.contentservice.content.application.dto;

import dev.animedia.contentservice.genre.application.dto.GenreDto;
import dev.animedia.contentservice.status.application.dto.StatusDto;
import dev.animedia.contentservice.content.domain.model.ContentType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record ContentDto(
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
	Set<String>languageCodeSet,
	Set<GenreDto> genreSet,
	Set<ContentTranslationDto> translationSet
) {}