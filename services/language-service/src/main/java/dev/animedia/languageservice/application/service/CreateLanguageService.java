package dev.animedia.languageservice.application.service;

import dev.animedia.languageservice.application.dto.LanguageDto;
import dev.animedia.languageservice.application.mapper.LanguageApplicationMapper;
import dev.animedia.languageservice.application.usecase.CreateLanguageUseCase;
import dev.animedia.languageservice.domain.exception.LanguageCodeAlreadyExistsException;
import dev.animedia.languageservice.domain.model.Language;
import dev.animedia.languageservice.domain.repository.LanguageCommandRepository;
import dev.animedia.languageservice.domain.repository.LanguageQueryRepository;

public class CreateLanguageService implements CreateLanguageUseCase {
    private final LanguageCommandRepository languageCommandRepository;
    private final LanguageQueryRepository languageQueryRepository;
    private final LanguageApplicationMapper languageApplicationMapper;

    public CreateLanguageService(
        LanguageCommandRepository languageCommandRepository,
        LanguageQueryRepository languageQueryRepository,
        LanguageApplicationMapper languageApplicationMapper
    ) {
        this.languageCommandRepository = languageCommandRepository;
        this.languageQueryRepository = languageQueryRepository;
        this.languageApplicationMapper = languageApplicationMapper;
    }

    @Override
    public LanguageDto create(LanguageDto request) {
        if(languageQueryRepository.findByCode(request.code()).isPresent()) {
            throw new LanguageCodeAlreadyExistsException();
        }

        Language language = languageApplicationMapper.toDomain(request);

        Language saved = languageCommandRepository.create(language);

        return languageApplicationMapper.toDto(saved);
    }
}
