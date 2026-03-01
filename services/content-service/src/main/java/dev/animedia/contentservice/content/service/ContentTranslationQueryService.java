package dev.animedia.contentservice.content.service;

import dev.animedia.contentservice.content.dto.response.ContentTranslationResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ContentTranslationQueryService {
	Page<ContentTranslationResponseDto> search(String contentUuid, String title, Pageable pageable);
	ContentTranslationResponseDto findByContentUuidAndLanguageCode(UUID contentUuid, String languageCode);
	List<ContentTranslationResponseDto> findByContentUuidsAndLanguageCode(List<UUID> contentUuid, String languageCode);
}
