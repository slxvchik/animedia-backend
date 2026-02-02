package dev.animedia.contentservice.language.service;

import dev.animedia.contentservice.language.dto.LanguageResponseDto;

import java.util.Collection;
import java.util.List;

public interface LanguageQueryService {

    LanguageResponseDto findByCode(String languageCode);
    List<LanguageResponseDto> findAllByCodes(Collection<String> languageCodes);

    boolean existsByCode(String languageCode);
    boolean existsAnyByCodes(Collection<String> languageCodes);
    boolean existsAllByCodes(Collection<String> languageCodes);
}
