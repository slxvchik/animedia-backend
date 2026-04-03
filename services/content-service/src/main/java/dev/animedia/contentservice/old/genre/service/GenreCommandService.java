package dev.animedia.contentservice.old.genre.service;

import dev.animedia.contentservice.old.genre.dto.request.GenreRequestDto;
import dev.animedia.contentservice.old.genre.dto.response.GenreResponseDto;

import java.util.List;

public interface GenreCommandService {

    GenreResponseDto create(GenreRequestDto genreRequestDto);
    List<GenreResponseDto> create(List<GenreRequestDto> genresRequestDto);

    GenreResponseDto update(Long id, GenreRequestDto genreRequestDto);
    
    void delete(Long id);
    void delete(List<Long> id);
}
