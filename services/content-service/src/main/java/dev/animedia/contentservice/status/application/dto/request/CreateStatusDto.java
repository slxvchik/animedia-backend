package dev.animedia.contentservice.status.application.dto.request;

import java.util.Set;

public record CreateStatusDto(
    String alias,
    Integer sortOrder,
	Boolean active,
    Set<CreateStatusTranslationDto> translationSet
) {}