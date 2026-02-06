package dev.animedia.contentservice.language.service.impl;

import dev.animedia.contentservice.language.LanguageMapper;
import dev.animedia.contentservice.language.repository.LanguageRepository;
import dev.animedia.contentservice.language.dto.LanguageResponseDto;
import dev.animedia.contentservice.language.exception.LanguageCodeNotFoundException;
import dev.animedia.contentservice.language.service.LanguageQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class LanguageQueryServiceImpl implements LanguageQueryService {

    private final LanguageRepository languageRepository;
    private final LanguageMapper languageMapper;

    @Autowired
    public LanguageQueryServiceImpl(LanguageRepository languageRepository, LanguageMapper languageMapper) {
        this.languageRepository = languageRepository;
        this.languageMapper = languageMapper;
    }

    @Override
    public LanguageResponseDto findByCode(String languageCode) {
        var language = languageRepository.findById(languageCode).orElseThrow(LanguageCodeNotFoundException::new);
        return languageMapper.toLanguageResponseDto(language);
    }

    @Override
    public List<LanguageResponseDto> findByCodes(Collection<String> languageCodes) {
        var languages = languageRepository.findAllById(languageCodes);
        return languageMapper.toLanguagesResponseDto(languages);
    }

    @Override
    public boolean existsByCode(String languageCode) {
        return languageRepository.existsById(languageCode);
    }

    @Override
    public boolean existsByNameExcludingId(String languageName, String languageCode) {
        return languageRepository.existsByNameAndCodeIsNot(languageName, languageCode);
    }

}
