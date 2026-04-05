package dev.animedia.contentservice.domain.status.repository;

import dev.animedia.contentservice.domain.shared.model.Page;
import dev.animedia.contentservice.domain.shared.model.Pageable;
import dev.animedia.contentservice.domain.status.model.StatusTranslation;
import jakarta.annotation.Nullable;

import java.util.List;

public interface StatusTranslationQueryRepository {
    List<StatusTranslation> findByIdList(List<Long> idList);
    Page<StatusTranslation> search(@Nullable List<String> languageCodeList, @Nullable List<String> nameList, Pageable pageable);
}
