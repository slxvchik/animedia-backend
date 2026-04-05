package dev.animedia.contentservice.domain.content.repository;

import dev.animedia.contentservice.domain.content.model.ContentTranslation;
import dev.animedia.contentservice.domain.shared.model.Page;
import dev.animedia.contentservice.domain.shared.model.Pageable;
import jakarta.annotation.Nullable;

import java.util.List;

public interface ContentTranslationQueryRepository {
    List<ContentTranslation> findByIdList(List<Long> idList);
    Page<ContentTranslation> search(
        @Nullable List<String> languageCodeList,
        @Nullable List<String> titleList,
        Pageable pageable
    );
}
