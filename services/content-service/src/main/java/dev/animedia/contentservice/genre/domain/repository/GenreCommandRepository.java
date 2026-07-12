package dev.animedia.contentservice.genre.domain.repository;

import dev.animedia.contentservice.genre.domain.model.Genre;

import java.util.UUID;

public interface GenreCommandRepository {
    UUID create(Genre genre);
    void update(Genre genre);
    void delete(UUID id);
}
