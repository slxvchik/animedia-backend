package dev.animedia.contentservice.application.status.dto;

import jakarta.annotation.Nullable;

public record StatusSearchDto(
	@Nullable Boolean active,
    @Nullable String alias,
    @Nullable String name,
    @Nullable String languageCode
) {}