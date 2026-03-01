package dev.animedia.contentservice.content.service.impl;

import dev.animedia.contentservice.app.config.LanguageInterceptor;
import dev.animedia.contentservice.app.exception.AppException;
import dev.animedia.contentservice.content.ContentConstants;
import dev.animedia.contentservice.content.dto.request.PrivateSearchRequestDto;
import dev.animedia.contentservice.content.dto.request.PublicSearchRequestDto;
import dev.animedia.contentservice.content.dto.response.ContentResponseDto;
import dev.animedia.contentservice.content.dto.response.ContentTranslationResponseDto;
import dev.animedia.contentservice.content.dto.response.ContentWithTranslationResponseDto;
import dev.animedia.contentservice.content.dto.response.ContentWithTranslationsResponseDto;
import dev.animedia.contentservice.content.mapper.ContentMapper;
import dev.animedia.contentservice.content.model.Content;
import dev.animedia.contentservice.content.model.ContentType;
import dev.animedia.contentservice.content.repository.ContentRepository;
import dev.animedia.contentservice.content.repository.ContentSearchRepository;
import dev.animedia.contentservice.content.service.ContentQueryService;
import dev.animedia.contentservice.content.service.ContentTranslationQueryService;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationResponseDto;
import dev.animedia.contentservice.genre.model.Genre;
import dev.animedia.contentservice.genre.service.GenreQueryService;
import dev.animedia.contentservice.status.dto.response.ContentStatusWithTranslationResponseDto;
import dev.animedia.contentservice.status.service.ContentStatusQueryService;
import io.grpc.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContentQueryServiceImpl implements ContentQueryService {

	private final ContentRepository contentRepository;
	private final ContentTranslationQueryService contentTranslationQueryService;
	private final GenreQueryService genreQueryService;
	private final ContentStatusQueryService contentStatusQueryService;
	private final ContentMapper contentMapper;
	private final ContentSearchRepository contentSearchRepository;

	@Autowired
	public ContentQueryServiceImpl(
		ContentRepository contentRepository,
		ContentTranslationQueryService contentTranslationQueryService,
		GenreQueryService genreQueryService,
		ContentStatusQueryService contentStatusQueryService,
		ContentMapper contentMapper,
		ContentSearchRepository contentSearchRepository
	) {
		this.contentRepository = contentRepository;
		this.contentTranslationQueryService = contentTranslationQueryService;
		this.genreQueryService = genreQueryService;
		this.contentStatusQueryService = contentStatusQueryService;
		this.contentMapper = contentMapper;
		this.contentSearchRepository = contentSearchRepository;
	}

	@Override
	public Page<ContentWithTranslationsResponseDto> search(PrivateSearchRequestDto privateSearchRequestDto) {
		var contentUuids = contentSearchRepository.search(privateSearchRequestDto);
		if (contentUuids.isEmpty()) return Page.empty(contentUuids.getPageable());

		var searchResult = searchFullContents(contentUuids.getContent());
		var contentsWithTranslations = contentMapper.toContentsWithTranslationsResponseDto(
			searchResult.contents(),
			searchResult.translations(),
			searchResult.contentStatuses(),
			searchResult.genres()
		);

		return PageableExecutionUtils.getPage(
			contentsWithTranslations,
			contentUuids.getPageable(),
			contentUuids::getTotalElements
		);
	}

	@Override
	public Page<ContentWithTranslationResponseDto> search(PublicSearchRequestDto publicSearchRequestDto) {
		var contentUuids = contentSearchRepository.search(publicSearchRequestDto);
		if (contentUuids.isEmpty()) return Page.empty(contentUuids.getPageable());

		var searchResult = searchFullContents(contentUuids.getContent());
		var contentsWithTranslations = contentMapper.toContentsWithTranslationResponseDto(
			searchResult.contents(),
			searchResult.translations(),
			searchResult.contentStatuses(),
			searchResult.genres()
		);

		return PageableExecutionUtils.getPage(
			contentsWithTranslations,
			contentUuids.getPageable(),
			contentUuids::getTotalElements
		);
	}

	private SearchResult searchFullContents(List<UUID> contentUuids) {
		List<Content> contents = contentRepository.findAllById(contentUuids);

		List<Long> genreIds = contents.stream()
			.flatMap(c -> c.getGenres().stream())
			.map(Genre::getId)
			.distinct()
			.toList();

		List<Long> contentStatusIds = contents.stream()
			.map(c -> c.getStatus().getId())
			.distinct()
			.toList();

		String languageCode = LanguageInterceptor.getLanguageCode();

		List<ContentTranslationResponseDto> translations = contentTranslationQueryService.findByContentUuidsAndLanguageCode(contentUuids, languageCode);
		List<GenreWithTranslationResponseDto> genres = genreQueryService.findByIdsAndLanguageCode(genreIds, languageCode);
		List<ContentStatusWithTranslationResponseDto> contentStatuses = contentStatusQueryService.findByIdsAndLanguageCode(contentStatusIds, languageCode);

		return new SearchResult(contents, translations, genres, contentStatuses);
	}

	private record SearchResult(
		List<Content> contents,
		List<ContentTranslationResponseDto> translations,
		List<GenreWithTranslationResponseDto> genres,
		List<ContentStatusWithTranslationResponseDto> contentStatuses
	) {}

	@Override
	public ContentResponseDto findByUuid(UUID contentUuid, String languageCode) {
		var content = contentRepository.findById(contentUuid)
			.orElseThrow(() -> new AppException(Status.Code.NOT_FOUND, ContentConstants.CONTENT_NOT_FOUND_MESSAGE));
		var contentStatusResponseDto = contentStatusQueryService.findByIdAndLanguageCode(content.getStatus().getId(), languageCode);
		var genreIds = content.getGenres().stream()
			.map(Genre::getId)
			.toList();
		var genresResponseDto = genreQueryService.findByIdsAndLanguageCode(genreIds, languageCode);
		return contentMapper.toContentResponseDto(content, contentStatusResponseDto, genresResponseDto);
	}

	@Override
	public ContentWithTranslationResponseDto findByAlias(String alias, String languageCode) {
		var content = contentRepository.findByAlias(alias)
			.orElseThrow(() -> new AppException(Status.Code.NOT_FOUND, ContentConstants.CONTENT_NOT_FOUND_MESSAGE));

		var contentStatusResponseDto = contentStatusQueryService.findByIdAndLanguageCode(content.getStatus().getId(), languageCode);
		var genreIds = content.getGenres().stream()
			.map(Genre::getId)
			.toList();
		var genresResponseDto = genreQueryService.findByIdsAndLanguageCode(genreIds, languageCode);

		var contentResponseDto = contentMapper.toContentResponseDto(content, contentStatusResponseDto, genresResponseDto);
		var translationResponseDto = contentTranslationQueryService.findByContentUuidAndLanguageCode(content.getUuid(), languageCode);
		return contentMapper.toContentWithTranslationResponseDto(contentResponseDto, translationResponseDto);
	}

	@Override
	public boolean exists(UUID uuid) {
		return contentRepository.existsById(uuid);
	}

	@Override
	public boolean exists(String alias, ContentType type, Integer season) {
		return contentRepository.existsByAliasAndTypeAndSeason(alias, type, season);
	}

	@Override
	public boolean existsExcludeId(String alias, ContentType type, Integer season, UUID uuid) {
		return contentRepository.existsByAliasAndTypeAndSeasonAndUuidIsNot(alias, type, season, uuid);
	}
}
