package dev.animedia.contentservice.application.genre.dto;

import java.util.UUID;

public record GenreTranslationDto(
	UUID id,
	String languageCode,
	String name,
	String description
) {}