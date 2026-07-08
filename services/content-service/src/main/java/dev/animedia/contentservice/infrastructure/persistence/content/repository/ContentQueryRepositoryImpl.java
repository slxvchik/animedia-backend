package dev.animedia.contentservice.infrastructure.persistence.content.repository;

import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.model.ContentType;
import dev.animedia.contentservice.domain.content.repository.ContentQueryRepository;
import dev.animedia.contentservice.domain.shared.pagination.Page;
import dev.animedia.contentservice.domain.shared.pagination.Pageable;
import dev.animedia.contentservice.infrastructure.persistence.content.mapper.ContentPersistenceMapper;
import dev.animedia.contentservice.infrastructure.persistence.content.model.ContentEntity;
import dev.animedia.contentservice.infrastructure.persistence.genre.mapper.GenrePersistenceMapper;
import dev.animedia.contentservice.infrastructure.persistence.shared.mapper.PaginationPersistenceMapper;
import dev.animedia.contentservice.infrastructure.persistence.status.mapper.StatusPersistenceMapper;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ContentQueryRepositoryImpl implements ContentQueryRepository {
	private final JpaContentRepository jpaContentRepository;
	private final ContentPersistenceMapper contentPersistenceMapper;
	private final StatusPersistenceMapper statusPersistenceMapper;
	private final GenrePersistenceMapper genrePersistenceMapper;
	private final PaginationPersistenceMapper paginationPersistenceMapper;

	@Autowired
	public ContentQueryRepositoryImpl(
		JpaContentRepository jpaContentRepository,
		ContentPersistenceMapper contentPersistenceMapper,
		StatusPersistenceMapper statusPersistenceMapper,
		GenrePersistenceMapper genrePersistenceMapper,
		PaginationPersistenceMapper paginationPersistenceMapper
	) {
		this.jpaContentRepository = jpaContentRepository;
        this.contentPersistenceMapper = contentPersistenceMapper;
		this.statusPersistenceMapper = statusPersistenceMapper;
		this.genrePersistenceMapper = genrePersistenceMapper;
		this.paginationPersistenceMapper = paginationPersistenceMapper;
	}

	@Override
	public Optional<Content> find(UUID id, @Nullable String languageCode) {
		return jpaContentRepository.findById(id, languageCode)
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
		@Nullable String languageCode
	) {
		return jpaContentRepository.findByAliasAndTypeAndSeason(alias, type, season, languageCode)
			.map(contentEntity -> contentPersistenceMapper.toContent(
				contentEntity,
				statusPersistenceMapper::toStatus,
				genrePersistenceMapper::toGenre
			));
	}

	@Override
	public Page<Content> findAll(Pageable pageable) {
		org.springframework.data.domain.Pageable springPageable = paginationPersistenceMapper.toPageable(
			pageable.page(),
			pageable.size()
		);

		org.springframework.data.domain.Page<ContentEntity> contentEntitySpringPage = jpaContentRepository.findAll(springPageable);

		List<Content> contentList = contentEntitySpringPage.getContent()
			.stream()
			.map(contentEntity -> contentPersistenceMapper.toContent(
				contentEntity,
				statusPersistenceMapper::toStatus,
				genrePersistenceMapper::toGenre
			))
			.toList();

		Page<ContentEntity> contentEntityDomainPage = paginationPersistenceMapper.toDomainPage(contentEntitySpringPage);

		return contentEntityDomainPage.changeContent(contentList);
	}

	@Override
	public boolean exists(String alias, ContentType type, int season) {
		return jpaContentRepository.existsByAliasAndContentTypeAndSeason(alias, type, season);
	}
}
