package dev.animedia.languageservice.infrastructure.persistence.mapper;

import dev.animedia.languageservice.domain.model.Language;
import dev.animedia.languageservice.infrastructure.persistence.model.LanguageEntity;
import org.springframework.stereotype.Component;

@Component
public class LanguagePersistenceMapper {
    public LanguageEntity toEntity(Language language) {
        LanguageEntity entity = new LanguageEntity();
        entity.setCode(language.getCode());
        entity.setName(language.getName());
        entity.setIsActive(language.getIsActive());
        entity.setIsDefault(language.getIsDefault());
        entity.setSortOrder(language.getSortOrder());
        entity.setFlagEmoji(language.getFlagEmoji());
        return entity;
    }

    public Language toDomain(LanguageEntity entity) {
        return new Language(
            entity.getCode(),
            entity.getName(),
            entity.getIsActive(),
            entity.getIsDefault(),
            entity.getSortOrder(),
            entity.getFlagEmoji()
        );
    }
}
