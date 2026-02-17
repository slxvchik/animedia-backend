package dev.animedia.languageservice.language.service;

import dev.animedia.languageservice.language.dto.LanguageRequestDto;
import dev.animedia.languageservice.language.dto.LanguageResponseDto;

public interface LanguageCommandService {
    LanguageResponseDto create(LanguageRequestDto requestDto);
    LanguageResponseDto update(LanguageRequestDto requestDto);
    void delete(String languageCode);
}
