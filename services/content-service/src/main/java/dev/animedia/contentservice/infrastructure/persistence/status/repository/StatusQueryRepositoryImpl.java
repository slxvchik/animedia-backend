package dev.animedia.contentservice.infrastructure.persistence.status.repository;

import dev.animedia.contentservice.domain.shared.pagination.Page;
import dev.animedia.contentservice.domain.shared.pagination.Pageable;
import dev.animedia.contentservice.domain.status.model.Status;
import dev.animedia.contentservice.domain.status.model.StatusSearchCriteria;
import dev.animedia.contentservice.domain.status.repository.StatusQueryRepository;
import dev.animedia.contentservice.infrastructure.persistence.shared.mapper.PaginationPersistenceMapper;
import dev.animedia.contentservice.infrastructure.persistence.status.mapper.StatusPersistenceMapper;
import dev.animedia.contentservice.infrastructure.persistence.status.model.StatusEntity;
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
	public Optional<Status> findById(UUID id, @Nullable Boolean active, @Nullable String languageCode) {
		StatusEntity statusEntity = jpaStatusRepository.findById(id, active, languageCode);
		return Optional.ofNullable(
			statusPersistenceMapper.toStatus(statusEntity)
		);
	}

	@Override
	public List<Status> findByIdList(List<UUID> idList, @Nullable Boolean active, @Nullable String languageCode) {
		List<StatusEntity> statusEntityList = jpaStatusRepository.findByIdList(idList, active, languageCode);
		return statusEntityList.stream()
			.map(statusPersistenceMapper::toStatus)
			.toList();
	}

	@Override
	public Page<Status> search(StatusSearchCriteria criteria, Pageable pageable) {
		org.springframework.data.domain.Pageable springPageable = paginationPersistenceMapper.toPageable(
			pageable.page(),
			pageable.size(),
			pageable.sortField(),
			pageable.sortDirection()
		);

		org.springframework.data.domain.Page<StatusEntity> statusEntitySpringPage = jpaStatusRepository.search(
			criteria.active(),
			criteria.alias(),
			criteria.name(),
			criteria.languageCode(),
			springPageable
		);

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
