package dev.animedia.contentservice.infrastructure.persistence.genre.repository;

import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.genre.model.GenreSearchCriteria;
import dev.animedia.contentservice.domain.genre.repository.GenreQueryRepository;
import dev.animedia.contentservice.domain.shared.model.Page;
import dev.animedia.contentservice.domain.shared.model.Pageable;
import dev.animedia.contentservice.infrastructure.persistence.genre.dto.GenreTranslationRowDto;
import dev.animedia.contentservice.infrastructure.persistence.genre.mapper.GenrePersistenceMapper;
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
        List<GenreTranslationRowDto> genreTranslationDbRowList = jpaGenreRepository.findById(id, languageCode, active);
        return Optional.ofNullable(
            genrePersistenceMapper.toGenreList(genreTranslationDbRowList).getFirst()
        );
    }

    @Override
    public List<Genre> findByIdList(List<Long> idList, @Nullable Boolean active, @Nullable String languageCode) {
        List<GenreTranslationRowDto> genreTranslationDbRowList = jpaGenreRepository.findByIdListAndLanguageCode(idList, languageCode, active);
        return genrePersistenceMapper.toGenreList(genreTranslationDbRowList);
    }

    @Override
    public Page<Genre> search(GenreSearchCriteria genreSearchCriteria, Pageable pageable) {
        org.springframework.data.domain.Pageable springPageable = paginationPersistenceMapper.toPageable(pageable.page(), pageable.size());

        org.springframework.data.domain.Page<Long> genreIdSpringPage = jpaGenreRepository.search(
            genreSearchCriteria.active(),
            genreSearchCriteria.alias(),
            genreSearchCriteria.name(),
            genreSearchCriteria.description(),
            genreSearchCriteria.languageCode(),
            springPageable
        );

        List<GenreTranslationRowDto> genreTranslationRowDtoList = jpaGenreRepository.findByIdListAndLanguageCode(
            genreIdSpringPage.getContent(),
            genreSearchCriteria.languageCode(),
            genreSearchCriteria.active()
        );

        List<Genre> genreList = genrePersistenceMapper.toGenreList(genreTranslationRowDtoList);

        Page<Long> genreIdDomainPage = paginationPersistenceMapper.toDomainPage(genreIdSpringPage);

        return genreIdDomainPage.changeContent(genreList);
    }

    @Override
    public boolean existsByAlias(String alias) {
        return jpaGenreRepository.existsByAlias(alias);
    }
}
