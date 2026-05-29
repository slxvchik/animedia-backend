package dev.animedia.contentservice.infrastructure.persistence.genre.repository;

import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.genre.repository.GenreCommandRepository;
import dev.animedia.contentservice.infrastructure.persistence.genre.mapper.GenrePersistenceMapper;
import dev.animedia.contentservice.infrastructure.persistence.genre.model.GenreEntity;
import dev.animedia.contentservice.infrastructure.persistence.genre.model.GenreTranslationEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class GenreCommandRepositoryImpl implements GenreCommandRepository {
    private final GenrePersistenceMapper genrePersistenceMapper;
    private final JpaGenreRepository jpaGenreRepository;

    @Autowired
    public GenreCommandRepositoryImpl(
        GenrePersistenceMapper genrePersistenceMapper,
        JpaGenreRepository jpaGenreRepository
    ) {
        this.genrePersistenceMapper = genrePersistenceMapper;
        this.jpaGenreRepository = jpaGenreRepository;
    }

    @Override
    public Genre create(Genre genre) {
        GenreEntity genreEntity = genrePersistenceMapper.toGenreEntity(genre);
        GenreEntity saved = jpaGenreRepository.save(genreEntity);
        return genrePersistenceMapper.toGenre(saved);
    }

    @Override
    public Genre update(Genre genre) {
        GenreEntity genreEntity = jpaGenreRepository.findById(genre.getId())
            .orElseThrow(EntityNotFoundException::new);

        genreEntity.setAlias(genre.getAlias());
        genreEntity.setSortOrder(genre.getSortOrder());

        Set<GenreTranslationEntity> newTranslationSet = genre.getTranslationSet().stream()
            .map(gt -> genrePersistenceMapper.toGenreTranslationEntity(gt, genreEntity))
            .collect(Collectors.toSet());

        genreEntity.syncTranslationSet(newTranslationSet);

        GenreEntity saved = jpaGenreRepository.save(genreEntity);

        return genrePersistenceMapper.toGenre(saved);
    }

    @Override
    public void delete(UUID id) {
        jpaGenreRepository.deleteById(id);
    }
}
