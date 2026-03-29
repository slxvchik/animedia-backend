package dev.animedia.languageservice.application.mapper;

import dev.animedia.languageservice.application.dto.LanguageDto;
import dev.animedia.languageservice.domain.model.Language;
import org.springframework.stereotype.Component;

@Component
public class LanguageApplicationMapper {
    public Language toDomain(LanguageDto dto) {
        return new Language(
            dto.code(),
            dto.name(),
            dto.isActive(),
            dto.isDefault(),
            dto.sortOrder(),
            dto.flagEmoji()
        );
    }

    public LanguageDto toDto(Language language) {
        return new LanguageDto(
            language.getCode(),
            language.getName(),
            language.getIsActive(),
            language.getIsDefault(),
            language.getSortOrder(),
            language.getFlagEmoji()
        );
    }
}
