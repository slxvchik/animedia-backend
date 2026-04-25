package dev.animedia.contentservice.domain.genre.model;

import jakarta.annotation.Nullable;

import java.util.List;

public record GenreSearchCriteria(
    @Nullable String alias,
    @Nullable String name,
    @Nullable String description,
    @Nullable String languageCode
) {}