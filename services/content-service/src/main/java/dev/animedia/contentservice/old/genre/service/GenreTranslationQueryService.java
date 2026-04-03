package dev.animedia.contentservice.old.genre.service;

import dev.animedia.contentservice.old.genre.dto.response.GenreTranslationResponseDto;

import java.util.List;

public interface GenreTranslationQueryService {

    GenreTranslationResponseDto findById(Long id);
    List<GenreTranslationResponseDto> findByIds(List<Long> ids);

    List<GenreTranslationResponseDto> findByGenreIds(List<Long> genreIds);
    List<GenreTranslationResponseDto> findByGenreIdsAndLanguageCode(List<Long> genreIds, String languageCode);

    boolean existsById(Long id);
    boolean existsAnyByIds(List<Long> ids);
    boolean existsAllByIds(List<Long> ids);
    boolean existsByGenreIdAndLanguageCode(Long id, String languageCode);
}
