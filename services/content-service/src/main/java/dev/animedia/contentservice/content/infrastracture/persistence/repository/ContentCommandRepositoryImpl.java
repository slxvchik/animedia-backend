package dev.animedia.contentservice.content.infrastracture.persistence.repository;

import dev.animedia.contentservice.content.domain.model.Content;
import dev.animedia.contentservice.content.domain.repository.ContentCommandRepository;
import dev.animedia.contentservice.content.infrastracture.persistence.mapper.ContentPersistenceMapper;
import dev.animedia.contentservice.content.infrastracture.persistence.model.ContentEntity;
import dev.animedia.contentservice.content.infrastracture.persistence.model.ContentTranslationEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class ContentCommandRepositoryImpl implements ContentCommandRepository {
	private final JpaContentRepository jpaContentRepository;
	private final ContentPersistenceMapper contentPersistenceMapper;

	@Autowired
	public ContentCommandRepositoryImpl(
		JpaContentRepository jpaContentRepository,
		ContentPersistenceMapper contentPersistenceMapper
	) {
		this.jpaContentRepository = jpaContentRepository;
		this.contentPersistenceMapper = contentPersistenceMapper;
	}

	@Override
	public UUID create(Content content) {;
		ContentEntity ce = contentPersistenceMapper.toContentEntity(
			content
		);
		ContentEntity saved = jpaContentRepository.saveAndFlush(ce);
		return saved.getId();
	}

	@Override
	public void update(Content content) {
		ContentEntity contentEntity = jpaContentRepository.findById(content.getId(), null)
			.orElseThrow(EntityNotFoundException::new);

		contentEntity.setStatusId(content.getStatusId());

		contentEntity.setCoverImageId(content.getCoverImageId());
		contentEntity.setTrailerVideoId(content.getTrailerVideoId());
		contentEntity.setReleaseDate(content.getReleaseDate());
		contentEntity.setActive(content.getActive());
		contentEntity.setSortOrder(content.getSort());

		contentEntity.syncLanguageCodeSet(content.getLanguageCodes());
		contentEntity.syncGenreSet(content.getGenreIds());

		Set<ContentTranslationEntity> contentTranslationEntitySet = content.getTranslations() == null ? null
			: content.getTranslations()
				.stream()
				.map(ct -> contentPersistenceMapper.toContentTranslationEntity(ct, contentEntity))
				.collect(Collectors.toSet());
		contentEntity.syncTranslationSet(contentTranslationEntitySet);

		jpaContentRepository.saveAndFlush(contentEntity);
	}

	@Override
	public void delete(UUID id) {
		jpaContentRepository.deleteById(id);
	}
}