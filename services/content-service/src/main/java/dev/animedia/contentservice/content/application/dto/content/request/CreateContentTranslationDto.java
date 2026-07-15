package dev.animedia.contentservice.content.application.dto.content.request;

public record CreateContentTranslationDto(
	String languageCode,
	String title,
	String description
) {}