package dev.animedia.contentservice.application.genre.dto;

import java.util.Set;
import java.util.UUID;

public record GenreDto(
	UUID id,
	String alias,
	Integer sortOrder,
	Boolean active,
	Set<GenreTranslationDto> translationSet
) {}