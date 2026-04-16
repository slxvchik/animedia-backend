package dev.animedia.contentservice.application.genre.dto;

public record GenreTranslationDto(
	Long id,
	String languageCode,
	String name,
	String description
) {}