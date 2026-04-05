package dev.animedia.contentservice.domain.content.repository;

import dev.animedia.contentservice.domain.content.model.ContentTranslation;

import java.util.UUID;

public interface ContentTranslationCommandRepository {
    ContentTranslation create(ContentTranslation translation);
    ContentTranslation update(ContentTranslation translation);
    void delete(UUID uuid);
}
