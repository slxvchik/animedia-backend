package dev.animedia.languageservice.application.service;

import dev.animedia.languageservice.application.dto.LanguageDto;
import dev.animedia.languageservice.application.mapper.LanguageApplicationMapper;
import dev.animedia.languageservice.application.usecase.GetLanguageUseCase;
import dev.animedia.languageservice.domain.exception.LanguageNotFoundException;
import dev.animedia.languageservice.domain.model.Language;
import dev.animedia.languageservice.domain.repository.LanguageQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetLanguageService implements GetLanguageUseCase {
    private final LanguageQueryRepository languageQueryRepository;
    private final LanguageApplicationMapper languageApplicationMapper;
    @Autowired
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
