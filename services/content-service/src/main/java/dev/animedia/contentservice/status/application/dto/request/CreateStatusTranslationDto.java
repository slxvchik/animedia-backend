package dev.animedia.contentservice.status.application.dto.request;

public record CreateStatusTranslationDto(
    String languageCode,
    String name
) {}