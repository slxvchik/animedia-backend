package dev.animedia.contentservice.content.service;

import org.springframework.data.domain.Page;

import dev.animedia.contentservice.content.dto.request.PrivateSearchRequestDto;
import dev.animedia.contentservice.content.dto.request.PublicSearchRequestDto;
import dev.animedia.contentservice.content.dto.response.ContentWithTranslationResponseDto;
import dev.animedia.contentservice.content.dto.response.ContentWithTranslationsResponseDto;
import org.springframework.data.domain.Pageable;

public interface ContentSearchService {
	Page<ContentWithTranslationsResponseDto> search(PrivateSearchRequestDto privateSearchRequestDto, Pageable pageable);
	Page<ContentWithTranslationResponseDto> search(PublicSearchRequestDto publicSearchRequestDto, Pageable pageable);
}
