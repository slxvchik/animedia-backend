package dev.animedia.contentservice.status.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.animedia.contentservice.status.dto.response.ContentStatusWithTranslationResponseDto;
import dev.animedia.contentservice.status.dto.response.ContentStatusWithTranslationListResponseDto;

public interface ContentStatusPageService {
    Page<ContentStatusWithTranslationListResponseDto> search(
        List<Long> contentStatusIds,
        List<String> languageCodes,
        String alias,
        String name,
        Pageable pageable
    );
    Page<ContentStatusWithTranslationResponseDto> search (
        String languageCode,
        String alias,
        String name,
        Pageable pageable
    );
}
