package dev.animedia.contentservice.genre.application.dto.request;

import java.util.UUID;

public record UpdateGenreTranslationDto(
	UUID id,
	String languageCode,
	String name,
	String description
) {}