package dev.animedia.contentservice.genre.application.dto;

import java.util.Set;
import java.util.UUID;

public record GenreDto(
	UUID id,
	String alias,
	Integer sortOrder,
	Boolean active,
	Set<GenreTranslationDto> translationSet
) {}