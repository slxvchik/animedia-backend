package dev.animedia.contentservice.contentstatus.dto.response;

public record ContentStatusWithTranslationResponseDto(
    Long id,
    String alias,
    Long contentStatusTranslationId,
    String languageCode,
    String name
) {}
