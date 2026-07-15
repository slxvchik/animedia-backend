package dev.animedia.contentservice.status.application.dto.response;

import java.util.Set;
import java.util.UUID;

public record StatusDto(
	UUID id,
    String alias,
    Integer sortOrder,
	Boolean active,
    Set<StatusTranslationDto> translations
) {}