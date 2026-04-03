package dev.animedia.contentservice.old.content.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.animedia.contentservice.old.app.exception.AppException;
import dev.animedia.contentservice.old.app.exception.AppExceptionStatus;
import dev.animedia.contentservice.old.content.ContentConstants;
import dev.animedia.contentservice.old.content.dto.response.ContentResponseDto;
import dev.animedia.contentservice.old.content.dto.response.ContentWithTranslationResponseDto;
import dev.animedia.contentservice.old.content.mapper.ContentMapper;
import dev.animedia.contentservice.old.content.model.ContentType;
import dev.animedia.contentservice.old.content.repository.ContentRepository;
import dev.animedia.contentservice.old.content.service.ContentQueryService;
import dev.animedia.contentservice.old.content.service.ContentTranslationQueryService;
import dev.animedia.contentservice.old.genre.model.Genre;
import dev.animedia.contentservice.old.genre.service.GenreQueryService;
import dev.animedia.contentservice.old.status.service.ContentStatusQueryService;

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
	public ContentResponseDto findByUuid(UUID contentUuid, String languageCode) {
		var content = contentRepository.findById(contentUuid)
			.orElseThrow(() -> new AppException(AppExceptionStatus.NOT_FOUND, ContentConstants.CONTENT_NOT_FOUND_MESSAGE));
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
			.orElseThrow(() -> new AppException(AppExceptionStatus.NOT_FOUND, ContentConstants.CONTENT_NOT_FOUND_MESSAGE));

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
