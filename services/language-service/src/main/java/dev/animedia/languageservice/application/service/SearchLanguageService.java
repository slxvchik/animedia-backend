package dev.animedia.languageservice.application.service;

import dev.animedia.languageservice.application.dto.LanguageDto;
import dev.animedia.languageservice.application.dto.SearchLanguageDto;
import dev.animedia.languageservice.application.mapper.LanguageApplicationMapper;
import dev.animedia.languageservice.application.usecase.SearchLanguageUseCase;
import dev.animedia.languageservice.domain.model.Language;
import dev.animedia.languageservice.domain.model.Page;
import dev.animedia.languageservice.domain.repository.LanguageQueryRepository;

public class SearchLanguageService implements SearchLanguageUseCase {
    private final LanguageQueryRepository languageQueryRepository;
    private final LanguageApplicationMapper languageApplicationMapper;

    public SearchLanguageService(
        LanguageQueryRepository languageQueryRepository,
        LanguageApplicationMapper languageApplicationMapper
    ) {
        this.languageQueryRepository = languageQueryRepository;
        this.languageApplicationMapper = languageApplicationMapper;
    }

    @Override
    public Page<LanguageDto> search(SearchLanguageDto searchDto) {
        Page<Language> languagePage = languageQueryRepository.search(searchDto.codes(), searchDto.names(), searchDto.isActive(), searchDto.pagination());
        var content = languagePage.content().stream().map(languageApplicationMapper::toDto).toList();
        return new Page<>(
            content,
            languagePage.totalElements(),
            languagePage.totalPages(),
            languagePage.pageNumber(),
            languagePage.pageSize(),
            languagePage.isFirst(),
            languagePage.isLast(),
            languagePage.hasNext(),
            languagePage.hasPrevious()
        );
    }
}
