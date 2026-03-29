package dev.animedia.languageservice.application.service;

import dev.animedia.languageservice.application.usecase.DeleteLanguageUseCase;
import dev.animedia.languageservice.domain.exception.LanguageNotFoundException;
import dev.animedia.languageservice.domain.repository.LanguageCommandRepository;
import dev.animedia.languageservice.domain.repository.LanguageQueryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteLanguageService implements DeleteLanguageUseCase {
    private final LanguageCommandRepository languageCommandRepository;
    private final LanguageQueryRepository languageQueryRepository;

    @Autowired
    public DeleteLanguageService(
        LanguageCommandRepository languageCommandRepository,
        LanguageQueryRepository languageQueryRepository
    ) {
        this.languageCommandRepository = languageCommandRepository;
        this.languageQueryRepository = languageQueryRepository;
    }

    @Transactional
    @Override
    public void delete(String code) {
        languageQueryRepository.findByCode(code)
            .orElseThrow(LanguageNotFoundException::new);
        languageCommandRepository.delete(code);
    }
}
