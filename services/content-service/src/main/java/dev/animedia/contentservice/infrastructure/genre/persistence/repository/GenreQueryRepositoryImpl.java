package dev.animedia.contentservice.infrastructure.genre.persistence.repository;

import dev.animedia.contentservice.application.shared.mapper.PaginationApplicationMapper;
import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.genre.model.GenreSearchCriteria;
import dev.animedia.contentservice.domain.genre.repository.GenreQueryRepository;
import dev.animedia.contentservice.domain.shared.model.Page;
import dev.animedia.contentservice.domain.shared.model.Pageable;
import dev.animedia.contentservice.infrastructure.genre.persistence.dto.GenreTranslationRowDto;
import dev.animedia.contentservice.infrastructure.genre.persistence.mapper.GenrePersistenceMapper;
import dev.animedia.contentservice.infrastructure.shared.mapper.PaginationPersistenceMapper;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GenreQueryRepositoryImpl implements GenreQueryRepository {
    private final GenrePersistenceMapper genrePersistenceMapper;
    private final PaginationPersistenceMapper paginationPersistenceMapper;
    private final JpaGenreRepository jpaGenreRepository;
    private final PaginationApplicationMapper paginationApplicationMapper;

    @Autowired
    public GenreQueryRepositoryImpl(
        GenrePersistenceMapper genrePersistenceMapper,
        PaginationPersistenceMapper paginationPersistenceMapper,
        JpaGenreRepository jpaGenreRepository,
        PaginationApplicationMapper paginationApplicationMapper
    ) {
        this.genrePersistenceMapper = genrePersistenceMapper;
	    this.paginationPersistenceMapper = paginationPersistenceMapper;
	    this.jpaGenreRepository = jpaGenreRepository;
        this.paginationApplicationMapper = paginationApplicationMapper;
    }

    @Override
    public Optional<Genre> findById(Long id, @Nullable String languageCode) {
        List<GenreTranslationRowDto> dbRowList = jpaGenreRepository.findById(id, languageCode);
        return Optional.ofNullable(
            genrePersistenceMapper.toGenreList(dbRowList).getFirst()
        );
    }

    @Override
    public List<Genre> findByIdList(List<Long> idList, @Nullable String languageCode) {
        List<GenreTranslationRowDto> dbRowList = jpaGenreRepository.findByIdListAndLanguageCode(idList, languageCode);
        return genrePersistenceMapper.toGenreList(dbRowList);
    }

    @Override
    public Page<Genre> search(GenreSearchCriteria genreSearchCriteria, Pageable pageable) {
        org.springframework.data.domain.Pageable springPageable = paginationPersistenceMapper.toPageable();

        org.springframework.data.domain.Page<Long> genreIdSpringPage = jpaGenreRepository.search(
            genreSearchCriteria.alias(),
            genreSearchCriteria.name(),
            genreSearchCriteria.description(),
            genreSearchCriteria.languageCode(),
            springPageable
        );

        List<GenreTranslationRowDto> genreTranslationRowDtoList = jpaGenreRepository.findByIdListAndLanguageCode(
            genreIdSpringPage.getContent(),
            genreSearchCriteria.languageCode()
        );

        List<Genre> genreList = genrePersistenceMapper.toGenreList(genreTranslationRowDtoList);

        return paginationApplicationMapper.changeContent(genreIdSpringPage, genreList);
    }

    @Override
    public boolean existsByAlias(String alias) {
        return jpaGenreRepository.existsByAlias(alias);
    }

    @Override
    public boolean existsByAliasExcludeId(String alias, Long id) {
        return jpaGenreRepository.existsByAliasAndIdNot(alias, id);
    }
}
