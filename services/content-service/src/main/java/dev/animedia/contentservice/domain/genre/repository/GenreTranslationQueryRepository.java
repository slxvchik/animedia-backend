package dev.animedia.contentservice.domain.genre.repository;

import dev.animedia.contentservice.domain.genre.model.GenreTranslation;
import dev.animedia.contentservice.domain.shared.model.Page;
import dev.animedia.contentservice.domain.shared.model.Pageable;
import jakarta.annotation.Nullable;

import java.util.List;

public interface GenreTranslationQueryRepository {
    List<GenreTranslation> findByIdList(List<Long> idList);
    Page<GenreTranslation> search(
        @Nullable List<String> languageCodeList,
        @Nullable List<String> nameList,
        Pageable pageable
    );
}
