package dev.animedia.contentservice.domain.status.repository;

import dev.animedia.contentservice.domain.shared.model.Page;
import dev.animedia.contentservice.domain.shared.model.Pageable;
import dev.animedia.contentservice.domain.status.model.Status;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.Optional;

public interface StatusQueryRepository {
    Optional<Status> findById(Long id, @Nullable String languageCode);
    List<Status> findByIdList(List<Long> idList, @Nullable String languageCode);
    Page<Status> search(@Nullable List<String> aliasList, @Nullable List<String> nameList, @Nullable List<String> languageCodeList, Pageable pageable);
}
