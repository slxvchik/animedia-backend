package dev.animedia.contentservice.application.content.dto;

import dev.animedia.contentservice.application.genre.dto.GenreDto;
import dev.animedia.contentservice.application.status.dto.StatusDto;
import dev.animedia.contentservice.domain.content.model.ContentType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record ContentDto(
	UUID id,
	String alias,
	ContentType type,
	int season,
	StatusDto status,
	String coverUrl,
	String trailerUrl,
	LocalDate releaseDate,
	LocalDateTime createdAt,
	LocalDateTime updatedAt,
	boolean active,
	int sort,
	Set<String>languageCodeSet,
	Set<GenreDto> genreSet,
	Set<ContentTranslationDto> translationSet
) {}