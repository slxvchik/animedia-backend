package dev.animedia.contentservice.status.application.dto.request;

import java.util.Set;
import java.util.UUID;

public record UpdateStatusDto(
	UUID id,
    Integer sortOrder,
	Boolean active,
    Set<UpdateStatusTranslationDto> translationSet
) {}