package dev.animedia.contentservice.genre.infrastracture.persistence.repository;

import dev.animedia.contentservice.genre.domain.model.Genre;
import dev.animedia.contentservice.genre.domain.repository.GenreCommandRepository;
import dev.animedia.contentservice.genre.infrastracture.persistence.mapper.GenrePersistenceMapper;
import dev.animedia.contentservice.genre.infrastracture.persistence.model.GenreEntity;
import dev.animedia.contentservice.genre.infrastracture.persistence.model.GenreTranslationEntity;
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
    public UUID create(Genre genre) {
        GenreEntity genreEntity = genrePersistenceMapper.toGenreEntity(genre);
        GenreEntity saved = jpaGenreRepository.save(genreEntity);
        return saved.getId();
    }

    @Override
    public void update(Genre genre) {
        GenreEntity genreEntity = jpaGenreRepository.findById(genre.getId())
            .orElseThrow(EntityNotFoundException::new);

        genreEntity.setAlias(genre.getAlias());
        genreEntity.setSortOrder(genre.getSortOrder());

        Set<GenreTranslationEntity> newTranslationSet = genre.getTranslationSet().stream()
            .map(gt -> genrePersistenceMapper.toGenreTranslationEntity(gt, genreEntity))
            .collect(Collectors.toSet());

        genreEntity.syncTranslationSet(newTranslationSet);

        jpaGenreRepository.save(genreEntity);
    }

    @Override
    public void delete(UUID id) {
        jpaGenreRepository.deleteById(id);
    }
}
