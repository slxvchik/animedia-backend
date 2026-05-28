package dev.animedia.contentservice.infrastructure.persistence.content.repository;

import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.model.ContentType;
import dev.animedia.contentservice.domain.content.repository.ContentQueryRepository;
import dev.animedia.contentservice.infrastructure.persistence.content.mapper.ContentPersistenceMapper;
import dev.animedia.contentservice.infrastructure.persistence.genre.mapper.GenrePersistenceMapper;
import dev.animedia.contentservice.infrastructure.persistence.status.mapper.StatusPersistenceMapper;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class ContentQueryRepositoryImpl implements ContentQueryRepository {
	private final JpaContentRepository jpaContentRepository;
	private final ContentPersistenceMapper contentPersistenceMapper;
	private final StatusPersistenceMapper statusPersistenceMapper;
	private final GenrePersistenceMapper genrePersistenceMapper;

	@Autowired
	public ContentQueryRepositoryImpl(
		JpaContentRepository jpaContentRepository,
		ContentPersistenceMapper contentPersistenceMapper,
		StatusPersistenceMapper statusPersistenceMapper,
		GenrePersistenceMapper genrePersistenceMapper
	) {
		this.jpaContentRepository = jpaContentRepository;
        this.contentPersistenceMapper = contentPersistenceMapper;
		this.statusPersistenceMapper = statusPersistenceMapper;
		this.genrePersistenceMapper = genrePersistenceMapper;
	}

	@Override
	public Optional<Content> find(UUID id, @Nullable String languageCode, @Nullable Boolean active) {
		return jpaContentRepository.findById(id, languageCode, active)
			.map(contentEntity -> contentPersistenceMapper.toContent(
				contentEntity,
				statusPersistenceMapper::toStatus,
				genrePersistenceMapper::toGenre
			));
	}

	@Override
	public Optional<Content> find(
		String alias,
		ContentType type,
		int season,
		@Nullable String languageCode,
		@Nullable Boolean active
	) {
		return jpaContentRepository.findByAliasAndTypeAndSeason(alias, type, season, languageCode, active)
			.map(contentEntity -> contentPersistenceMapper.toContent(
				contentEntity,
				statusPersistenceMapper::toStatus,
				genrePersistenceMapper::toGenre
			));
	}

	@Override
	public boolean exists(String alias, ContentType type, int season) {
		return jpaContentRepository.existsByAliasAndContentTypeAndSeason(alias, type, season);
	}
}
