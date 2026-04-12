package dev.animedia.contentservice.domain.genre.repository;

import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.genre.model.GenreSearchCriteria;
import dev.animedia.contentservice.domain.shared.model.Page;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.Optional;

public interface GenreQueryRepository {
    Optional<Genre> findById(Long id, @Nullable String languageCode);
    List<Genre> findByIdList(List<Long> idList, @Nullable String languageCode);
    Page<Genre> search(GenreSearchCriteria genreSearchCriteria);

    boolean existsByAlias(String alias);
    boolean existsByAliasExcludeId(String alias, Long id);
}
