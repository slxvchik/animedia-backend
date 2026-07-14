package dev.animedia.contentservice.genre.application.dto.request;

public record CreateGenreTranslationDto(
	String languageCode,
	String name,
	String description
) {}