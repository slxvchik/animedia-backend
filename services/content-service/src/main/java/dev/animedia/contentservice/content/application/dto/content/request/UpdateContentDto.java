package dev.animedia.contentservice.content.application.dto.content.request;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record UpdateContentDto(
	UUID id,
	String statusId,
	String coverImageId,
	String trailerVideoId,
	LocalDate releaseDate,
	Boolean active,
	Integer sortOrder,
	Set<String> languageCodes,
	Set<String> genreIds,
	Set<UpdateContentTranslationDto> translations
) {}