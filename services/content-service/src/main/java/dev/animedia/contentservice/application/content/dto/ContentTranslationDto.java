package dev.animedia.contentservice.application.content.dto;

import java.util.UUID;

public record ContentTranslationDto(
	UUID uuid,
	String languageCode,
	String title,
	String description
) {}