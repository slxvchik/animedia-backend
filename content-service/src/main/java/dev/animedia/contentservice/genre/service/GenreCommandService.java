package dev.animedia.contentservice.genre.service;

import dev.animedia.contentservice.genre.dto.request.UpdateGenreRequestDto;
import dev.animedia.contentservice.genre.dto.request.CreateGenreRequestDto;
import dev.animedia.contentservice.genre.dto.response.GenreResponseDto;

public interface GenreCommandService {

    GenreResponseDto create(CreateGenreRequestDto genre);
    GenreResponseDto update(UpdateGenreRequestDto genre);
    void delete(Long id);
}
