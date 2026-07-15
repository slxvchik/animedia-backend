package dev.animedia.contentservice.content.application.dto.content.request;

import java.util.UUID;

public record UpdateContentTranslationDto(
	UUID id,
	String languageCode,
	String title,
	String description
) {}