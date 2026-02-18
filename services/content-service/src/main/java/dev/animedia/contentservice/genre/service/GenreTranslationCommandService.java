package dev.animedia.contentservice.genre.service;

import java.util.List;

import dev.animedia.contentservice.genre.dto.request.GenreTranslationRequestDto;
import dev.animedia.contentservice.genre.dto.response.GenreTranslationResponseDto;

public interface GenreTranslationCommandService {
    GenreTranslationResponseDto create(GenreTranslationRequestDto genreTranslationDto);
    GenreTranslationResponseDto update(Long id, GenreTranslationRequestDto genreTranslationDto);
    void delete(Long genreTranslationId);
    void delete(List<Long> genreTranslationIds);
}
