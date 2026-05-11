package dev.animedia.contentservice.infrastructure.content.persistence.repository;

import dev.animedia.contentservice.application.shared.mapper.PaginationApplicationMapper;
import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.model.ContentSearchCriteria;
import dev.animedia.contentservice.domain.content.repository.ContentSearchRepository;
import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.genre.repository.GenreQueryRepository;
import dev.animedia.contentservice.domain.shared.model.Page;
import dev.animedia.contentservice.domain.shared.model.Pageable;
import dev.animedia.contentservice.domain.status.model.Status;
import dev.animedia.contentservice.domain.status.repository.StatusQueryRepository;
import dev.animedia.contentservice.infrastructure.content.persistence.mapper.ContentPersistenceMapper;
import dev.animedia.contentservice.infrastructure.content.persistence.model.ContentEntity;
import dev.animedia.contentservice.infrastructure.shared.mapper.PaginationPersistenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ContentSearchRepositoryImpl implements ContentSearchRepository {
	private final GenreQueryRepository genreQueryRepository;
	private final StatusQueryRepository statusQueryRepository;
	private final JpaContentRepository jpaContentRepository;
	private final ContentPersistenceMapper contentPersistenceMapper;
	private final PaginationPersistenceMapper paginationPersistenceMapper;
	private final PaginationApplicationMapper paginationApplicationMapper;

	@Autowired
	public ContentSearchRepositoryImpl(
		GenreQueryRepository genreQueryRepository,
		StatusQueryRepository statusQueryRepository,
		JpaContentRepository jpaContentRepository,
		ContentPersistenceMapper contentPersistenceMapper,
		PaginationPersistenceMapper paginationPersistenceMapper,
		PaginationApplicationMapper paginationApplicationMapper
	) {
        this.genreQueryRepository = genreQueryRepository;
        this.statusQueryRepository = statusQueryRepository;
        this.jpaContentRepository = jpaContentRepository;
        this.contentPersistenceMapper = contentPersistenceMapper;
        this.paginationPersistenceMapper = paginationPersistenceMapper;
		this.paginationApplicationMapper = paginationApplicationMapper;
	}

	@Override
	public Page<Content> search(ContentSearchCriteria contentSearchCriteria, Pageable pageable) {
		org.springframework.data.domain.Pageable springPageable = paginationPersistenceMapper.toPageable(pageable.page(), pageable.size());

		String languageCode = contentSearchCriteria.translateLanguageCode();

		var specs = List.of(
			ContentSpecification.hasUuid(contentSearchCriteria.uuid()),
			ContentSpecification.hasAliases(contentSearchCriteria.aliasList()),
			ContentSpecification.hasTranslationFilters(contentSearchCriteria.titleList(), languageCode),
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
		Page<ContentEntity> contentEntityPage = paginationPersistenceMapper.toDomainPage(contentEntitySpringPage);

		// fetch all genres and statuses on page
		Set<Long> allGenreIdSet = new HashSet<>();
		Set<Long> allStatusIdSet = new HashSet<>();

		for (var contentEntity : contentEntitySpringPage.getContent()) {
			for (var contentGenreEntity : contentEntity.getGenreSet()) {
				allGenreIdSet.add(contentGenreEntity.getId());
			}
			allStatusIdSet.add(contentEntity.getStatusEntity().getId());
		}

		Map<Long, Genre> genreMap = genreQueryRepository.findByIdList(new ArrayList<>(allGenreIdSet), languageCode)
			.stream()
			.collect(
				Collectors.toMap(Genre::getId, genre -> genre)
			);
		Map<Long, Status> statusMap = statusQueryRepository.findByIdList(new ArrayList<>(allStatusIdSet), languageCode)
			.stream()
			.collect(
				Collectors.toMap(Status::getId, status -> status)
			);

		List<Content> contentList = contentEntitySpringPage.getContent()
			.stream()
			.map(ce -> contentPersistenceMapper.toContent(
				ce,
				// already loaded with specs
				ce.getTranslationSet(),
				statusMap.getOrDefault(ce.getStatusEntity().getId(), null),
				ce.getGenreSet()
					.stream()
					.map(ceGenre -> genreMap.getOrDefault(ceGenre.getId(), null))
					.collect(Collectors.toSet())
			))
			.toList();

		return paginationApplicationMapper.changeContent(contentEntityPage, contentList);
	}
}
