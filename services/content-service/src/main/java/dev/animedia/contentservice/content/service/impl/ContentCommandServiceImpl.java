package dev.animedia.contentservice.content.service.impl;

import dev.animedia.contentservice.app.config.LanguageInterceptor;
import dev.animedia.contentservice.app.exception.AppException;
import dev.animedia.contentservice.app.exception.AppExceptionStatus;
import dev.animedia.contentservice.content.ContentConstants;
import dev.animedia.contentservice.content.dto.request.ContentRequestDto;
import dev.animedia.contentservice.content.dto.response.ContentResponseDto;
import dev.animedia.contentservice.content.mapper.ContentMapper;
import dev.animedia.contentservice.content.model.Content;
import dev.animedia.contentservice.content.repository.ContentRepository;
import dev.animedia.contentservice.content.service.ContentCommandService;
import dev.animedia.contentservice.content.service.ContentQueryService;
import dev.animedia.contentservice.genre.GenreConstants;
import dev.animedia.contentservice.genre.service.GenreQueryService;
import dev.animedia.contentservice.status.ContentStatusConstants;
import dev.animedia.contentservice.status.service.ContentStatusQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ContentCommandServiceImpl implements ContentCommandService {

	private final ContentRepository contentRepository;
	private final ContentMapper contentMapper;
	private final ContentQueryService contentQueryService;
	private final GenreQueryService genreQueryService;
	private final ContentStatusQueryService contentStatusQueryService;

	@Autowired
	public ContentCommandServiceImpl(
		ContentRepository contentRepository,
		ContentMapper contentMapper,
		ContentQueryService contentQueryService,
		GenreQueryService genreQueryService,
		ContentStatusQueryService contentStatusQueryService
	) {
		this.contentRepository = contentRepository;
		this.contentMapper = contentMapper;
		this.contentQueryService = contentQueryService;
		this.genreQueryService = genreQueryService;
		this.contentStatusQueryService = contentStatusQueryService;
	}

	@Override
	public ContentResponseDto create(ContentRequestDto requestDto) {
		Content content = contentMapper.toContent(requestDto);

		List<String> errorMessages = new ArrayList<>();

		var contentExists = contentQueryService.exists(content.getAlias(), content.getType(), content.getSeason());
		if (contentExists) errorMessages.add(ContentConstants.CONTENT_EXISTS_MESSAGE);

		validateRequest(errorMessages, requestDto);

		if (!errorMessages.isEmpty()) throw new AppException(AppExceptionStatus.INVALID_ARGUMENT, errorMessages);

		var savedContentUuid = contentRepository.save(content).getUuid();
		String languageCode = LanguageInterceptor.getLanguageCode();
		return contentQueryService.findByUuid(savedContentUuid, languageCode);
	}

	@Override
	public ContentResponseDto update(UUID uuid, ContentRequestDto requestDto) {
		var content = contentRepository.findById(uuid)
			.orElseThrow(() -> new AppException(AppExceptionStatus.NOT_FOUND, ContentConstants.CONTENT_NOT_FOUND_MESSAGE));

		List<String> errorMessages = new ArrayList<>();

		var contentExists = contentQueryService.existsExcludeId(requestDto.alias(), requestDto.type(), requestDto.season(), uuid);
		if (contentExists) errorMessages.add(ContentConstants.CONTENT_EXISTS_MESSAGE);

		validateRequest(errorMessages, requestDto);
		if (!errorMessages.isEmpty()) throw new AppException(AppExceptionStatus.INVALID_ARGUMENT, errorMessages);

		contentMapper.updateEntity(requestDto, content);
		contentRepository.save(content);

		String languageCode = LanguageInterceptor.getLanguageCode();
		return contentQueryService.findByUuid(content.getUuid(), languageCode);
	}

	@Override
	public void delete(UUID uuid) {
		var contentExists = contentQueryService.exists(uuid);
		if (!contentExists) throw new AppException(AppExceptionStatus.NOT_FOUND, ContentConstants.CONTENT_NOT_FOUND_MESSAGE);
		contentRepository.deleteById(uuid);
	}

	private void validateRequest(List<String> errorMessages, ContentRequestDto request) {
		var genresExists = genreQueryService.existsAllByIds(new ArrayList<>(request.genreIds()));
		if (!genresExists) errorMessages.add(GenreConstants.GENRES_NOT_FOUND_MESSAGE);

		var contentStatusExists = contentStatusQueryService.existsById(request.contentStatusId());
		if (!contentStatusExists) errorMessages.add(ContentStatusConstants.CONTENT_STATUS_NOT_FOUND_MESSAGE);
	}
}
