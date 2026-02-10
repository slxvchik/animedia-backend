package dev.animedia.contentservice.contentstatus.service;

import dev.animedia.contentservice.contentstatus.dto.request.ContentStatusSearchRequestDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusWithTranslationsResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ContentStatusPageService {
    Page<ContentStatusWithTranslationsResponseDto> search(ContentStatusSearchRequestDto contentStatusSearchRequestDto);
}
