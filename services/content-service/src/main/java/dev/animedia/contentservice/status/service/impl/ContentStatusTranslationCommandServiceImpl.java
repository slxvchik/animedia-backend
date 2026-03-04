package dev.animedia.contentservice.status.service.impl;

import dev.animedia.contentservice.app.exception.AppException;
import dev.animedia.contentservice.app.exception.AppExceptionStatus;
import dev.animedia.contentservice.status.ContentStatusConstants;
import dev.animedia.contentservice.status.dto.request.CreateContentStatusTranslationRequestDto;
import dev.animedia.contentservice.status.dto.request.UpdateContentStatusTranslationRequestDto;
import dev.animedia.contentservice.status.dto.response.ContentStatusTranslationResponseDto;
import dev.animedia.contentservice.status.mapper.ContentStatusTranslationMapper;
import dev.animedia.contentservice.status.repository.ContentStatusTranslationRepository;
import dev.animedia.contentservice.status.service.ContentStatusTranslationCommandService;
import dev.animedia.contentservice.status.service.ContentStatusTranslationQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentStatusTranslationCommandServiceImpl implements ContentStatusTranslationCommandService {

	private final ContentStatusTranslationRepository contentStatusTranslationRepository;
	private final ContentStatusTranslationQueryService contentStatusTranslationQueryService;
	private final ContentStatusTranslationMapper contentStatusTranslationMapper;

	@Autowired
	public ContentStatusTranslationCommandServiceImpl(
		ContentStatusTranslationRepository contentStatusTranslationRepository,
		ContentStatusTranslationQueryService contentStatusTranslationQueryService,
		ContentStatusTranslationMapper contentStatusTranslationMapper
	) {
		this.contentStatusTranslationRepository = contentStatusTranslationRepository;
		this.contentStatusTranslationQueryService = contentStatusTranslationQueryService;
		this.contentStatusTranslationMapper = contentStatusTranslationMapper;
	}

	@Override
	public ContentStatusTranslationResponseDto create(CreateContentStatusTranslationRequestDto createContentStatusTranslationRequestDto) {

		var contentStatusTranslationExists = contentStatusTranslationQueryService.existsByContentStatusIdAndLanguageCode(
			createContentStatusTranslationRequestDto.contentStatusId(),
			createContentStatusTranslationRequestDto.languageCode()
		);
		if (contentStatusTranslationExists) throw new AppException(AppExceptionStatus.ALREADY_EXISTS, ContentStatusConstants.CONTENT_STATUS_TRANSLATION_EXISTS_MESSAGE);

		var contentStatusTranslation = contentStatusTranslationMapper.toContentStatusTranslation(createContentStatusTranslationRequestDto);

		var savedContentStatusTranslation = contentStatusTranslationRepository.save(contentStatusTranslation);
		return contentStatusTranslationMapper.toContentStatusTranslationResponseDto(savedContentStatusTranslation);
	}

	@Override
	public ContentStatusTranslationResponseDto update(Long id, UpdateContentStatusTranslationRequestDto updateContentStatusTranslationRequestDto) {
		var contentStatusTranslation = contentStatusTranslationRepository.findById(id)
			.orElseThrow(() -> new AppException(ContentStatusConstants.CONTENT_STATUS_NOT_FOUND_MESSAGE));
		contentStatusTranslation.setName(updateContentStatusTranslationRequestDto.name());
		var savedContentStatusTranslation = contentStatusTranslationRepository.save(contentStatusTranslation);
		return contentStatusTranslationMapper.toContentStatusTranslationResponseDto(savedContentStatusTranslation);
	}

	@Override
	public void delete(Long id) {
		var contentStatusTranslationExists = contentStatusTranslationQueryService.existsById(id);
		if (contentStatusTranslationExists) throw new AppException(AppExceptionStatus.NOT_FOUND, ContentStatusConstants.CONTENT_STATUS_TRANSLATION_NOT_FOUND_MESSAGE);
		contentStatusTranslationRepository.deleteById(id);
	}
}
