package dev.animedia.contentservice.infrastructure.status.persistence.repository;

import dev.animedia.contentservice.domain.shared.model.Page;
import dev.animedia.contentservice.domain.shared.model.Pageable;
import dev.animedia.contentservice.domain.status.model.Status;
import dev.animedia.contentservice.domain.status.model.StatusSearchCriteria;
import dev.animedia.contentservice.domain.status.repository.StatusQueryRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StatusQueryRepositoryImpl implements StatusQueryRepository {
	private final JpaStatusQueryRepository jpaStatusQueryRepository;

	@Autowired
	public StatusQueryRepositoryImpl(JpaStatusQueryRepository jpaStatusQueryRepository) {
		this.jpaStatusQueryRepository = jpaStatusQueryRepository;
	}

	@Override
	public Optional<Status> findById(Long id, @Nullable String languageCode) {
		return Optional.empty();
	}

	@Override
	public List<Status> findByIdList(List<Long> idList, @Nullable String languageCode) {
		return List.of();
	}

	@Override
	public Page<Status> search(StatusSearchCriteria criteria, Pageable pageable) {
		return null;
	}

	@Override
	public boolean existsByAlias(String alias) {
		return jpaStatusQueryRepository.existsByAlias(alias);
	}

	@Override
	public boolean existsByAliasExcludeId(String alias, Long id) {
		return jpaStatusQueryRepository.existsByAliasAndIdNot(alias, id);
	}
}
