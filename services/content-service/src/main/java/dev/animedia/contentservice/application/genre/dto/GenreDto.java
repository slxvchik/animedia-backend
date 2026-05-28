package dev.animedia.contentservice.application.genre.dto;

import java.util.Set;

public record GenreDto(
	Long id,
	String alias,
	Integer sortOrder,
	Boolean active,
	Set<GenreTranslationDto> translationSet
) {}