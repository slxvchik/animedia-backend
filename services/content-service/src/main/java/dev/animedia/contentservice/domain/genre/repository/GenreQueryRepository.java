package dev.animedia.contentservice.domain.genre.repository;

import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.genre.model.GenreSearchCriteria;
import dev.animedia.contentservice.domain.shared.pagination.Page;
import dev.animedia.contentservice.domain.shared.pagination.Pageable;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.Optional;

public interface GenreQueryRepository {
    Optional<Genre> findById(Long id, @Nullable Boolean active, @Nullable String languageCode);
    List<Genre> findByIdList(List<Long> idList, @Nullable Boolean active, @Nullable String languageCode);
    Page<Genre> search(GenreSearchCriteria genreSearchCriteria, Pageable pageable);

    boolean existsByAlias(String alias);
}