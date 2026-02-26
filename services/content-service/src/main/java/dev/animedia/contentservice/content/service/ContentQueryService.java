package dev.animedia.contentservice.content.service;

import dev.animedia.contentservice.content.dto.request.PrivateSearchRequestDto;
import dev.animedia.contentservice.content.dto.request.PublicSearchRequestDto;
import dev.animedia.contentservice.content.dto.response.ContentResponseDto;
import dev.animedia.contentservice.content.dto.response.ContentWithTranslationResponseDto;
import dev.animedia.contentservice.content.dto.response.ContentWithTranslationsResponseDto;
import dev.animedia.contentservice.content.model.ContentType;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ContentQueryService {
	Page<ContentWithTranslationsResponseDto> search(PrivateSearchRequestDto privateSearchRequestDto);
	Page<ContentWithTranslationResponseDto> search(PublicSearchRequestDto publicSearchRequestDto);
	ContentResponseDto findByUuid(UUID contentUuid, String languageCode);
	ContentWithTranslationResponseDto findByAlias(String alias, String languageCode);

	boolean exists(UUID uuid);
	boolean exists(String alias, ContentType type, Integer season);
	boolean existsExcludeId(String alias, ContentType type, Integer season, UUID uuid);
}
