package dev.animedia.contentservice.status.infrastracture.persistence.repository;

import dev.animedia.contentservice.shared.domain.pagination.Page;
import dev.animedia.contentservice.shared.domain.pagination.Pageable;
import dev.animedia.contentservice.status.domain.model.Status;
import dev.animedia.contentservice.status.domain.repository.StatusQueryRepository;
import dev.animedia.contentservice.shared.infrastructure.persistence.mapper.PaginationPersistenceMapper;
import dev.animedia.contentservice.status.infrastracture.persistence.mapper.StatusPersistenceMapper;
import dev.animedia.contentservice.status.infrastracture.persistence.model.StatusEntity;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class StatusQueryRepositoryImpl implements StatusQueryRepository {
	private final StatusPersistenceMapper statusPersistenceMapper;
	private final JpaStatusRepository jpaStatusRepository;
	private final PaginationPersistenceMapper paginationPersistenceMapper;

	@Autowired
	public StatusQueryRepositoryImpl(
		StatusPersistenceMapper statusPersistenceMapper,
		JpaStatusRepository jpaStatusRepository,
		PaginationPersistenceMapper paginationPersistenceMapper
	) {
		this.statusPersistenceMapper = statusPersistenceMapper;
		this.jpaStatusRepository = jpaStatusRepository;
		this.paginationPersistenceMapper = paginationPersistenceMapper;
	}

	@Override
	public Optional<Status> findById(UUID id, @Nullable String languageCode) {
		StatusEntity statusEntity = jpaStatusRepository.findById(id, languageCode);
		return Optional.ofNullable(
			statusPersistenceMapper.toStatus(statusEntity)
		);
	}

	@Override
	public List<Status> findByIdList(List<UUID> idList, @Nullable String languageCode) {
		List<StatusEntity> statusEntityList = jpaStatusRepository.findByIdList(idList, languageCode);
		return statusEntityList.stream()
			.map(statusPersistenceMapper::toStatus)
			.toList();
	}

	@Override
	public Page<Status> findAll(Pageable pageable) {
		org.springframework.data.domain.Pageable springPageable = paginationPersistenceMapper.toPageable(
			pageable.getPage(),
			pageable.getSize()
		);

		org.springframework.data.domain.Page<StatusEntity> statusEntitySpringPage = jpaStatusRepository.findByPageable(springPageable);

		List<Status> statusList = statusEntitySpringPage.getContent()
			.stream()
			.map(statusPersistenceMapper::toStatus)
			.toList();

		Page<StatusEntity> statusEntityDomainPage = paginationPersistenceMapper.toDomainPage(statusEntitySpringPage);

		return statusEntityDomainPage.changeContent(statusList);
	}

	@Override
	public boolean existsByAlias(String alias) {
		return jpaStatusRepository.existsByAlias(alias);
	}
}
