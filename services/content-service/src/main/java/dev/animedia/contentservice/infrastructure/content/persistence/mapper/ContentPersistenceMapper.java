package dev.animedia.contentservice.infrastructure.content.persistence.mapper;

import dev.animedia.contentservice.domain.content.model.ContentTranslation;
import dev.animedia.contentservice.infrastructure.content.persistence.model.ContentTranslationEntity;
import org.springframework.stereotype.Component;

@Component
public class ContentPersistenceMapper {
    public ContentTranslation toContentTranslation(ContentTranslationEntity cte) {
        return new ContentTranslation(
            cte.getId(),
            cte.getLanguageCode(),
            cte.getTitle(),
            cte.getDescription()
        );
    }
}
