package dev.animedia.contentservice.status.service;

import dev.animedia.contentservice.status.dto.response.ContentStatusWithTranslationResponseDto;
import dev.animedia.contentservice.status.dto.response.ContentStatusWithTranslationsResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContentStatusPageService {
    Page<ContentStatusWithTranslationsResponseDto> search(
        List<Long> contentStatusIds,
        List<String> languageCodes,
        List<String> aliases,
        List<String> names,
        Pageable pageable
    );
    Page<ContentStatusWithTranslationResponseDto> search (
        String languageCode,
        List<String> aliases,
        List<String> names,
        Pageable pageable
    );
}
