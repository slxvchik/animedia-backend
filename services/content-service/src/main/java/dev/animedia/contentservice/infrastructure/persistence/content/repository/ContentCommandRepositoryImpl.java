package dev.animedia.contentservice.infrastructure.persistence.content.repository;

import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.repository.ContentCommandRepository;
import dev.animedia.contentservice.infrastructure.persistence.content.exception.ContentCreateException;
import dev.animedia.contentservice.infrastructure.persistence.content.mapper.ContentPersistenceMapper;
import dev.animedia.contentservice.infrastructure.persistence.content.model.ContentEntity;
import dev.animedia.contentservice.infrastructure.persistence.content.model.ContentTranslationEntity;
import dev.animedia.contentservice.infrastructure.persistence.genre.mapper.GenrePersistenceMapper;
import dev.animedia.contentservice.infrastructure.persistence.genre.model.GenreEntity;
import dev.animedia.contentservice.infrastructure.persistence.status.mapper.StatusPersistenceMapper;
import dev.animedia.contentservice.infrastructure.persistence.status.model.StatusEntity;
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
	public Content create(Content content) {
		ContentEntity ce = contentPersistenceMapper.toContentEntity(
			content,
			statusPersistenceMapper::toStatusEntity,
			genrePersistenceMapper::toGenreEntity
		);

		ContentEntity saved = jpaContentRepository.saveAndFlush(ce);

		// fetch with all relations
		ContentEntity savedResponse = jpaContentRepository.findById(saved.getId(), false, null)
			.orElseThrow(ContentCreateException::new);

		return contentPersistenceMapper.toContent(
			savedResponse,
			statusPersistenceMapper::toStatus,
			genrePersistenceMapper::toGenre
		);
	}

	@Override
	public Content update(Content content) {
		ContentEntity contentEntity = jpaContentRepository.findById(content.getId(), false, null)
			.orElseThrow(EntityNotFoundException::new);

		StatusEntity statusEntity = statusPersistenceMapper.toStatusEntity(content.getStatus());
		contentEntity.setStatusEntity(statusEntity);

		contentEntity.setCoverImageId(content.getCoverImageId());
		contentEntity.setTrailerVideoId(content.getTrailerVideoId());
		contentEntity.setReleaseDate(content.getReleaseDate());
		contentEntity.setActive(content.getActive());
		contentEntity.setSortOrder(content.getSort());

		Set<GenreEntity> genreEntitySet = content.getGenreSet() == null ? null
			: content.getGenreSet()
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

		ContentEntity updated = jpaContentRepository.saveAndFlush(contentEntity);

		return contentPersistenceMapper.toContent(
			updated,
			statusPersistenceMapper::toStatus,
			genrePersistenceMapper::toGenre
		);
	}

	@Override
	public void delete(UUID id) {
		jpaContentRepository.deleteById(id);
	}
}