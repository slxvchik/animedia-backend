package dev.animedia.contentservice.genre.application.dto.response;

import java.util.UUID;

public record GenreTranslationDto(
	UUID id,
	String languageCode,
	String name,
	String description
) {}