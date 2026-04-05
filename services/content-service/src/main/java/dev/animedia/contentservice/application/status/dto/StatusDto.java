package dev.animedia.contentservice.application.status.dto;

import java.util.Set;

public record StatusDto(
    long id,
    String alias,
    int sortOrder,
    Set<StatusTranslationDto> translationSet
) {}