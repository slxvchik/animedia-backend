package dev.animedia.contentservice.domain.content.repository;

import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.model.ContentType;
import dev.animedia.contentservice.domain.shared.pagination.Page;
import dev.animedia.contentservice.domain.shared.pagination.Pageable;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContentQueryRepository {
	Optional<Content> find(
		UUID id,
		@Nullable
		String languageCode
	);

	Optional<Content> find(
		String alias,
		ContentType type,
		int season,
		@Nullable
		String languageCode
	);

	List<Content> find(
		List<UUID> idList,
		@Nullable
		String languageCode
	);

	Page<Content> findAll(Pageable pageable);

	boolean exists(String alias, ContentType type, int season);
}