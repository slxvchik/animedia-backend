package dev.animedia.contentservice.application.genre.dto;

import java.util.Set;

public record GenreDto(
	Long id,
	String alias,
	long sortOrder,
	Set<GenreTranslationDto> translationSet
) {}