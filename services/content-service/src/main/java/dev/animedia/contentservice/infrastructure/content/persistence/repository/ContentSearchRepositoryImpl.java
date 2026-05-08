package dev.animedia.contentservice.infrastructure.content.persistence.repository;

import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.model.ContentSearchCriteria;
import dev.animedia.contentservice.domain.content.repository.ContentSearchRepository;
import dev.animedia.contentservice.domain.shared.model.Page;
import dev.animedia.contentservice.domain.shared.model.Pageable;
import dev.animedia.contentservice.infrastructure.content.persistence.model.ContentEntity;
import dev.animedia.contentservice.infrastructure.shared.mapper.PaginationPersistenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ContentSearchRepositoryImpl implements ContentSearchRepository {
	private final JpaContentRepository jpaContentRepository;
	private final PaginationPersistenceMapper paginationPersistenceMapper;

	@Autowired
	public ContentSearchRepositoryImpl(
		JpaContentRepository jpaContentRepository,
		PaginationPersistenceMapper paginationPersistenceMapper
	) {
		this.jpaContentRepository = jpaContentRepository;
		this.paginationPersistenceMapper = paginationPersistenceMapper;
	}

	@Override
	public Page<Content> search(ContentSearchCriteria contentSearchCriteria, Pageable pageable) {
		org.springframework.data.domain.Pageable springPageable = paginationPersistenceMapper.toPageable(pageable.page(), pageable.size());

		var specs = List.of(
			ContentSpecification.hasUuid(contentSearchCriteria.uuid()),
			ContentSpecification.hasAliases(contentSearchCriteria.aliasList()),
			ContentSpecification.hasTranslationFilters(contentSearchCriteria.titleList(), contentSearchCriteria.languageCodeList()),
			ContentSpecification.hasTypes(contentSearchCriteria.typeList()),
			ContentSpecification.hasSeasons(contentSearchCriteria.seasonList()),
			ContentSpecification.hasStatuses(contentSearchCriteria.statusIdList()),
			ContentSpecification.hasReleaseFrom(contentSearchCriteria.releaseDateFrom()),
			ContentSpecification.hasReleaseTo(contentSearchCriteria.releaseDateTo()),
			ContentSpecification.hasCreatedAtFrom(contentSearchCriteria.createdAtFrom()),
			ContentSpecification.hasCreatedTo(contentSearchCriteria.createdAtTo()),
			ContentSpecification.hasUpdatedAtFrom(contentSearchCriteria.updatedAtFrom()),
			ContentSpecification.hasUpdatedAtTo(contentSearchCriteria.updatedAtTo()),
			ContentSpecification.hasActive(contentSearchCriteria.active()),
			ContentSpecification.hasLanguageCodes(contentSearchCriteria.languageCodeList()),
			ContentSpecification.hasGenres(contentSearchCriteria.genreIdList())
		);
		org.springframework.data.domain.Page<ContentEntity> contentEntitySpringPage = jpaContentRepository.findAll(Specification.allOf(specs), springPageable);

		List<UUID> contentIdList = contentEntitySpringPage.stream().map(ContentEntity::getId).toList();
		return null;
	}
}
