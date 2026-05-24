package dev.animedia.languageservice.application.service;

import dev.animedia.languageservice.application.usecase.DeleteLanguageUseCase;
import dev.animedia.languageservice.domain.exception.LanguageNotFoundException;
import dev.animedia.languageservice.domain.repository.LanguageCommandRepository;
import dev.animedia.languageservice.domain.repository.LanguageQueryRepository;

public class DeleteLanguageService implements DeleteLanguageUseCase {
    private final LanguageCommandRepository languageCommandRepository;
    private final LanguageQueryRepository languageQueryRepository;

    public DeleteLanguageService(
        LanguageCommandRepository languageCommandRepository,
        LanguageQueryRepository languageQueryRepository
    ) {
        this.languageCommandRepository = languageCommandRepository;
        this.languageQueryRepository = languageQueryRepository;
    }

    @Override
    public void delete(String code) {
        languageQueryRepository.findByCode(code)
            .orElseThrow(LanguageNotFoundException::new);
        languageCommandRepository.delete(code);
    }
}
