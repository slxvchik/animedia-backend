package dev.animedia.contentservice.genre.service;

import dev.animedia.contentservice.genre.dto.GenreLanguagePair;
import dev.animedia.contentservice.genre.dto.response.GenreTranslationResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenreTranslationQueryService {

    Page<GenreTranslationResponseDto> findAll(Pageable pageable);

    GenreTranslationResponseDto findById(Long id);
    List<GenreTranslationResponseDto> findByIds(List<Long> ids);

    GenreTranslationResponseDto findByGenreId(Long genreId);
    List<GenreTranslationResponseDto> findByGenreIds(List<Long> genreIds);

    GenreTranslationResponseDto findByLanguageCode(String languageCode);
    List<GenreTranslationResponseDto> findByLanguageCodes(List<String> languageCodes);

    GenreTranslationResponseDto findByGenreIdAndLanguageCode(Long genreId, String languageCode);
    List<GenreTranslationResponseDto> findByGenreIdsAndLanguageCodes(List<GenreLanguagePair> genreIdsLanguageCodes);

    boolean existsById(Long id);
    boolean existsAnyByIds(List<Long> ids);
    boolean existsAllByIds(List<Long> ids);

    boolean existsByGenreIdAndLanguageCode(Long id, String languageCode);
    boolean existsAnyByGenreIdsAndLanguageCodes(List<GenreLanguagePair> genreIdsLanguageCodes);
    boolean existsAllByGenreIdsAndLanguageCodes(List<GenreLanguagePair> genreIdsLanguageCodes);
}
