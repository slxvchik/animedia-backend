package dev.animedia.contentservice.infrastructure.persistence.content.repository;

import dev.animedia.contentservice.application.content.exception.ContentNotFoundException;
import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.repository.ContentCommandRepository;
import dev.animedia.contentservice.infrastructure.persistence.content.mapper.ContentPersistenceMapper;
import dev.animedia.contentservice.infrastructure.persistence.content.model.ContentEntity;
import dev.animedia.contentservice.infrastructure.persistence.genre.mapper.GenrePersistenceMapper;
import dev.animedia.contentservice.infrastructure.persistence.genre.model.GenreEntity;
import dev.animedia.contentservice.infrastructure.persistence.status.mapper.StatusPersistenceMapper;
import dev.animedia.contentservice.infrastructure.persistence.status.model.StatusEntity;
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
		StatusEntity se = statusPersistenceMapper.toStatusEntity(content.getStatus());
		Set<GenreEntity> geSet = content.getGenreSet() == null ? null :
			content.getGenreSet().stream()
				.map(genrePersistenceMapper::toGenreEntity)
				.collect(Collectors.toSet());
		ContentEntity ce = contentPersistenceMapper.toContentEntity(content, se, geSet);

		ContentEntity saved = jpaContentRepository.save(ce);


		return null;
	}

	@Override
	public Content update(Content content) {
		return null;
	}

	@Override
	public void delete(UUID id) {
		jpaContentRepository.findById(id)
			.orElseThrow(ContentNotFoundException::new);
		jpaContentRepository.deleteById(id);
	}
}