package dev.animedia.contentservice.infrastructure.status.persistence.repository;

import dev.animedia.contentservice.application.shared.mapper.PaginationApplicationMapper;
import dev.animedia.contentservice.domain.shared.model.Page;
import dev.animedia.contentservice.domain.shared.model.Pageable;
import dev.animedia.contentservice.domain.status.model.Status;
import dev.animedia.contentservice.domain.status.model.StatusSearchCriteria;
import dev.animedia.contentservice.domain.status.repository.StatusQueryRepository;
import dev.animedia.contentservice.infrastructure.shared.mapper.PaginationPersistenceMapper;
import dev.animedia.contentservice.infrastructure.status.persistence.dto.StatusTranslationRowDto;
import dev.animedia.contentservice.infrastructure.status.persistence.mapper.StatusPersistenceMapper;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StatusQueryRepositoryImpl implements StatusQueryRepository {
	private final StatusPersistenceMapper statusPersistenceMapper;
	private final JpaStatusRepository jpaStatusRepository;
	private final PaginationPersistenceMapper paginationPersistenceMapper;
	private final PaginationApplicationMapper paginationApplicationMapper;

	@Autowired
	public StatusQueryRepositoryImpl(
		StatusPersistenceMapper statusPersistenceMapper,
		JpaStatusRepository jpaStatusRepository,
		PaginationPersistenceMapper paginationPersistenceMapper,
		PaginationApplicationMapper paginationApplicationMapper
	) {
		this.statusPersistenceMapper = statusPersistenceMapper;
		this.jpaStatusRepository = jpaStatusRepository;
		this.paginationPersistenceMapper = paginationPersistenceMapper;
		this.paginationApplicationMapper = paginationApplicationMapper;
	}

	@Override
	public Optional<Status> findById(Long id, @Nullable String languageCode) {
		List<StatusTranslationRowDto> statusTranslationRowDtoList = jpaStatusRepository.findByIdAndLanguageCode(id, languageCode);
		return Optional.ofNullable(
			statusPersistenceMapper.toStatusList(statusTranslationRowDtoList).getFirst()
		);
	}

	@Override
	public List<Status> findByIdList(List<Long> idList, @Nullable String languageCode) {
		List<StatusTranslationRowDto> statusTranslationRowDtoList = jpaStatusRepository.findByIdListAndLanguageCode(idList, languageCode);
		return statusPersistenceMapper.toStatusList(statusTranslationRowDtoList);
	}

	@Override
	public Page<Status> search(StatusSearchCriteria criteria, Pageable pageable) {
		org.springframework.data.domain.Pageable springPageable = paginationPersistenceMapper.toPageable(pageable.page(), pageable.size());

		org.springframework.data.domain.Page<Long> statusIdSpringPage = jpaStatusRepository.search(
			criteria.alias(),
			criteria.name(),
			criteria.languageCode(),
			springPageable
		);

		// Find statuses with translation for page
		List<StatusTranslationRowDto> statusTranslationRowDtoList = jpaStatusRepository.findByIdListAndLanguageCode(
			statusIdSpringPage.getContent(),
			criteria.languageCode()
		);

		List<Status> statusList = statusPersistenceMapper.toStatusList(statusTranslationRowDtoList);

		Page<Long> statusIdDomainPage = paginationPersistenceMapper.toDomainPage(statusIdSpringPage);

		return paginationApplicationMapper.changeContent(statusIdDomainPage, statusList);
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
