package dev.animedia.contentservice.infrastructure.content.persistence.repository;

import dev.animedia.contentservice.application.status.exception.StatusNotFoundException;
import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.model.ContentType;
import dev.animedia.contentservice.domain.content.repository.ContentQueryRepository;
import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.genre.repository.GenreQueryRepository;
import dev.animedia.contentservice.domain.status.model.Status;
import dev.animedia.contentservice.domain.status.repository.StatusQueryRepository;
import dev.animedia.contentservice.infrastructure.content.persistence.model.ContentEntity;
import dev.animedia.contentservice.infrastructure.genre.persistence.model.GenreEntity;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ContentQueryRepositoryImpl implements ContentQueryRepository {
	private final JpaContentRepository jpaContentRepository;
	private final GenreQueryRepository genreQueryService;
	private final StatusQueryRepository statusQueryRepository;

	@Autowired
	public ContentQueryRepositoryImpl(
		JpaContentRepository jpaContentRepository,
		GenreQueryRepository genreQueryService,
		StatusQueryRepository statusQueryRepository
	) {
		this.jpaContentRepository = jpaContentRepository;
		this.genreQueryService = genreQueryService;
		this.statusQueryRepository = statusQueryRepository;
	}

	@Override
	public Optional<Content> find(UUID id, @Nullable String languageCode) {
		Optional<ContentEntity> optionalContentEntity = jpaContentRepository.findById(id, languageCode);
		if (optionalContentEntity.isEmpty()) return Optional.empty();

		ContentEntity contentEntity = optionalContentEntity.get();

		List<Long> genreIdList = contentEntity.getGenreSet().stream()
			.map(GenreEntity::getId)
			.toList();
		List<Genre> genreList = genreQueryService.findByIdList(genreIdList, languageCode);

		Long statusId = contentEntity.getStatusEntity().getId();
		Status status = statusQueryRepository.findById(statusId, languageCode)
			.orElseThrow(StatusNotFoundException::new);

		Content content = new Content.Builder()

			.build();

		return Optional.of();
	}

	@Override
	public Optional<Content> find(
		String alias,
		ContentType type,
		@Nullable Integer season,
		@Nullable String languageCode
	) {
		return Optional.empty();
	}

	@Override
	public boolean exists(String alias, ContentType type, @Nullable Integer season) {
		return false;
	}
}
