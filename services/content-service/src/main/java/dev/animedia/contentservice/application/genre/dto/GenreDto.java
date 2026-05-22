package dev.animedia.contentservice.application.genre.dto;

import java.util.Set;

public record GenreDto(
	Long id,
	String alias,
	int sortOrder,
	boolean active,
	Set<GenreTranslationDto> translationSet
) {}