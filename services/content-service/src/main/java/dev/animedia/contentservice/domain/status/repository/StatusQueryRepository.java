package dev.animedia.contentservice.domain.status.repository;

import dev.animedia.contentservice.domain.shared.model.Page;
import dev.animedia.contentservice.domain.shared.model.Pageable;
import dev.animedia.contentservice.domain.status.model.Status;
import dev.animedia.contentservice.domain.status.model.StatusSearchCriteria;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.Optional;

public interface StatusQueryRepository {
    Optional<Status> findById(Long id, boolean onlyActive, @Nullable String languageCode);
    List<Status> findByIdList(List<Long> idList, boolean onlyActive, @Nullable String languageCode);

    Page<Status> search(StatusSearchCriteria criteria, Pageable pageable);

    boolean existsByAlias(String alias);
    boolean existsByAliasExcludeId(String alias, Long id);
}