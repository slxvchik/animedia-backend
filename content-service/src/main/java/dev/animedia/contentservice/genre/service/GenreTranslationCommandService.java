package dev.animedia.contentservice.genre.service;

import java.util.List;

import dev.animedia.contentservice.genre.dto.request.CreateGenreTranslationRequestDto;
import dev.animedia.contentservice.genre.dto.request.UpdateGenreTranslationRequestDto;
import dev.animedia.contentservice.genre.dto.response.GenreTranslationResponseDto;

public interface GenreTranslationCommandService {
    GenreTranslationResponseDto addTranslation(CreateGenreTranslationRequestDto genreTranslation);
    List<GenreTranslationResponseDto> addTranslations(List<CreateGenreTranslationRequestDto> genreTranslations);
    GenreTranslationResponseDto updateTranslation(UpdateGenreTranslationRequestDto genreTranslation);
    void removeTranslation(Long genreTranslation);
    void removeTranslations(List<Long> genreTranslations);
}
