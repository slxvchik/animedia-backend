package dev.animedia.contentservice.domain.genre.repository;

import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.shared.model.Page;
import dev.animedia.contentservice.domain.shared.model.Pageable;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.Optional;

public interface GenreQueryRepository {
    Optional<Genre> findById(Long id, @Nullable String languageCode);
    List<Genre> findByIdList(List<Long> idList, @Nullable String languageCode);
    Page<Genre> search(@Nullable List<String> aliasList, @Nullable List<String> nameList, @Nullable String description, @Nullable List<String> languageCodeList, Pageable pageable);
}
