package dev.animedia.contentservice.genre.application.dto.request;

import java.util.Set;

public record CreateGenreDto(
	String alias,
	Integer sortOrder,
	Boolean active,
	Set<CreateGenreTranslationDto> translationSet
) {}