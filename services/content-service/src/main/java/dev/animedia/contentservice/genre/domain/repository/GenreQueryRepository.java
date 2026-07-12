package dev.animedia.contentservice.genre.domain.repository;

import dev.animedia.contentservice.genre.domain.model.Genre;
import dev.animedia.contentservice.shared.domain.pagination.Page;
import dev.animedia.contentservice.shared.domain.pagination.Pageable;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GenreQueryRepository {
	Optional<Genre> findById(
		UUID id,
		@Nullable
		String languageCode
	);

	List<Genre> findByIdList(
		List<UUID> idList,
		@Nullable
		String languageCode
	);

	Page<Genre> findAll(Pageable pageable);

	boolean existsByAlias(String alias);
}