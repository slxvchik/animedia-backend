package dev.animedia.contentservice.content.repository;

import dev.animedia.contentservice.content.dto.request.PrivateSearchRequestDto;
import dev.animedia.contentservice.content.dto.request.PublicSearchRequestDto;
import dev.animedia.contentservice.content.dto.response.ContentWithTranslationResponseDto;
import dev.animedia.contentservice.content.dto.response.ContentWithTranslationsResponseDto;
import dev.animedia.contentservice.content.model.Content;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ContentSearchRepository {
	Page<UUID> search(PrivateSearchRequestDto searchRequestDto);
	Page<UUID> search(PublicSearchRequestDto searchRequestDto, String languageCode);
}
