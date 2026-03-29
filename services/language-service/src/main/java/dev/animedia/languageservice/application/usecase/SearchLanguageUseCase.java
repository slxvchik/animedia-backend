package dev.animedia.languageservice.application.usecase;

import dev.animedia.languageservice.application.dto.LanguageDto;
import dev.animedia.languageservice.application.dto.Page;
import dev.animedia.languageservice.application.dto.SearchLanguageDto;

public interface SearchLanguageUseCase {
    Page<LanguageDto> search(SearchLanguageDto searchDto);
}
