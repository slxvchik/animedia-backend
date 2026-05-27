package dev.animedia.contentservice.infrastructure.persistence.genre.repository;

import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.genre.model.GenreSearchCriteria;
import dev.animedia.contentservice.domain.genre.repository.GenreQueryRepository;
import dev.animedia.contentservice.domain.shared.model.Page;
import dev.animedia.contentservice.domain.shared.model.Pageable;
import dev.animedia.contentservice.infrastructure.persistence.genre.mapper.GenrePersistenceMapper;
import dev.animedia.contentservice.infrastructure.persistence.genre.model.GenreEntity;
import dev.animedia.contentservice.infrastructure.persistence.shared.mapper.PaginationPersistenceMapper;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    public Optional<Genre> findById(Long id, @Nullable Boolean active, @Nullable String languageCode) {
        GenreEntity genreEntity = jpaGenreRepository.findById(id, languageCode, active);
        return Optional.ofNullable(
            genrePersistenceMapper.toGenre(genreEntity)
        );
    }

    @Override
    public List<Genre> findByIdList(List<Long> idList, @Nullable Boolean active, @Nullable String languageCode) {
        List<GenreEntity> genreEntityList = jpaGenreRepository.findByIdList(idList, languageCode, active);
        return genreEntityList.stream()
            .map(genrePersistenceMapper::toGenre)
            .toList();
    }

    @Override
    public Page<Genre> search(GenreSearchCriteria genreSearchCriteria, Pageable pageable) {
        org.springframework.data.domain.Pageable springPageable = paginationPersistenceMapper.toPageable(
            pageable.page(),
            pageable.size(),
            pageable.sortField(),
            pageable.sortDirection()
        );

        org.springframework.data.domain.Page<GenreEntity> genreEntitySpringPage = jpaGenreRepository.search(
            genreSearchCriteria.active(),
            genreSearchCriteria.alias(),
            genreSearchCriteria.name(),
            genreSearchCriteria.description(),
            genreSearchCriteria.languageCode(),
            springPageable
        );

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
