package dev.animedia.contentservice.language.service.impl;

import dev.animedia.contentservice.language.dto.LanguageResponseDto;
import dev.animedia.contentservice.language.service.LanguageQueryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageQueryServiceImpl implements LanguageQueryService {

    @Override
    public LanguageResponseDto findByCode(String languageCode) {
        return null;
    }

    @Override
    public List<LanguageResponseDto> findAllByCodes(List<String> languageCodes) {
        return List.of();
    }

    @Override
    public boolean existsByCode(String languageCode) {
        return false;
    }

    @Override
    public boolean existsAnyByCodes(List<String> languageCodes) {
        return false;
    }

}
