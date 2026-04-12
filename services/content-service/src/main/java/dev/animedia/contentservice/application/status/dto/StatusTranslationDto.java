package dev.animedia.contentservice.application.status.dto;

public record StatusTranslationDto(
    long id,
    String languageCode,
    String name
) {}