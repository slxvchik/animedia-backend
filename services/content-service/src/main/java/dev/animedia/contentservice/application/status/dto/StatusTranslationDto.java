package dev.animedia.contentservice.application.status.dto;

public record StatusTranslationDto(
    Long id,
    String languageCode,
    String name
) {}