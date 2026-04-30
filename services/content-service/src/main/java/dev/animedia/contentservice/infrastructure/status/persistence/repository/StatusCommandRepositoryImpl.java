package dev.animedia.contentservice.infrastructure.status.persistence.repository;

import dev.animedia.contentservice.application.status.exception.StatusNotFoundException;
import dev.animedia.contentservice.domain.status.model.Status;
import dev.animedia.contentservice.domain.status.repository.StatusCommandRepository;
import dev.animedia.contentservice.infrastructure.status.persistence.mapper.StatusPersistenceMapper;
import dev.animedia.contentservice.infrastructure.status.persistence.model.StatusEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StatusCommandRepositoryImpl implements StatusCommandRepository {
	private final JpaStatusQueryRepository jpaStatusQueryRepository;
	private final StatusPersistenceMapper statusPersistenceMapper;

	@Autowired
	public StatusCommandRepositoryImpl(
		JpaStatusQueryRepository jpaStatusQueryRepository,
		StatusPersistenceMapper statusPersistenceMapper
	) {
		this.jpaStatusQueryRepository = jpaStatusQueryRepository;
		this.statusPersistenceMapper = statusPersistenceMapper;
	}

	@Override
	public Status create(Status status) {
		StatusEntity statusEntity = statusPersistenceMapper.toStatusEntity(status);
		StatusEntity saved = jpaStatusQueryRepository.save(statusEntity);
		return statusPersistenceMapper.toStatus(saved);
	}

	@Override
	public Status update(Status status) {
		StatusEntity statusEntity = jpaStatusQueryRepository.findById(status.getId())
			.orElseThrow(StatusNotFoundException::new);

		/**
		 * Сначала убираем те
		 */

		return null;
	}

	@Override
	public void delete(Long id) {

	}
}
