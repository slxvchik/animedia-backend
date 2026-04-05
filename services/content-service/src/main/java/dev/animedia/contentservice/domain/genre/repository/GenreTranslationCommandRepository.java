package dev.animedia.contentservice.domain.genre.repository;

import dev.animedia.contentservice.domain.genre.model.GenreTranslation;

public interface GenreTranslationCommandRepository {
    GenreTranslation create(GenreTranslation translation);
    GenreTranslation update(GenreTranslation translation);
    void delete(Long id);
}
