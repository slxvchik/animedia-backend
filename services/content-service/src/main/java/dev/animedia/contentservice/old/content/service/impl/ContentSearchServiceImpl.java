package dev.animedia.contentservice.old.content.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import dev.animedia.contentservice.old.content.dto.request.PrivateSearchRequestDto;
import dev.animedia.contentservice.old.content.dto.request.PublicSearchRequestDto;
import dev.animedia.contentservice.old.content.dto.response.ContentTranslationResponseDto;
import dev.animedia.contentservice.old.content.dto.response.ContentWithTranslationResponseDto;
import dev.animedia.contentservice.old.content.dto.response.ContentWithTranslationListResponseDto;
import dev.animedia.contentservice.old.content.mapper.ContentMapper;
import dev.animedia.contentservice.old.content.model.Content;
import dev.animedia.contentservice.old.content.repository.ContentRepository;
import dev.animedia.contentservice.old.content.repository.ContentSpecification;
import dev.animedia.contentservice.old.content.service.ContentSearchService;
import dev.animedia.contentservice.old.genre.dto.response.GenreWithTranslationResponseDto;
import dev.animedia.contentservice.old.genre.model.Genre;
import dev.animedia.contentservice.old.genre.service.GenreQueryService;
import dev.animedia.contentservice.old.status.dto.response.ContentStatusWithTranslationResponseDto;
import dev.animedia.contentservice.old.status.service.ContentStatusQueryService;

@Service
public class ContentSearchServiceImpl implements ContentSearchService {

	private final ContentMapper contentMapper;
	private final ContentTranslationQueryServiceImpl contentTranslationQueryService;
	private final GenreQueryService genreQueryService;
	private final ContentStatusQueryService contentStatusQueryService;
	private final ContentRepository contentRepository;

	@Autowired
	public ContentSearchServiceImpl(
		ContentMapper contentMapper,
		ContentTranslationQueryServiceImpl contentTranslationQueryService,
		GenreQueryService genreQueryService,
		ContentStatusQueryService contentStatusQueryService,
		ContentRepository contentRepository
	) {
		this.contentMapper = contentMapper;
		this.contentTranslationQueryService = contentTranslationQueryService;
		this.genreQueryService = genreQueryService;
		this.contentStatusQueryService = contentStatusQueryService;
		this.contentRepository = contentRepository;
	}

	@Override
	public Page<ContentWithTranslationListResponseDto> search(PrivateSearchRequestDto privateSearchRequestDto, String languageCode, Pageable pageable) {
		var contentPage = searchContents(privateSearchRequestDto, pageable);
		if (contentPage.isEmpty()) return Page.empty(contentPage.getPageable());

		var searchResult = fetchContentRelations(contentPage.getContent(), languageCode);
		var contentsWithTranslations = contentMapper.toContentListWithTranslationListResponseDto(
			searchResult.contents(),
			searchResult.translations(),
			searchResult.contentStatuses(),
			searchResult.genres()
		);

		return PageableExecutionUtils.getPage(
			contentsWithTranslations,
			contentPage.getPageable(),
			contentPage::getTotalElements
		);
	}

	@Override
	public Page<ContentWithTranslationResponseDto> search(PublicSearchRequestDto publicSearchRequestDto, String languageCode, Pageable pageable) {

		var contentUuids = searchContents(publicSearchRequestDto, languageCode, pageable);
		if (contentUuids.isEmpty()) return Page.empty(contentUuids.getPageable());

		var searchResult = fetchContentRelations(contentUuids.getContent(), languageCode);
		var contentsWithTranslations = contentMapper.toContentListWithTranslationResponseDto(
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

	private Page<Content> searchContents(PrivateSearchRequestDto searchRequestDto, Pageable pageable) {
		var specs = List.of(
			ContentSpecification.hasUuid(searchRequestDto.uuid()),
			ContentSpecification.hasCreatedAtFrom(searchRequestDto.createdAtFrom()),
			ContentSpecification.hasCreatedTo(searchRequestDto.createdAtTo()),
			ContentSpecification.hasUpdatedAtFrom(searchRequestDto.updatedAtFrom()),
			ContentSpecification.hasUpdatedAtTo(searchRequestDto.updatedAtTo()),
			ContentSpecification.hasActive(searchRequestDto.active()),
			ContentSpecification.hasAlias(searchRequestDto.alias()),
			ContentSpecification.hasTranslationFilters(searchRequestDto.title(), null),
			ContentSpecification.hasTypes(searchRequestDto.types()),
			ContentSpecification.hasSeasons(searchRequestDto.seasons()),
			ContentSpecification.hasStatuses(searchRequestDto.contentStatusIds()),
			ContentSpecification.hasReleaseFrom(searchRequestDto.releaseFrom()),
			ContentSpecification.hasReleaseTo(searchRequestDto.releaseTo()),
			ContentSpecification.hasLanguageCodes(searchRequestDto.languageCodes()),
			ContentSpecification.hasGenres(searchRequestDto.genreIds())
		);

		return contentRepository.findAll(Specification.allOf(specs), pageable);
	}

	private Page<Content> searchContents(PublicSearchRequestDto searchRequestDto, String languageCode, Pageable pageable) {
		var specs = List.of(
			ContentSpecification.hasAlias(searchRequestDto.alias()),
			ContentSpecification.hasTranslationFilters(searchRequestDto.title(), languageCode),
			ContentSpecification.hasTypes(searchRequestDto.types()),
			ContentSpecification.hasSeasons(searchRequestDto.seasons()),
			ContentSpecification.hasStatuses(searchRequestDto.contentStatusIds()),
			ContentSpecification.hasReleaseFrom(searchRequestDto.releaseFrom()),
			ContentSpecification.hasReleaseTo(searchRequestDto.releaseTo()),
			ContentSpecification.hasLanguageCodes(searchRequestDto.languageCodes()),
			ContentSpecification.hasGenres(searchRequestDto.genreIds())
		);

		return contentRepository.findAll(Specification.allOf(specs), pageable);
	}

	/**
	 * @param contents - found content entities
	 * @return all nested content entities with a translation as a ResponseDto
	 */
	private ContentRelationsResult fetchContentRelations(List<Content> contents, String languageCode) {
		List<UUID> contentUuids = contents.stream()
			.map(Content::getUuid)
			.toList();

		List<Long> genreIds = contents.stream()
			.flatMap(c -> c.getGenres().stream())
			.map(Genre::getId)
			.distinct()
			.toList();

		List<Long> contentStatusIds = contents.stream()
			.map(c -> c.getStatus().getId())
			.distinct()
			.toList();

		List<ContentTranslationResponseDto> translations = contentTranslationQueryService.findByContentUuidsAndLanguageCode(contentUuids, languageCode);
		List<GenreWithTranslationResponseDto> genres = genreQueryService.findByIdsAndLanguageCode(genreIds, languageCode);
		List<ContentStatusWithTranslationResponseDto> contentStatuses = contentStatusQueryService.findByIdsAndLanguageCode(contentStatusIds, languageCode);

		return new ContentRelationsResult(contents, translations, genres, contentStatuses);
	}

	private record ContentRelationsResult(
		List<Content> contents,
		List<ContentTranslationResponseDto> translations,
		List<GenreWithTranslationResponseDto> genres,
		List<ContentStatusWithTranslationResponseDto> contentStatuses
	) {}
}
