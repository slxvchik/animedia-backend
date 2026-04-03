package dev.animedia.contentservice.old.content.service;

import org.springframework.data.domain.Page;

import dev.animedia.contentservice.old.content.dto.request.PrivateSearchRequestDto;
import dev.animedia.contentservice.old.content.dto.request.PublicSearchRequestDto;
import dev.animedia.contentservice.old.content.dto.response.ContentWithTranslationResponseDto;
import dev.animedia.contentservice.old.content.dto.response.ContentWithTranslationListResponseDto;
import org.springframework.data.domain.Pageable;

public interface ContentSearchService {
	Page<ContentWithTranslationListResponseDto> search(PrivateSearchRequestDto privateSearchRequestDto, String languageCode, Pageable pageable);
	Page<ContentWithTranslationResponseDto> search(PublicSearchRequestDto publicSearchRequestDto, String languageCode, Pageable pageable);
}
