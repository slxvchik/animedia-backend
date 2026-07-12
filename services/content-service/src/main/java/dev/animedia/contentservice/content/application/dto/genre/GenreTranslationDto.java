package dev.animedia.contentservice.content.application.dto.genre;

import java.util.UUID;

public record GenreTranslationDto(
	UUID id,
	String languageCode,
	String name,
	String description
) {}