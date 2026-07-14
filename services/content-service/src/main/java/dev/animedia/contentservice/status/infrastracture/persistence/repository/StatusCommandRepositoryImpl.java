package dev.animedia.contentservice.status.infrastracture.persistence.repository;

import dev.animedia.contentservice.status.domain.model.Status;
import dev.animedia.contentservice.status.domain.repository.StatusCommandRepository;
import dev.animedia.contentservice.status.infrastracture.persistence.mapper.StatusPersistenceMapper;
import dev.animedia.contentservice.status.infrastracture.persistence.model.StatusEntity;
import dev.animedia.contentservice.status.infrastracture.persistence.model.StatusTranslationEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;
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
	public UUID create(Status status) {
		StatusEntity statusEntity = statusPersistenceMapper.toStatusEntity(status);
		StatusEntity saved = jpaStatusRepository.save(statusEntity);
		return saved.getId();
	}

	@Override
	public void update(Status status) {
		StatusEntity statusEntity = jpaStatusRepository.findById(status.getId())
			.orElseThrow(EntityNotFoundException::new);

		statusEntity.setSortOrder(status.getSortOrder());

		Set<StatusTranslationEntity> newTranslationEntitySet = status.getTranslations().stream()
			.map(dto -> statusPersistenceMapper.toStatusTranslationEntity(dto, statusEntity))
			.collect(Collectors.toSet());

		statusEntity.syncTranslationSet(newTranslationEntitySet);

		jpaStatusRepository.save(statusEntity);
	}

	@Override
	public void delete(UUID id) {
		jpaStatusRepository.deleteById(id);
	}
}
