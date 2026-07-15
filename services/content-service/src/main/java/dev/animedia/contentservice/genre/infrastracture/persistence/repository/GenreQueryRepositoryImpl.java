package dev.animedia.contentservice.genre.infrastracture.persistence.repository;

import dev.animedia.contentservice.genre.domain.model.Genre;
import dev.animedia.contentservice.genre.domain.repository.GenreQueryRepository;
import dev.animedia.contentservice.shared.domain.pagination.Page;
import dev.animedia.contentservice.shared.domain.pagination.Pageable;
import dev.animedia.contentservice.genre.infrastracture.persistence.mapper.GenrePersistenceMapper;
import dev.animedia.contentservice.genre.infrastracture.persistence.model.GenreEntity;
import dev.animedia.contentservice.shared.infrastructure.persistence.mapper.PaginationPersistenceMapper;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class GenreQueryRepositoryImpl implements GenreQueryRepository {
    private final GenrePersistenceMapper genrePersistenceMapper;
    private final PaginationPersistenceMapper paginationPersistenceMapper;
    private final JpaGenreRepository jpaGenreRepository;

    @Autowired
    public GenreQueryRepositoryImpl(
        GenrePersistenceMapper genrePersistenceMapper,
        PaginationPersistenceMapper paginationPersistenceMapper,
        JpaGenreRepository jpaGenreRepository
    ) {
        this.genrePersistenceMapper = genrePersistenceMapper;
	    this.paginationPersistenceMapper = paginationPersistenceMapper;
	    this.jpaGenreRepository = jpaGenreRepository;
    }

    @Override
    public Optional<Genre> findById(UUID id, @Nullable String languageCode) {
        GenreEntity genreEntity = jpaGenreRepository.findById(id, languageCode);
        return Optional.ofNullable(
            genrePersistenceMapper.toGenre(genreEntity)
        );
    }

    @Override
    public List<Genre> findByIdList(List<UUID> idList, @Nullable String languageCode) {
        List<GenreEntity> genreEntities = jpaGenreRepository.findByIdList(idList, languageCode);
        return genreEntities.stream()
            .map(genrePersistenceMapper::toGenre)
            .toList();
    }

    @Override
    public Page<Genre> findAll(Pageable pageable) {
        org.springframework.data.domain.Pageable springPageable = paginationPersistenceMapper.toPageable(
            pageable.getPage(),
            pageable.getSize()
        );

        org.springframework.data.domain.Page<GenreEntity> genreEntitySpringPage = jpaGenreRepository.findByPageable(springPageable);

        List<Genre> genreList = genreEntitySpringPage.getContent()
            .stream()
            .map(genrePersistenceMapper::toGenre)
            .toList();

        Page<GenreEntity> genreEntityDomainPage = paginationPersistenceMapper.toDomainPage(genreEntitySpringPage);

        return genreEntityDomainPage.changeContent(genreList);
    }

    @Override
    public boolean existsByAlias(String alias) {
        return jpaGenreRepository.existsByAlias(alias);
    }
}
