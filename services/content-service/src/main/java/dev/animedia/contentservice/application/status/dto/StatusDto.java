package dev.animedia.contentservice.application.status.dto;

import java.util.Set;
import java.util.UUID;

public record StatusDto(
	UUID id,
    String alias,
    Integer sortOrder,
	Boolean active,
    Set<StatusTranslationDto> translationSet
) {}