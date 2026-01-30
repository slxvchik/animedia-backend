package dev.animedia.contentservice.genre.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationResponseDto;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationsResponseDto;

import java.util.List;

public interface GenreQueryService {

    Page<GenreWithTranslationsResponseDto> findAll(Pageable pageable);
    List<GenreWithTranslationsResponseDto> findByIds(List<Long> ids);
    List<GenreWithTranslationsResponseDto> findByAliases(List<String> aliases);

    Page<GenreWithTranslationResponseDto> findAllByLanguage(Pageable pageable, String languageCode);
    List<GenreWithTranslationResponseDto> findByIdsAndLanguage(List<Long> ids, String languageCode);
    List<GenreWithTranslationResponseDto> findByAliasesAndLanguage(List<String> aliases, String languageCode);

    boolean existsById(Long id);
    boolean existsAnyByIds(List<Long> ids);
    boolean existsAllByIds(List<Long> ids);

    boolean existsByAlias(String alias);
    boolean existsAnyByAliases(List<String> aliases);
    boolean existsAllByAliases(List<String> aliases);
}
