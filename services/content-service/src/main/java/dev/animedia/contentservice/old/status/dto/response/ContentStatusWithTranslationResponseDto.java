package dev.animedia.contentservice.old.status.dto.response;

public record ContentStatusWithTranslationResponseDto(
    Long id,
    String alias,
    Long contentStatusTranslationId,
    String languageCode,
    String name
) {}
