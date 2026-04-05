package dev.animedia.languageservice.application.dto;

import dev.animedia.languageservice.domain.model.Pageable;

import java.util.List;

public record SearchLanguageDto(
    List<String> codes,
    List<String> names,
    Boolean isActive,
    Pageable pagination
) {
}
