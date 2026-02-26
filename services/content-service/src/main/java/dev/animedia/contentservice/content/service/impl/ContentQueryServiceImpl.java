package dev.animedia.contentservice.content.service.impl;

import dev.animedia.contentservice.app.exception.AppException;
import dev.animedia.contentservice.content.ContentConstants;
import dev.animedia.contentservice.content.dto.request.PrivateSearchRequestDto;
import dev.animedia.contentservice.content.dto.request.PublicSearchRequestDto;
import dev.animedia.contentservice.content.dto.response.ContentResponseDto;
import dev.animedia.contentservice.content.dto.response.ContentWithTranslationResponseDto;
import dev.animedia.contentservice.content.dto.response.ContentWithTranslationsResponseDto;
import dev.animedia.contentservice.content.mapper.ContentMapper;
import dev.animedia.contentservice.content.model.Content;
import dev.animedia.contentservice.content.model.ContentType;
import dev.animedia.contentservice.content.repository.ContentRepository;
import dev.animedia.contentservice.content.repository.ContentTranslationRepository;
import dev.animedia.contentservice.content.service.ContentQueryService;
import dev.animedia.contentservice.content.service.ContentTranslationQueryService;
import dev.animedia.contentservice.genre.model.Genre;
import dev.animedia.contentservice.genre.service.GenreQueryService;
import dev.animedia.contentservice.status.service.ContentStatusQueryService;
import io.grpc.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ContentQueryServiceImpl implements ContentQueryService {

	private final ContentRepository contentRepository;
	private final ContentTranslationQueryService contentTranslationQueryService;
	private final GenreQueryService genreQueryService;
	private final ContentStatusQueryService contentStatusQueryService;
	private final ContentMapper contentMapper;

	@Autowired
	public ContentQueryServiceImpl(
		ContentRepository contentRepository,
		ContentTranslationQueryService contentTranslationQueryService,
		GenreQueryService genreQueryService,
		ContentStatusQueryService contentStatusQueryService,
		ContentMapper contentMapper
	) {
		this.contentRepository = contentRepository;
		this.contentTranslationQueryService = contentTranslationQueryService;
		this.genreQueryService = genreQueryService;
		this.contentStatusQueryService = contentStatusQueryService;
		this.contentMapper = contentMapper;
	}

	@Override
	public Page<ContentWithTranslationsResponseDto> search(PrivateSearchRequestDto privateSearchRequestDto) {
		return null;
	}

	@Override
	public Page<ContentWithTranslationResponseDto> search(PublicSearchRequestDto publicSearchRequestDto) {

		return null;
	}

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
