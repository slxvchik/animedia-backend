package dev.animedia.contentservice.language.service;

import dev.animedia.contentservice.language.dto.LanguageResponseDto;

import java.util.List;

public interface LanguageQueryService {

    LanguageResponseDto findByCode(String languageCode);
    List<LanguageResponseDto> findAllByCodes(List<String> languageCodes);

    boolean existsByCode(String languageCode);
    boolean existsAnyByCodes(List<String> languageCodes);
    boolean existsAllByCodes(List<String> languageCodes);
}
