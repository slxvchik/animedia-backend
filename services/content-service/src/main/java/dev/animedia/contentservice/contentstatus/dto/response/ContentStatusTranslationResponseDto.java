package dev.animedia.contentservice.contentstatus.dto.response;

public record ContentStatusTranslationResponseDto(
    Long id,
    Long contentStatusId,
    String languageCode,
    String name
) {}