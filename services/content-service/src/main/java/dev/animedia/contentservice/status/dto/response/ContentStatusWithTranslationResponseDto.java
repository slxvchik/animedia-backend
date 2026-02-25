package dev.animedia.contentservice.status.dto.response;

public record ContentStatusWithTranslationResponseDto(
    Long id,
    String alias,
    Long contentStatusTranslationId,
    String languageCode,
    String name
) {}
