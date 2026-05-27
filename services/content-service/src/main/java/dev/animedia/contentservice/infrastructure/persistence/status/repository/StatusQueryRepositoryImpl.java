package dev.animedia.contentservice.infrastructure.persistence.status.repository;

import dev.animedia.contentservice.domain.shared.model.Page;
import dev.animedia.contentservice.domain.shared.model.Pageable;
import dev.animedia.contentservice.domain.status.model.Status;
import dev.animedia.contentservice.domain.status.model.StatusSearchCriteria;
import dev.animedia.contentservice.domain.status.repository.StatusQueryRepository;
import dev.animedia.contentservice.infrastructure.persistence.shared.mapper.PaginationPersistenceMapper;
import dev.animedia.contentservice.infrastructure.persistence.status.dto.StatusTranslationRowDto;
import dev.animedia.contentservice.infrastructure.persistence.status.mapper.StatusPersistenceMapper;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
	public Optional<Status> findById(Long id, @Nullable Boolean active, @Nullable String languageCode) {
		List<StatusTranslationRowDto> statusTranslationRowDtoList = jpaStatusRepository.findByIdAndLanguageCode(id, active, languageCode);
		return Optional.ofNullable(
			statusPersistenceMapper.toStatusList(statusTranslationRowDtoList).getFirst()
		);
	}

	@Override
	public List<Status> findByIdList(List<Long> idList, @Nullable Boolean active, @Nullable String languageCode) {
		List<StatusTranslationRowDto> statusTranslationRowDtoList = jpaStatusRepository.findByIdListAndLanguageCode(idList, active, languageCode);
		return statusPersistenceMapper.toStatusList(statusTranslationRowDtoList);
	}

	@Override
	public Page<Status> search(StatusSearchCriteria criteria, Pageable pageable) {
		org.springframework.data.domain.Pageable springPageable = paginationPersistenceMapper.toPageable(pageable.page(), pageable.size());

		org.springframework.data.domain.Page<Long> statusIdSpringPage = jpaStatusRepository.search(
			criteria.active(),
			criteria.alias(),
			criteria.name(),
			criteria.languageCode(),
			springPageable
		);

		// Find statuses with translation for page
		List<StatusTranslationRowDto> statusTranslationRowDtoList = jpaStatusRepository.findByIdListAndLanguageCode(
			statusIdSpringPage.getContent(),
			criteria.active(),
			criteria.languageCode()
		);

		List<Status> statusList = statusPersistenceMapper.toStatusList(statusTranslationRowDtoList);

		Page<Long> statusIdDomainPage = paginationPersistenceMapper.toDomainPage(statusIdSpringPage);

		return statusIdDomainPage.changeContent(statusList);
	}

	@Override
	public boolean existsByAlias(String alias) {
		return jpaStatusRepository.existsByAlias(alias);
	}

	@Override
	public boolean existsByAliasExcludeId(String alias, Long id) {
		return jpaStatusRepository.existsByAliasAndIdNot(alias, id);
	}
}
