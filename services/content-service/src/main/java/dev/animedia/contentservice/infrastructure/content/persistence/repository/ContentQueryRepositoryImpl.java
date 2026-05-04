package dev.animedia.contentservice.infrastructure.content.persistence.repository;

import dev.animedia.contentservice.application.status.exception.StatusNotFoundException;
import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.model.ContentTranslation;
import dev.animedia.contentservice.domain.content.model.ContentType;
import dev.animedia.contentservice.domain.content.repository.ContentQueryRepository;
import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.genre.repository.GenreQueryRepository;
import dev.animedia.contentservice.domain.status.model.Status;
import dev.animedia.contentservice.domain.status.repository.StatusQueryRepository;
import dev.animedia.contentservice.infrastructure.content.persistence.mapper.ContentPersistenceMapper;
import dev.animedia.contentservice.infrastructure.content.persistence.model.ContentEntity;
import dev.animedia.contentservice.infrastructure.genre.persistence.model.GenreEntity;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ContentQueryRepositoryImpl implements ContentQueryRepository {
	private final JpaContentRepository jpaContentRepository;
	private final GenreQueryRepository genreQueryService;
	private final StatusQueryRepository statusQueryRepository;
	private final ContentPersistenceMapper contentPersistenceMapper;

	@Autowired
	public ContentQueryRepositoryImpl(
		JpaContentRepository jpaContentRepository,
		GenreQueryRepository genreQueryService,
		StatusQueryRepository statusQueryRepository,
		ContentPersistenceMapper contentPersistenceMapper
    ) {
		this.jpaContentRepository = jpaContentRepository;
		this.genreQueryService = genreQueryService;
		this.statusQueryRepository = statusQueryRepository;
        this.contentPersistenceMapper = contentPersistenceMapper;
    }

	@Override
	public Optional<Content> find(UUID id, @Nullable String languageCode) {
		return jpaContentRepository.findById(id, languageCode)
			.map(contentEntity -> mapToContent(contentEntity, languageCode));
	}

	@Override
	public Optional<Content> find(
		String alias,
		ContentType type,
		@Nullable Integer season,
		@Nullable String languageCode
	) {
		return jpaContentRepository.findByAliasAndTypeAndSeason(alias, type, season, languageCode)
			.map(contentEntity -> mapToContent(contentEntity, languageCode));
	}

	private Content mapToContent(ContentEntity contentEntity, @Nullable String languageCode) {
		List<Long> genreIdList = contentEntity.getGenreSet().stream()
			.map(GenreEntity::getId)
			.toList();
		Set<Genre> genreSet = new HashSet<>(genreQueryService.findByIdList(genreIdList, languageCode));

		Long statusId = contentEntity.getStatusEntity().getId();
		Status status = statusQueryRepository.findById(statusId, languageCode)
			.orElseThrow(StatusNotFoundException::new);

		Set<ContentTranslation> translationSet = contentEntity.getTranslationSet().stream()
			.map(contentPersistenceMapper::toContentTranslation)
			.collect(Collectors.toSet());

		return new Content.Builder()
			.id(contentEntity.getId())
			.alias(contentEntity.getAlias())
			.type(contentEntity.getContentType())
			.season(contentEntity.getSeason())
			.status(status)
			.coverUrl(contentEntity.getCoverUrl())
			.trailerUrl(contentEntity.getTrailerUrl())
			.releaseDate(contentEntity.getReleaseDate())
			.createdAt(contentEntity.getCreatedAt())
			.updatedAt(contentEntity.getUpdatedAt())
			.active(contentEntity.getActive())
			.sort(contentEntity.getSortOrder())
			.languageCodeSet(contentEntity.getLanguageCodeSet())
			.genreSet(genreSet)
			.translationSet(translationSet)
			.build();
	}

	@Override
	public boolean exists(String alias, ContentType type, Integer season) {
		return jpaContentRepository.existsByAliasAndContentTypeAndSeason(alias, type, season);
	}
}
