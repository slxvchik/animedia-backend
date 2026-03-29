package dev.animedia.languageservice.application.service;

import dev.animedia.languageservice.application.dto.LanguageDto;
import dev.animedia.languageservice.application.mapper.LanguageApplicationMapper;
import dev.animedia.languageservice.application.usecase.UpdateLanguageUseCase;
import dev.animedia.languageservice.domain.exception.LanguageNotFoundException;
import dev.animedia.languageservice.domain.model.Language;
import dev.animedia.languageservice.domain.repository.LanguageCommandRepository;
import dev.animedia.languageservice.domain.repository.LanguageQueryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateLanguageService implements UpdateLanguageUseCase {
    private final LanguageCommandRepository languageCommandRepository;
    private final LanguageQueryRepository languageQueryRepository;
    private final LanguageApplicationMapper languageApplicationMapper;

    @Autowired
    public UpdateLanguageService(
        LanguageCommandRepository languageCommandRepository,
        LanguageQueryRepository languageQueryRepository,
        LanguageApplicationMapper languageApplicationMapper
    ) {
        this.languageCommandRepository = languageCommandRepository;
        this.languageQueryRepository = languageQueryRepository;
        this.languageApplicationMapper = languageApplicationMapper;
    }

    @Transactional
    @Override
    public LanguageDto update(LanguageDto request) {
        Language language = languageQueryRepository.findByCode(request.code())
            .orElseThrow(LanguageNotFoundException::new);

        language.update(request.name(), request.isActive(), request.isDefault(), request.sortOrder(), request.flagEmoji());

        Language updated = languageCommandRepository.update(language);

        return languageApplicationMapper.toDto(updated);
    }
}
