package dev.animedia.contentservice.content.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.animedia.contentservice.app.exception.AppException;
import dev.animedia.contentservice.app.exception.AppExceptionStatus;
import dev.animedia.contentservice.content.ContentConstants;
import dev.animedia.contentservice.content.dto.request.ContentTranslationRequestDto;
import dev.animedia.contentservice.content.dto.response.ContentTranslationResponseDto;
import dev.animedia.contentservice.content.mapper.ContentTranslationMapper;
import dev.animedia.contentservice.content.repository.ContentTranslationRepository;
import dev.animedia.contentservice.content.service.ContentTranslationCommandService;
import dev.animedia.contentservice.content.service.ContentTranslationQueryService;

@Service
public class ContentTranslationCommandServiceImpl implements ContentTranslationCommandService {

	private final ContentTranslationRepository contentTranslationRepository;
	private final ContentTranslationMapper contentTranslationMapper;
	private final ContentTranslationQueryService contentTranslationQueryService;

	@Autowired
	public ContentTranslationCommandServiceImpl(
		ContentTranslationRepository contentTranslationRepository,
		ContentTranslationMapper contentTranslationMapper,
		ContentTranslationQueryService contentTranslationQueryService
	) {
		this.contentTranslationRepository = contentTranslationRepository;
		this.contentTranslationMapper = contentTranslationMapper;
		this.contentTranslationQueryService = contentTranslationQueryService;
	}

	@Override
	public ContentTranslationResponseDto create(ContentTranslationRequestDto contentRequestDto) {
		var translationExists = contentTranslationQueryService.existsByContentIdAndLanguageCode(contentRequestDto.contentUuid(), contentRequestDto.languageCode());
		if (translationExists) throw new AppException(AppExceptionStatus.ALREADY_EXISTS, ContentConstants.CONTENT_TRANSLATION_EXISTS_MESSAGE);

		var translation = contentTranslationMapper.toContentTranslation(contentRequestDto);
		var savedTranslation = contentTranslationRepository.save(translation);

		return contentTranslationMapper.toContentTranslationResponseDto(savedTranslation);
	}

	@Override
	public ContentTranslationResponseDto update(UUID uuid, ContentTranslationRequestDto contentRequestDto) {
		var translation = contentTranslationRepository.findById(uuid).orElseThrow(
			() -> new AppException(AppExceptionStatus.NOT_FOUND, ContentConstants.CONTENT_TRANSLATION_NOT_FOUND_MESSAGE)
		);

		translation.setTitle(contentRequestDto.title());
		translation.setDescription(contentRequestDto.description());
		var updatedTranslation = contentTranslationRepository.save(translation);

		return contentTranslationMapper.toContentTranslationResponseDto(updatedTranslation);
	}

	@Override
	public void delete(UUID uuid) {
		var translationExists = contentTranslationQueryService.existsById(uuid);
		if (!translationExists) throw new AppException(AppExceptionStatus.NOT_FOUND, ContentConstants.CONTENT_TRANSLATION_NOT_FOUND_MESSAGE);
		contentTranslationRepository.deleteById(uuid);
	}
}
