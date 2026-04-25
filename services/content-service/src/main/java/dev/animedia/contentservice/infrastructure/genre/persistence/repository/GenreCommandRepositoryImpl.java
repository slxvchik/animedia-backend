package dev.animedia.contentservice.infrastructure.genre.persistence.repository;

import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.genre.repository.GenreCommandRepository;
import org.springframework.stereotype.Repository;

@Repository
public class GenreCommandRepositoryImpl implements GenreCommandRepository {
    @Override
    public Genre create(Genre genre) {
        return null;
    }

    @Override
    public Genre update(Genre genre) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
