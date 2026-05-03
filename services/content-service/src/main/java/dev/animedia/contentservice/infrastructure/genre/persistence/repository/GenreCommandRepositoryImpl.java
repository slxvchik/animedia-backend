package dev.animedia.contentservice.infrastructure.genre.persistence.repository;

import dev.animedia.contentservice.application.genre.exception.GenreNotFoundException;
import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.genre.repository.GenreCommandRepository;
import dev.animedia.contentservice.infrastructure.genre.persistence.mapper.GenrePersistenceMapper;
import dev.animedia.contentservice.infrastructure.genre.persistence.model.GenreEntity;
import dev.animedia.contentservice.infrastructure.genre.persistence.model.GenreTranslationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;
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
            .orElseThrow(GenreNotFoundException::new);

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
    public void delete(Long id) {
        jpaGenreRepository.findById(id)
            .orElseThrow(GenreNotFoundException::new);
        jpaGenreRepository.deleteById(id);
    }
}
