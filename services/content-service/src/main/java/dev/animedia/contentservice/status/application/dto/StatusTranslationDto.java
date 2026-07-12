package dev.animedia.contentservice.status.application.dto;

import java.util.UUID;

public record StatusTranslationDto(
	UUID id,
    String languageCode,
    String name
) {}