package dev.animedia.contentservice.genre.service;

import dev.animedia.contentservice.genre.dto.GenreLanguagePair;
import dev.animedia.contentservice.genre.dto.response.GenreTranslationResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenreTranslationQueryService {

    Page<GenreTranslationResponseDto> findAll(Pageable pageable);

    GenreTranslationResponseDto findById(Long id);
    Page<GenreTranslationResponseDto> findByIds(List<Long> ids, Pageable pageable);

    Page<GenreTranslationResponseDto> findByGenreId(Long genreId, Pageable pageable);

    Page<GenreTranslationResponseDto> findByGenreIds(List<Long> genreIds, Pageable pageable);
    Page<GenreTranslationResponseDto> findByGenreIdsAndLanguageCode(List<Long> genreIds, String languageCode, Pageable pageable);

    Page<GenreTranslationResponseDto> findByLanguageCode(String languageCode, Pageable pageable);

    boolean existsById(Long id);
    boolean existsAnyByIds(List<Long> ids);
    boolean existsAllByIds(List<Long> ids);

    boolean existsByGenreIdAndLanguageCode(Long id, String languageCode);
    boolean existsAnyByGenreIdsAndLanguageCodes(List<GenreLanguagePair> genreIdsLanguageCodes);
    boolean existsAllByGenreIdsAndLanguageCodes(List<GenreLanguagePair> genreIdsLanguageCodes);
}
