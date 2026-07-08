package dev.animedia.contentservice.domain.status.repository;

import dev.animedia.contentservice.domain.shared.pagination.Page;
import dev.animedia.contentservice.domain.shared.pagination.Pageable;
import dev.animedia.contentservice.domain.status.model.Status;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StatusQueryRepository {
	Optional<Status> findById(UUID id, @Nullable Boolean active, @Nullable String languageCode);

	List<Status> findByIdList(List<UUID> idList, @Nullable Boolean active, @Nullable String languageCode);

	Page<Status> findAll(Pageable pageable);

	boolean existsByAlias(String alias);
}