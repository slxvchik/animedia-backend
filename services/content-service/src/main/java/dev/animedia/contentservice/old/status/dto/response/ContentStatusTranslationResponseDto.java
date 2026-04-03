package dev.animedia.contentservice.old.status.dto.response;

public record ContentStatusTranslationResponseDto(
    Long id,
    Long contentStatusId,
    String languageCode,
    String name
) {}