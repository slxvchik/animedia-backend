package dev.animedia.contentservice.content.service.impl;

import dev.animedia.contentservice.content.dto.request.ContentTranslationRequestDto;
import dev.animedia.contentservice.content.dto.response.ContentTranslationResponseDto;
import dev.animedia.contentservice.content.repository.ContentTranslationRepository;
import dev.animedia.contentservice.content.service.ContentTranslationCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ContentTranslationCommandServiceImpl implements ContentTranslationCommandService {

	private final ContentTranslationRepository contentTranslationRepository;

	@Autowired
	public ContentTranslationCommandServiceImpl(ContentTranslationRepository contentTranslationRepository) {
		this.contentTranslationRepository = contentTranslationRepository;
	}

	@Override
	public ContentTranslationResponseDto create(ContentTranslationRequestDto contentRequestDto) {

		return null;
	}

	@Override
	public ContentTranslationResponseDto update(ContentTranslationRequestDto contentRequestDto) {
		return null;
	}

	@Override
	public void delete(UUID uuid) {

	}
}
