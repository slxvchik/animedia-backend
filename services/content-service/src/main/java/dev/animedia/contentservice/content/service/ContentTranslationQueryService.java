package dev.animedia.contentservice.content.service;

import dev.animedia.contentservice.content.dto.response.ContentTranslationResponseDto;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ContentTranslationQueryService {
	Page<ContentTranslationResponseDto> search(String contentUuid, String title);
	ContentTranslationResponseDto findByContentUuidAndLanguageCode(UUID contentUuid, String languageCode);
}
