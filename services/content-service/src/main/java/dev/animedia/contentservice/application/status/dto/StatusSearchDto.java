package dev.animedia.contentservice.application.status.dto;

import jakarta.annotation.Nullable;

import java.util.List;

public record StatusSearchDto(
    @Nullable String alias,
    @Nullable String name,
    @Nullable String languageCode
) {}