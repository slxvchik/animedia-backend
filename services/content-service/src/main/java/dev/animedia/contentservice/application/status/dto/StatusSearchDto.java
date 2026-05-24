package dev.animedia.contentservice.application.status.dto;

import jakarta.annotation.Nullable;

public record StatusSearchDto(
	boolean onlyActive,
    @Nullable String alias,
    @Nullable String name,
    @Nullable String languageCode
) {}