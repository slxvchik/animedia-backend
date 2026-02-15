package dev.animedia.contentservice.contentstatus.service.impl;

import dev.animedia.contentservice.contentstatus.dto.request.CreateContentStatusTranslationRequestDto;
import dev.animedia.contentservice.contentstatus.dto.request.UpdateContentStatusTranslationRequestDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusTranslationResponseDto;
import dev.animedia.contentservice.contentstatus.exception.ContentStatusTranslationExistsException;
import dev.animedia.contentservice.contentstatus.exception.ContentStatusTranslationNotFoundException;
import dev.animedia.contentservice.contentstatus.mapper.ContentStatusTranslationMapper;
import dev.animedia.contentservice.contentstatus.repository.ContentStatusTranslationRepository;
import dev.animedia.contentservice.contentstatus.service.ContentStatusTranslationCommandService;
import dev.animedia.contentservice.contentstatus.service.ContentStatusTranslationQueryService;
import dev.animedia.contentservice.genre.exception.GenreNotFoundException;
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
		if (contentStatusTranslationExists) throw new ContentStatusTranslationExistsException();

		var contentStatusTranslation = contentStatusTranslationMapper.toContentStatusTranslation(createContentStatusTranslationRequestDto);

		var savedContentStatusTranslation = contentStatusTranslationRepository.save(contentStatusTranslation);
		return contentStatusTranslationMapper.toContentStatusTranslationResponseDto(savedContentStatusTranslation);
	}

	@Override
	public ContentStatusTranslationResponseDto update(Long id, UpdateContentStatusTranslationRequestDto updateContentStatusTranslationRequestDto) {
		var contentStatusTranslation = contentStatusTranslationRepository.findById(id).orElseThrow(GenreNotFoundException::new);
		contentStatusTranslation.setName(updateContentStatusTranslationRequestDto.name());
		var savedContentStatusTranslation = contentStatusTranslationRepository.save(contentStatusTranslation);
		return contentStatusTranslationMapper.toContentStatusTranslationResponseDto(savedContentStatusTranslation);
	}

	@Override
	public void delete(Long id) {
		var contentStatusTranslationExists = contentStatusTranslationQueryService.existsById(id);
		if (contentStatusTranslationExists) throw new ContentStatusTranslationNotFoundException();
		contentStatusTranslationRepository.deleteById(id);
	}
}
