package dev.animedia.contentservice.status.domain.repository;

import dev.animedia.contentservice.shared.domain.pagination.Page;
import dev.animedia.contentservice.shared.domain.pagination.Pageable;
import dev.animedia.contentservice.status.domain.model.Status;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StatusQueryRepository {
	Optional<Status> findById(UUID id, @Nullable String languageCode);

	List<Status> findByIdList(List<UUID> idList, @Nullable String languageCode);

	Page<Status> findAll(Pageable pageable);

	boolean existsByAlias(String alias);
}