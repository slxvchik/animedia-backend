package dev.animedia.contentservice.content.infrastracture.persistence.repository;

import dev.animedia.contentservice.content.domain.model.Content;
import dev.animedia.contentservice.content.domain.model.ContentType;
import dev.animedia.contentservice.content.domain.repository.ContentQueryRepository;
import dev.animedia.contentservice.genre.domain.model.Genre;
import dev.animedia.contentservice.shared.domain.pagination.Page;
import dev.animedia.contentservice.shared.domain.pagination.Pageable;
import dev.animedia.contentservice.status.domain.model.Status;
import dev.animedia.contentservice.content.infrastracture.persistence.mapper.ContentPersistenceMapper;
import dev.animedia.contentservice.content.infrastracture.persistence.model.ContentEntity;
import dev.animedia.contentservice.genre.infrastracture.persistence.mapper.GenrePersistenceMapper;
import dev.animedia.contentservice.genre.infrastracture.persistence.model.GenreEntity;
import dev.animedia.contentservice.genre.infrastracture.persistence.repository.JpaGenreRepository;
import dev.animedia.contentservice.shared.infrastructure.persistence.mapper.PaginationPersistenceMapper;
import dev.animedia.contentservice.status.infrastracture.persistence.mapper.StatusPersistenceMapper;
import dev.animedia.contentservice.status.infrastracture.persistence.model.StatusEntity;
import dev.animedia.contentservice.status.infrastracture.persistence.repository.JpaStatusRepository;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ContentQueryRepositoryImpl implements ContentQueryRepository {
	private final JpaContentRepository jpaContentRepository;
	private final JpaStatusRepository jpaStatusRepository;
	private final JpaGenreRepository jpaGenreRepository;

	private final ContentPersistenceMapper contentPersistenceMapper;
	private final StatusPersistenceMapper statusPersistenceMapper;
	private final GenrePersistenceMapper genrePersistenceMapper;

	private final PaginationPersistenceMapper paginationPersistenceMapper;

	@Autowired
	public ContentQueryRepositoryImpl(
		JpaContentRepository jpaContentRepository,
		JpaStatusRepository jpaStatusRepository,
		JpaGenreRepository jpaGenreRepository,
		ContentPersistenceMapper contentPersistenceMapper,
		StatusPersistenceMapper statusPersistenceMapper,
		GenrePersistenceMapper genrePersistenceMapper,
		PaginationPersistenceMapper paginationPersistenceMapper
	) {
		this.jpaContentRepository = jpaContentRepository;
		this.jpaStatusRepository = jpaStatusRepository;
		this.jpaGenreRepository = jpaGenreRepository;
		this.contentPersistenceMapper = contentPersistenceMapper;
		this.statusPersistenceMapper = statusPersistenceMapper;
		this.genrePersistenceMapper = genrePersistenceMapper;
		this.paginationPersistenceMapper = paginationPersistenceMapper;
	}

	@Override
	public Optional<Content> find(UUID id, @Nullable String languageCode) {
		Optional<ContentEntity> optionalContentEntity = jpaContentRepository.findById(id, languageCode);
		if (optionalContentEntity.isEmpty()) return Optional.empty();
		ContentEntity ce = optionalContentEntity.get();
		return fetchContentOptional(ce, languageCode);
	}

	@Override
	public Optional<Content> find(
		String alias,
		ContentType type,
		int season,
		@Nullable String languageCode
	) {
		Optional<ContentEntity> optionalContentEntity = jpaContentRepository.findByAliasAndTypeAndSeason(alias, type, season, languageCode);
		if (optionalContentEntity.isEmpty()) return Optional.empty();
		ContentEntity ce = optionalContentEntity.get();
		return fetchContentOptional(ce, languageCode);
	}

	@Override
	public List<Content> find(List<UUID> idList, @Nullable String languageCode) {
		List<ContentEntity> ceList = jpaContentRepository.findByIdListAndLanguageCode(idList, languageCode);

		List<UUID> statusIdList = new ArrayList<>();
		List<UUID> genreIdList = new ArrayList<>();

		for (ContentEntity ce : ceList) {
			statusIdList.add(ce.getStatusEntity().getId());
			genreIdList.addAll(ce.getGenres().stream().map(GenreEntity::getId).toList());
		}

		Map<UUID, Status> sMap = fetchStatuses(statusIdList, languageCode).stream()
			.collect(Collectors.toMap(
				Status::getId,
				s -> s
			));
		Map<UUID, Genre> gMap = fetchGenres(genreIdList, languageCode).stream()
			.collect(Collectors.toMap(
				Genre::getId,
				g -> g
			));

		return ceList.stream()
			.map(contentEntity -> contentPersistenceMapper.toContent(
				contentEntity,
				sMap.get(contentEntity.getStatusEntity().getId()),
				contentEntity.getGenres().stream()
					.map(g -> gMap.get(g.getId()))
					.collect(Collectors.toSet())
			)).toList();
	}

	@Override
	public Page<Content> findAll(Pageable pageable) {
		org.springframework.data.domain.Pageable springPageable = paginationPersistenceMapper.toPageable(
			pageable.getPage(),
			pageable.getSize()
		);

		org.springframework.data.domain.Page<ContentEntity> contentEntitySpringPage = jpaContentRepository.findAll(springPageable);

		List<UUID> statusIdList = new ArrayList<>();
		List<UUID> genreIdList = new ArrayList<>();

		for (ContentEntity ce : contentEntitySpringPage.getContent()) {
			statusIdList.add(ce.getStatusEntity().getId());
			genreIdList.addAll(ce.getGenres().stream().map(GenreEntity::getId).toList());
		}

		Map<UUID, Status> sMap = fetchStatuses(statusIdList, null).stream()
			.collect(Collectors.toMap(
				Status::getId,
				s -> s
			));
		Map<UUID, Genre> gMap = fetchGenres(genreIdList, null).stream()
			.collect(Collectors.toMap(
				Genre::getId,
				g -> g
			));

		List<Content> contentList = contentEntitySpringPage.getContent().stream()
			.map(contentEntity -> contentPersistenceMapper.toContent(
				contentEntity,
				sMap.get(contentEntity.getStatusEntity().getId()),
				contentEntity.getGenres().stream()
					.map(g -> gMap.get(g.getId()))
					.collect(Collectors.toSet())
			)).toList();

		Page<ContentEntity> contentEntityDomainPage = paginationPersistenceMapper.toDomainPage(contentEntitySpringPage);

		return contentEntityDomainPage.changeContent(contentList);
	}

	@Override
	public boolean exists(String alias, ContentType type, int season) {
		return jpaContentRepository.existsByAliasAndContentTypeAndSeason(alias, type, season);
	}

	private Optional<Content> fetchContentOptional(ContentEntity ce, @Nullable String languageCode) {
		Status s = fetchStatus(ce.getStatusEntity().getId(), languageCode);

		List<UUID> genreIdList = ce.getGenres().stream().map(GenreEntity::getId).toList();
		Set<Genre> gSet = fetchGenres(genreIdList, languageCode);

		return Optional.of(contentPersistenceMapper.toContent(
			ce,
			s,
			gSet
		));
	}

	private Status fetchStatus(UUID statusId, @Nullable String languageCode) {
		StatusEntity se = jpaStatusRepository.findById(statusId, languageCode);
		return statusPersistenceMapper.toStatus(se);
	}

	private Set<Status> fetchStatuses(List<UUID> statusIdList, @Nullable String languageCode) {
		List<StatusEntity> seList = jpaStatusRepository.findByIdList(statusIdList, languageCode);
		return seList.stream()
			.map(statusPersistenceMapper::toStatus)
			.collect(Collectors.toSet());
	}

	private Set<Genre> fetchGenres(List<UUID> genreIdList, @Nullable String languageCode) {
		List<GenreEntity> geList = jpaGenreRepository.findByIdList(genreIdList, languageCode);
		return geList.stream()
			.map(genrePersistenceMapper::toGenre)
			.collect(Collectors.toSet());
	}
}
