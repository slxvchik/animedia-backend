package dev.animedia.languageservice.application.dto;

public record LanguageDto(
    String code,
    String name,
    Boolean isActive,
    Boolean isDefault,
    Integer sortOrder,
    String flagEmoji
) {}