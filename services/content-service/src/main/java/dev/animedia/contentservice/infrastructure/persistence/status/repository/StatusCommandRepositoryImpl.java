package dev.animedia.contentservice.infrastructure.persistence.status.repository;

import dev.animedia.contentservice.domain.status.model.Status;
import dev.animedia.contentservice.domain.status.repository.StatusCommandRepository;
import dev.animedia.contentservice.infrastructure.persistence.status.mapper.StatusPersistenceMapper;
import dev.animedia.contentservice.infrastructure.persistence.status.model.StatusEntity;
import dev.animedia.contentservice.infrastructure.persistence.status.model.StatusTranslationEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class StatusCommandRepositoryImpl implements StatusCommandRepository {
	private final JpaStatusRepository jpaStatusRepository;
	private final StatusPersistenceMapper statusPersistenceMapper;

	@Autowired
	public StatusCommandRepositoryImpl(
		JpaStatusRepository jpaStatusRepository,
		StatusPersistenceMapper statusPersistenceMapper
	) {
		this.jpaStatusRepository = jpaStatusRepository;
		this.statusPersistenceMapper = statusPersistenceMapper;
	}

	@Override
	public Status create(Status status) {
		StatusEntity statusEntity = statusPersistenceMapper.toStatusEntity(status);
		StatusEntity saved = jpaStatusRepository.save(statusEntity);
		return statusPersistenceMapper.toStatus(saved);
	}

	@Override
	public Status update(Status status) {
		StatusEntity statusEntity = jpaStatusRepository.findById(status.getId())
			.orElseThrow(EntityNotFoundException::new);

		statusEntity.setAlias(status.getAlias());
		statusEntity.setSortOrder(status.getSortOrder());

		Set<StatusTranslationEntity> newTranslationEntitySet = status.getTranslationSet().stream()
			.map(dto -> statusPersistenceMapper.toStatusTranslationEntity(dto, statusEntity))
			.collect(Collectors.toSet());

		statusEntity.syncTranslationSet(newTranslationEntitySet);

		StatusEntity saved = jpaStatusRepository.save(statusEntity);

		return statusPersistenceMapper.toStatus(saved);
	}

	@Override
	public void delete(Long id) {
		jpaStatusRepository.deleteById(id);
	}
}
