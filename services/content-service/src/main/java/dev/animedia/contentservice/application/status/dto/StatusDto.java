package dev.animedia.contentservice.application.status.dto;

import java.util.Set;

public record StatusDto(
    Long id,
    String alias,
    int sortOrder,
    Set<StatusTranslationDto> translationSet
) {}