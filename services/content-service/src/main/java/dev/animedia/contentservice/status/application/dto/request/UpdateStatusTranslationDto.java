package dev.animedia.contentservice.status.application.dto.request;

import java.util.UUID;

public record UpdateStatusTranslationDto(
	UUID id,
    String languageCode,
    String name
) {}