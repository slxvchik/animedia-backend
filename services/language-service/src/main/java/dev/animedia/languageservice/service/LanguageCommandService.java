package dev.animedia.languageservice.service;

import dev.animedia.languageservice.dto.LanguageRequestDto;
import dev.animedia.languageservice.dto.LanguageResponseDto;

public interface LanguageCommandService {
    LanguageResponseDto create(LanguageRequestDto requestDto);
    LanguageResponseDto update(LanguageRequestDto requestDto);
    void delete(String languageCode);
}
