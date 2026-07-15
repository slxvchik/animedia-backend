package dev.animedia.contentservice.content.application.dto.content.request;

import dev.animedia.contentservice.content.domain.model.ContentType;

import java.time.LocalDate;
import java.util.Set;

public record CreateContentDto(
	String alias,
	ContentType type,
	Integer season,
	String statusId,
	String coverImageId,
	String trailerVideoId,
	LocalDate releaseDate,
	Boolean active,
	Integer sortOrder,
	Set<String> languageCodes,
	Set<String> genreIds,
	Set<CreateContentTranslationDto> translations
) {}