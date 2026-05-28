package dev.animedia.contentservice.application.content.dto;

import java.util.UUID;

public record ContentTranslationDto(
	UUID id,
	String languageCode,
	String title,
	String description
) {}