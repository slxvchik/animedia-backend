package dev.animedia.contentservice.genre.service;

import dev.animedia.contentservice.genre.dto.request.UpdateGenreRequestDto;
import dev.animedia.contentservice.genre.dto.request.CreateGenreRequestDto;
import dev.animedia.contentservice.genre.dto.response.GenreResponseDto;

import java.util.List;

public interface GenreCommandService {

    GenreResponseDto create(CreateGenreRequestDto genre);
    List<GenreResponseDto> create(List<CreateGenreRequestDto> genre);

    GenreResponseDto update(UpdateGenreRequestDto genre);
    
    void delete(Long id);
    void delete(List<Long> id);
}
