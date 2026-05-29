package dev.animedia.contentservice.application.status.dto;

import java.util.UUID;

public record StatusTranslationDto(
	UUID id,
    String languageCode,
    String name
) {}