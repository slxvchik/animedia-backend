package dev.animedia.contentservice.genre.service;

import dev.animedia.contentservice.genre.dto.request.UpdateGenreRequestDto;
import dev.animedia.contentservice.genre.dto.request.CreateGenreRequestDto;
import dev.animedia.contentservice.genre.dto.response.GenreResponseDto;

import java.util.List;

public interface GenreCommandService {

    GenreResponseDto create(CreateGenreRequestDto createGenreRequestDto);
    List<GenreResponseDto> create(List<CreateGenreRequestDto> createGenresRequestDto);

    GenreResponseDto update(UpdateGenreRequestDto updateGenreRequestDto);
    
    void delete(Long id);
    void delete(List<Long> id);
}
