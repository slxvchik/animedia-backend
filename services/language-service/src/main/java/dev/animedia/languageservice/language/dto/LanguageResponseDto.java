package dev.animedia.languageservice.language.dto;

public record LanguageResponseDto(
    String code,
    String name,
	String nativeName,
	Boolean isActive,
	Boolean isDefault,
	Integer sortOrder,
	String flagEmoji
) {}
