package dev.animedia.languageservice.application.service;

import dev.animedia.languageservice.application.dto.LanguageDto;
import dev.animedia.languageservice.application.mapper.LanguageApplicationMapper;
import dev.animedia.languageservice.application.usecase.GetLanguageUseCase;
import dev.animedia.languageservice.domain.exception.LanguageNotFoundException;
import dev.animedia.languageservice.domain.model.Language;
import dev.animedia.languageservice.domain.repository.LanguageQueryRepository;

public class GetLanguageService implements GetLanguageUseCase {
    private final LanguageQueryRepository languageQueryRepository;
    private final LanguageApplicationMapper languageApplicationMapper;

    public GetLanguageService(
        LanguageQueryRepository languageQueryRepository,
        LanguageApplicationMapper languageApplicationMapper
    ) {
        this.languageQueryRepository = languageQueryRepository;
        this.languageApplicationMapper = languageApplicationMapper;
    }

    @Override
    public LanguageDto getByCode(String code) {
        Language language = languageQueryRepository.findByCode(code)
            .orElseThrow(LanguageNotFoundException::new);
        return languageApplicationMapper.toDto(language);
    }
}
