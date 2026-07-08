package dev.animedia.contentservice.domain.genre.repository;

import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.shared.pagination.Page;
import dev.animedia.contentservice.domain.shared.pagination.Pageable;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GenreQueryRepository {
	Optional<Genre> findById(
		UUID id,
		@Nullable
		Boolean active,
		@Nullable
		String languageCode
	);

	List<Genre> findByIdList(
		List<UUID> idList,
		@Nullable
		Boolean active,
		@Nullable
		String languageCode
	);

	Page<Genre> findAll(Pageable pageable);

	boolean existsByAlias(String alias);
}