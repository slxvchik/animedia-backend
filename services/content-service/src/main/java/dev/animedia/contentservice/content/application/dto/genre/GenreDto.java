package dev.animedia.contentservice.content.application.dto.genre;

import java.util.Set;
import java.util.UUID;

public record GenreDto(
	UUID id,
	String alias,
	Integer sortOrder,
	Boolean active,
	Set<GenreTranslationDto> translationSet
) {}