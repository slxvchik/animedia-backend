package dev.animedia.contentservice.genre.service;

import dev.animedia.contentservice.genre.dto.GenreLanguagePair;
import dev.animedia.contentservice.genre.dto.response.GenreTranslationResponseDto;

import java.util.List;

public interface GenreTranslationQueryService {


    List<GenreTranslationResponseDto> findAll();

    GenreTranslationResponseDto findById(Long id);
    List<GenreTranslationResponseDto> findByIds(List<Long> ids);

    List<GenreTranslationResponseDto> findByLanguageCode(String languageCode);
    List<GenreTranslationResponseDto> findByGenreId(Long genreId);
    List<GenreTranslationResponseDto> findByGenreIdsAndLanguageCode(List<Long> genreIds, String languageCode);

    boolean existsById(Long id);
    boolean existsAnyByIds(List<Long> ids);
    boolean existsAllByIds(List<Long> ids);

    boolean existsByGenreIdAndLanguageCode(Long id, String languageCode);
    boolean existsAnyByGenreIdsAndLanguageCodes(List<GenreLanguagePair> genreIdsLanguageCodes);
    boolean existsAllByGenreIdsAndLanguageCodes(List<GenreLanguagePair> genreIdsLanguageCodes);
}
