package dev.animedia.contentservice.domain.genre.repository;

import dev.animedia.contentservice.domain.genre.model.Genre;

import java.util.UUID;

public interface GenreCommandRepository {
    UUID create(Genre genre);
    void update(Genre genre);
    void delete(UUID id);
}
