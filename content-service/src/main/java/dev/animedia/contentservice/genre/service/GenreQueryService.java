package dev.animedia.contentservice.genre.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationResponseDto;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationsResponseDto;

import java.util.List;

public interface GenreQueryService {
    Page<GenreWithTranslationResponseDto> findAll(Pageable pageable, String languageCode);
    List<GenreWithTranslationResponseDto> findByIds(List<Long> ids, String languageCode);
    List<GenreWithTranslationResponseDto> findByAliases(List<String> aliases, String languageCode);

    Page<GenreWithTranslationsResponseDto> findAll(Pageable pageable);
    List<GenreWithTranslationsResponseDto> findByIds(List<Long> ids);
    List<GenreWithTranslationsResponseDto> findByAliases(List<String> aliases);
}
