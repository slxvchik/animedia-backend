package dev.animedia.contentservice.contentstatus.service.impl;

import dev.animedia.contentservice.contentstatus.dto.request.ContentStatusTranslationRequestDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusTranslationResponseDto;
import dev.animedia.contentservice.contentstatus.exception.ContentStatusTranslationExistsException;
import dev.animedia.contentservice.contentstatus.repository.ContentStatusTranslationRepository;
import dev.animedia.contentservice.contentstatus.service.ContentStatusTranslationCommandService;
import dev.animedia.contentservice.contentstatus.service.ContentStatusTranslationQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentStatusTranslationCommandServiceImpl implements ContentStatusTranslationCommandService {

	private final ContentStatusTranslationRepository contentStatusTranslationRepository;
	private final ContentStatusTranslationQueryService contentStatusTranslationQueryService;

	@Autowired
	public ContentStatusTranslationCommandServiceImpl(
		ContentStatusTranslationRepository contentStatusTranslationRepository,
		ContentStatusTranslationQueryService contentStatusTranslationQueryService
	) {
		this.contentStatusTranslationRepository = contentStatusTranslationRepository;
		this.contentStatusTranslationQueryService = contentStatusTranslationQueryService;
	}

	@Override
	public ContentStatusTranslationResponseDto create(ContentStatusTranslationRequestDto createContentStatusTranslationRequestDto) {

		var contentStatusTranslationExists = contentStatusTranslationQueryService.existsByContentStatusIdAndLanguageCode(
			createContentStatusTranslationRequestDto.contentStatusId(),
			createContentStatusTranslationRequestDto.languageCode()
		);
		if (contentStatusTranslationExists) throw new ContentStatusTranslationExistsException();

		contentStatusTranslationMapper

		return null;
	}

	@Override
	public ContentStatusTranslationResponseDto update(ContentStatusTranslationRequestDto createContentStatusTranslationRequestDto) {
		return null;
	}

	@Override
	public void delete(Long id) {

	}
}
