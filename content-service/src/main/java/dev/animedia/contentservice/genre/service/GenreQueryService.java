package dev.animedia.contentservice.genre.service;

import dev.animedia.contentservice.genre.dto.response.GenreResponseDto;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationResponseDto;

import java.util.List;

public interface GenreQueryService {

    GenreResponseDto findById(Long id);
    List<GenreResponseDto> findByIds(List<Long> ids);
    List<GenreResponseDto> findByAliases(List<String> aliases);

    List<GenreWithTranslationResponseDto> findByIdsAndLanguageCode(List<Long> ids, String languageCode);
    List<GenreWithTranslationResponseDto> findByAliasesAndLanguage(List<String> aliases, String languageCode);

    boolean existsById(Long id);
    boolean existsAnyByIds(List<Long> ids);
    boolean existsAllByIds(List<Long> ids);

    boolean existsByAlias(String alias);
    boolean existsAnyByAliases(List<String> aliases);
    boolean existsAllByAliases(List<String> aliases);

    boolean existsByAliasExcludingId(String alias, Long id);
}
