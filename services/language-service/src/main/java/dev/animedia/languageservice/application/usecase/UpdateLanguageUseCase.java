package dev.animedia.languageservice.application.usecase;

import dev.animedia.languageservice.application.dto.LanguageDto;

public interface UpdateLanguageUseCase {
    LanguageDto update(LanguageDto languageDto);
}
