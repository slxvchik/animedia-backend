package dev.animedia.contentservice.domain.status.model;

import jakarta.annotation.Nullable;

public record StatusSearchCriteria(
	@Nullable Boolean active,
    @Nullable String alias,
    @Nullable String name,
    @Nullable String languageCode
) {}