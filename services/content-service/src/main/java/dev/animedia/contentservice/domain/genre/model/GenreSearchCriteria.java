package dev.animedia.contentservice.domain.genre.model;

import jakarta.annotation.Nullable;

public record GenreSearchCriteria(
	boolean onlyActive,
    @Nullable String alias,
    @Nullable String name,
    @Nullable String description,
    @Nullable String languageCode
) {}