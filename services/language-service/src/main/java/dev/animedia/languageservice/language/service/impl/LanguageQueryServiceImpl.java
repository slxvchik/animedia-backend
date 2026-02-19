package dev.animedia.languageservice.language.service.impl;

import dev.animedia.languageservice.app.exception.AppException;
import dev.animedia.languageservice.language.LanguageErrorConstants;
import dev.animedia.languageservice.language.LanguageMapper;
import dev.animedia.languageservice.language.repository.LanguageRepository;
import dev.animedia.languageservice.language.dto.LanguageResponseDto;
import dev.animedia.languageservice.language.service.LanguageQueryService;
import io.grpc.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        var language = languageRepository.findById(languageCode)
            .orElseThrow(() -> new AppException(Status.Code.NOT_FOUND, LanguageErrorConstants.LANGUAGE_CODE_NOT_FOUND_MESSAGE));
        return languageMapper.toLanguageResponseDto(language);
    }

    @Override
    public List<LanguageResponseDto> findByCodes(List<String> languageCodes) {
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
