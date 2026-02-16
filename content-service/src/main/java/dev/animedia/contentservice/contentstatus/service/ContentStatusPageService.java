package dev.animedia.contentservice.contentstatus.service;

import dev.animedia.contentservice.contentstatus.dto.request.SearchContentStatusAdminRequestDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusWithTranslationsResponseDto;
import org.springframework.data.domain.Page;

public interface ContentStatusPageService {
    Page<ContentStatusWithTranslationsResponseDto> search(SearchContentStatusAdminRequestDto searchContentStatusAdminRequestDto);
}
