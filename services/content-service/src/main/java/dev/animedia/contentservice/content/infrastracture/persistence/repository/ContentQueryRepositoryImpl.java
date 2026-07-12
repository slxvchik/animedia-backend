package dev.animedia.contentservice.content.infrastracture.persistence.repository;

import dev.animedia.contentservice.content.domain.model.Content;
import dev.animedia.contentservice.content.domain.model.ContentType;
import dev.animedia.contentservice.content.domain.repository.ContentQueryRepository;
import dev.animedia.contentservice.content.infrastracture.persistence.mapper.ContentPersistenceMapper;
import dev.animedia.contentservice.content.infrastracture.persistence.model.ContentEntity;
import dev.animedia.contentservice.shared.domain.pagination.Page;
import dev.animedia.contentservice.shared.domain.pagination.Pageable;
import dev.animedia.contentservice.shared.infrastructure.persistence.mapper.PaginationPersistenceMapper;
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
	private final PaginationPersistenceMapper paginationPersistenceMapper;

	@Autowired
	public ContentQueryRepositoryImpl(
		JpaContentRepository jpaContentRepository,
		ContentPersistenceMapper contentPersistenceMapper,
		PaginationPersistenceMapper paginationPersistenceMapper
	) {
		this.jpaContentRepository = jpaContentRepository;
		this.contentPersistenceMapper = contentPersistenceMapper;
		this.paginationPersistenceMapper = paginationPersistenceMapper;
	}

	@Override
	public Optional<Content> find(UUID id, @Nullable String languageCode) {
		Optional<ContentEntity> optionalContentEntity = jpaContentRepository.findById(id, languageCode);
		if (optionalContentEntity.isEmpty()) return Optional.empty();
		ContentEntity ce = optionalContentEntity.get();
		return Optional.of(contentPersistenceMapper.toContent(ce));
	}

	@Override
	public Optional<Content> find(
		String alias,
		ContentType type,
		int season,
		@Nullable String languageCode
	) {
		Optional<ContentEntity> optionalContentEntity = jpaContentRepository.findByAliasAndTypeAndSeason(alias, type, season, languageCode);
		if (optionalContentEntity.isEmpty()) return Optional.empty();
		ContentEntity ce = optionalContentEntity.get();
		return Optional.of(contentPersistenceMapper.toContent(ce));
	}

	@Override
	public List<Content> find(List<UUID> idList, @Nullable String languageCode) {
		List<ContentEntity> ceList = jpaContentRepository.findByIdListAndLanguageCode(idList, languageCode);
		return ceList.stream()
			.map(contentPersistenceMapper::toContent).toList();
	}

	@Override
	public Page<Content> findAll(Pageable pageable) {
		org.springframework.data.domain.Pageable springPageable = paginationPersistenceMapper.toPageable(
			pageable.getPage(),
			pageable.getSize()
		);

		org.springframework.data.domain.Page<ContentEntity> contentEntitySpringPage = jpaContentRepository.findAll(springPageable);

		List<Content> contentList = contentEntitySpringPage.getContent().stream()
			.map(contentPersistenceMapper::toContent).toList();

		Page<ContentEntity> contentEntityDomainPage = paginationPersistenceMapper.toDomainPage(contentEntitySpringPage);

		return contentEntityDomainPage.changeContent(contentList);
	}

	@Override
	public boolean exists(String alias, ContentType type, int season) {
		return jpaContentRepository.existsByAliasAndContentTypeAndSeason(alias, type, season);
	}
}
