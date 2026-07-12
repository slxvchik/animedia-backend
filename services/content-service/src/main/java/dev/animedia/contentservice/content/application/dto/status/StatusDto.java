package dev.animedia.contentservice.content.application.dto.status;

import java.util.Set;
import java.util.UUID;

public record StatusDto(
	UUID id,
    String alias,
    Integer sortOrder,
	Boolean active,
    Set<StatusTranslationDto> translationSet
) {}