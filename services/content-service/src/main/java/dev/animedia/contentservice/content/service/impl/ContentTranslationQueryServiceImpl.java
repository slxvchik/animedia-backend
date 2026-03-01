package dev.animedia.contentservice.content.service.impl;

import dev.animedia.contentservice.content.dto.response.ContentTranslationResponseDto;
import dev.animedia.contentservice.content.service.ContentTranslationQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContentTranslationQueryServiceImpl implements ContentTranslationQueryService {
	@Override
	public Page<ContentTranslationResponseDto> search(String contentUuid, String title, Pageable pageable) {
		return null;
	}

	@Override
	public ContentTranslationResponseDto findByContentUuidAndLanguageCode(UUID contentUuid, String languageCode) {
		return null;
	}

	@Override
	public List<ContentTranslationResponseDto> findByContentUuidsAndLanguageCode(List<UUID> contentUuids, String languageCode) {
		return null;
	}
}
