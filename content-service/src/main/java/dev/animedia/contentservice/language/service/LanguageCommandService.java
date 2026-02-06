package dev.animedia.contentservice.language.service;

import dev.animedia.contentservice.language.dto.LanguageRequestDto;
import dev.animedia.contentservice.language.dto.LanguageResponseDto;

public interface LanguageCommandService {
    LanguageResponseDto create(LanguageRequestDto requestDto);
    LanguageResponseDto update(LanguageRequestDto requestDto);
    void delete(String languageCode);
}
