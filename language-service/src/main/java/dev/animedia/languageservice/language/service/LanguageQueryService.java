package dev.animedia.languageservice.language.service;

import dev.animedia.languageservice.language.dto.LanguageResponseDto;

import java.util.List;

public interface LanguageQueryService {

    LanguageResponseDto findByCode(String languageCode);
    List<LanguageResponseDto> findByCodes(List<String> languageCodes);

    boolean existsByCode(String languageCode);
    boolean existsByNameExcludingId(String languageName, String languageCode);
}
