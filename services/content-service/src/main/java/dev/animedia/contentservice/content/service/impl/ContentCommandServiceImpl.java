package dev.animedia.contentservice.content.service.impl;

import dev.animedia.contentservice.app.config.LanguageInterceptor;
import dev.animedia.contentservice.app.exception.AppException;
import dev.animedia.contentservice.content.ContentConstants;
import dev.animedia.contentservice.content.dto.request.ContentRequestDto;
import dev.animedia.contentservice.content.dto.response.ContentResponseDto;
import dev.animedia.contentservice.content.mapper.ContentMapper;
import dev.animedia.contentservice.content.model.Content;
import dev.animedia.contentservice.content.repository.ContentRepository;
import dev.animedia.contentservice.content.service.ContentCommandService;
import dev.animedia.contentservice.content.service.ContentQueryService;
import io.grpc.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ContentCommandServiceImpl implements ContentCommandService {

	private final ContentRepository contentRepository;
	private final ContentMapper contentMapper;
	private final ContentQueryService contentQueryService;

	@Autowired
	public ContentCommandServiceImpl(
		ContentRepository contentRepository,
		ContentMapper contentMapper,
		ContentQueryService contentQueryService
	) {
		this.contentRepository = contentRepository;
		this.contentMapper = contentMapper;
		this.contentQueryService = contentQueryService;
	}

	@Override
	public ContentResponseDto create(ContentRequestDto contentRequestDto) {
		Content content = contentMapper.toContent(contentRequestDto);
		var contentExists = contentQueryService.exists(content.getAlias(), content.getType(), content.getSeason());
		if (contentExists) throw new AppException(Status.Code.ALREADY_EXISTS, ContentConstants.CONTENT_EXISTS_MESSAGE);
		var savedContentUuid = contentRepository.save(content).getUuid();
		String languageCode = LanguageInterceptor.getLanguageCode();
		return contentQueryService.findByUuid(savedContentUuid, languageCode);
	}

	@SuppressWarnings("DuplicatedCode")
	@Override
	public ContentResponseDto update(ContentRequestDto contentRequestDto) {
		var content = contentRepository.findById(contentRequestDto.uuid())
			.orElseThrow(() -> new AppException(Status.Code.NOT_FOUND, ContentConstants.CONTENT_NOT_FOUND_MESSAGE));

		var contentExists = contentQueryService.existsExcludeId(contentRequestDto.alias(), contentRequestDto.type(), contentRequestDto.season(), contentRequestDto.uuid());
		if (contentExists) throw new AppException(Status.Code.ALREADY_EXISTS, ContentConstants.CONTENT_EXISTS_MESSAGE);

		content.setAlias(contentRequestDto.alias());
		content.setType(contentRequestDto.type());
		content.setSeason(contentRequestDto.season());
		content.setStatus(contentRequestDto.status());
		content.setCoverUrl(contentRequestDto.coverUrl());
		content.setTrailerUrl(contentRequestDto.trailerUrl());
		content.setReleaseDate(contentRequestDto.releaseDate());
		content.setActive(contentRequestDto.active());
		content.setLanguageCodes(contentRequestDto.languageCodes());
		content.setGenres(contentRequestDto.genres());

		contentRepository.save(content);

		String languageCode = LanguageInterceptor.getLanguageCode();
		return contentQueryService.findByUuid(content.getUuid(), languageCode);
	}

	@Override
	public void delete(UUID uuid) {
		var contentExists = contentQueryService.exists(uuid);
		if (!contentExists) throw new AppException(Status.Code.NOT_FOUND, ContentConstants.CONTENT_NOT_FOUND_MESSAGE);
		contentRepository.deleteById(uuid);
	}
}
