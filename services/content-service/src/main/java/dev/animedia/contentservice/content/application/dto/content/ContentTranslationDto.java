package dev.animedia.contentservice.content.application.dto.content;

import java.util.UUID;

public record ContentTranslationDto(
	UUID id,
	String languageCode,
	String title,
	String description
) {}