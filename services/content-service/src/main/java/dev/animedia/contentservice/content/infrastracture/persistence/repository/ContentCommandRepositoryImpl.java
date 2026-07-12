package dev.animedia.contentservice.content.infrastracture.persistence.repository;

import dev.animedia.contentservice.content.domain.model.Content;
import dev.animedia.contentservice.content.domain.repository.ContentCommandRepository;
import dev.animedia.contentservice.content.infrastracture.persistence.mapper.ContentPersistenceMapper;
import dev.animedia.contentservice.content.infrastracture.persistence.model.ContentEntity;
import dev.animedia.contentservice.content.infrastracture.persistence.model.ContentTranslationEntity;
import dev.animedia.contentservice.genre.infrastracture.persistence.mapper.GenrePersistenceMapper;
import dev.animedia.contentservice.genre.infrastracture.persistence.model.GenreEntity;
import dev.animedia.contentservice.status.infrastracture.persistence.mapper.StatusPersistenceMapper;
import dev.animedia.contentservice.status.infrastracture.persistence.model.StatusEntity;
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
	private final StatusPersistenceMapper statusPersistenceMapper;
	private final GenrePersistenceMapper genrePersistenceMapper;

	@Autowired
	public ContentCommandRepositoryImpl(
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
	public UUID create(Content content) {
		StatusEntity statusEntity = statusPersistenceMapper.toStatusEntity(content.getStatusId());

		Set<GenreEntity> genreEntitySet = content.getGenreIdSet().stream()
			.map(genrePersistenceMapper::toGenreEntity)
			.collect(Collectors.toSet());

		ContentEntity ce = contentPersistenceMapper.toContentEntity(
			content,
			statusEntity,
			genreEntitySet
		);

		ContentEntity saved = jpaContentRepository.saveAndFlush(ce);

		return saved.getId();
	}

	@Override
	public void update(Content content) {
		ContentEntity contentEntity = jpaContentRepository.findById(content.getId(), null)
			.orElseThrow(EntityNotFoundException::new);

		StatusEntity statusEntity = statusPersistenceMapper.toStatusEntity(content.getStatusId());
		contentEntity.setStatusEntity(statusEntity);

		contentEntity.setCoverImageId(content.getCoverImageId());
		contentEntity.setTrailerVideoId(content.getTrailerVideoId());
		contentEntity.setReleaseDate(content.getReleaseDate());
		contentEntity.setActive(content.getActive());
		contentEntity.setSortOrder(content.getSort());

		Set<GenreEntity> genreEntitySet = content.getGenreIdSet() == null ? null
			: content.getGenreIdSet()
				.stream()
				.map(genrePersistenceMapper::toGenreEntity)
				.collect(Collectors.toSet());
		contentEntity.syncGenreSet(genreEntitySet);

		contentEntity.syncLanguageCodeSet(content.getLanguageCodeSet());

		Set<ContentTranslationEntity> contentTranslationEntitySet = content.getTranslationSet() == null ? null
			: content.getTranslationSet()
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