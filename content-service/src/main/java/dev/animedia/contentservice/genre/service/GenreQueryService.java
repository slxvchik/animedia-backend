package dev.animedia.contentservice.genre.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationResponseDto;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationsResponseDto;

import java.util.List;

public interface GenreQueryService {

    Page<GenreWithTranslationsResponseDto> findAll(Pageable pageable);
    Page<GenreWithTranslationsResponseDto> findByIds(List<Long> ids, Pageable pageable);
    Page<GenreWithTranslationsResponseDto> findByAliases(List<String> aliases, Pageable pageable);

    Page<GenreWithTranslationResponseDto> findByLanguage(String languageCode, Pageable pageable);
    Page<GenreWithTranslationResponseDto> findByIdsAndLanguageCode(List<Long> ids, String languageCode, Pageable pageable);
    Page<GenreWithTranslationResponseDto> findByAliasesAndLanguage(List<String> aliases, String languageCode, Pageable pageable);

    boolean existsById(Long id);
    boolean existsAnyByIds(List<Long> ids);
    boolean existsAllByIds(List<Long> ids);

    boolean existsByAlias(String alias);
    boolean existsAnyByAliases(List<String> aliases);
    boolean existsAllByAliases(List<String> aliases);
}
