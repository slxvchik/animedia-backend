package dev.animedia.contentservice.genre.service;

import java.util.List;

import dev.animedia.contentservice.genre.dto.request.CreateGenreTranslationRequestDto;
import dev.animedia.contentservice.genre.dto.request.UpdateGenreTranslationRequestDto;
import dev.animedia.contentservice.genre.dto.response.GenreTranslationResponseDto;

public interface GenreTranslationCommandService {

    GenreTranslationResponseDto createTranslation(CreateGenreTranslationRequestDto createGenreTranslationDto);
    List<GenreTranslationResponseDto> createTranslations(List<CreateGenreTranslationRequestDto> createGenreTranslationsDto);

    GenreTranslationResponseDto updateTranslation(UpdateGenreTranslationRequestDto updateGenreTranslationDto);

    void deleteTranslation(Long genreTranslationId);
    void deleteTranslations(List<Long> genreTranslationIds);
}
