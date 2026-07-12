package dev.animedia.contentservice.content.application.dto.status;

import java.util.UUID;

public record StatusTranslationDto(
	UUID id,
    String languageCode,
    String name
) {}