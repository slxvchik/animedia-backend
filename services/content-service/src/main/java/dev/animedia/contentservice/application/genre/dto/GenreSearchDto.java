package dev.animedia.contentservice.application.genre.dto;

import jakarta.annotation.Nullable;

public record GenreSearchDto(
	@Nullable String alias,
	@Nullable String name,
	@Nullable String description,
	@Nullable String languageCode
) {}