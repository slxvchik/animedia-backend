package dev.animedia.contentservice.content.application.dto.content;

import dev.animedia.contentservice.content.domain.model.ContentType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record ContentRequestDto(
	UUID id,
	String alias,
	ContentType type,
	Integer season,
	String statusId,
	String coverImageId,
	String trailerVideoId,
	LocalDate releaseDate,
	LocalDateTime createdAt,
	LocalDateTime updatedAt,
	Boolean active,
	Integer sortOrder,
	Set<String> languageCodeSet,
	Set<String> genreIdSet,
	Set<ContentTranslationDto> translationSet
) {}