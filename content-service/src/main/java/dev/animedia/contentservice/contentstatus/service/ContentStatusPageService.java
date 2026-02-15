package dev.animedia.contentservice.contentstatus.service;

import dev.animedia.contentservice.contentstatus.dto.request.ContentStatusAdminSearchRequestDto;
import dev.animedia.contentservice.contentstatus.dto.request.ContentStatusUserSearchRequestDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusWithTranslationsResponseDto;
import org.springframework.data.domain.Page;

public interface ContentStatusPageService {
    Page<ContentStatusWithTranslationsResponseDto> search(ContentStatusAdminSearchRequestDto contentStatusAdminSearchRequestDto);
}
