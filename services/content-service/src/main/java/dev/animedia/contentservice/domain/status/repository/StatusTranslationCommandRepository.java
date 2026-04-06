package dev.animedia.contentservice.domain.status.repository;

import dev.animedia.contentservice.domain.status.model.StatusTranslation;

public interface StatusTranslationCommandRepository {
    StatusTranslation create(StatusTranslation statusTranslation);
    StatusTranslation update(StatusTranslation statusTranslation);
    void delete(Long id);
}
