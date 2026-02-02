package dev.animedia.contentservice.genre.service;

import java.util.Collection;
import java.util.List;

import dev.animedia.contentservice.genre.dto.request.CreateGenreTranslationRequestDto;
import dev.animedia.contentservice.genre.dto.request.UpdateGenreTranslationRequestDto;
import dev.animedia.contentservice.genre.dto.response.GenreTranslationResponseDto;

public interface GenreTranslationCommandService {

    GenreTranslationResponseDto create(CreateGenreTranslationRequestDto createGenreTranslationDto);
    List<GenreTranslationResponseDto> create(List<CreateGenreTranslationRequestDto> createGenreTranslationsDto);

    GenreTranslationResponseDto update(UpdateGenreTranslationRequestDto updateGenreTranslationDto);

    void delete(Long genreTranslationId);
    void delete(List<Long> genreTranslationIds);
}
