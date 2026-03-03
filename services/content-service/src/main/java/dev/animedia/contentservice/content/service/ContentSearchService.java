package dev.animedia.contentservice.content.service;

import dev.animedia.contentservice.content.dto.request.PrivateSearchRequestDto;
import dev.animedia.contentservice.content.dto.request.PublicSearchRequestDto;
import dev.animedia.contentservice.content.dto.response.ContentWithTranslationResponseDto;
import dev.animedia.contentservice.content.dto.response.ContentWithTranslationsResponseDto;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ContentSearchService {
	Page<ContentWithTranslationsResponseDto> search(PrivateSearchRequestDto privateSearchRequestDto);
	Page<ContentWithTranslationResponseDto> search(PublicSearchRequestDto publicSearchRequestDto);
}
