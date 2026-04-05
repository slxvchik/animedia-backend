package dev.animedia.contentservice.domain.genre.repository;

import dev.animedia.contentservice.domain.genre.model.Genre;

public interface GenreCommandRepository {
    Genre create(Genre genre);
    Genre update(Genre genre);
    void delete(Long id);
}
