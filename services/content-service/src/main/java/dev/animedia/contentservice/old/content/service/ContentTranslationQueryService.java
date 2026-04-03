package dev.animedia.contentservice.old.content.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.animedia.contentservice.old.content.dto.response.ContentTranslationResponseDto;

public interface ContentTranslationQueryService {
	Page<ContentTranslationResponseDto> search(UUID contentUuid, String title, Pageable pageable);
	ContentTranslationResponseDto findByContentUuidAndLanguageCode(UUID contentUuid, String languageCode);
	List<ContentTranslationResponseDto> findByContentUuidsAndLanguageCode(List<UUID> contentUuid, String languageCode);

	boolean existsById(UUID uuid);
	boolean existsByContentIdAndLanguageCode(UUID contentUuid, String languageCode);
}
