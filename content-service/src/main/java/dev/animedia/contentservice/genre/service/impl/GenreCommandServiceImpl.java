package dev.animedia.contentservice.genre.service.impl;

import dev.animedia.contentservice.genre.service.GenreTranslationQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.animedia.contentservice.genre.dto.request.CreateGenreRequestDto;
import dev.animedia.contentservice.genre.dto.request.UpdateGenreRequestDto;
import dev.animedia.contentservice.genre.dto.response.GenreResponseDto;
import dev.animedia.contentservice.genre.repository.GenreRepository;
import dev.animedia.contentservice.genre.repository.GenreTranslationRepository;
import dev.animedia.contentservice.genre.service.GenreCommandService;

import java.util.List;

@Service
public class GenreCommandServiceImpl implements GenreCommandService {

    private final GenreRepository genreRepository;
    private final GenreTranslationQueryService genreTranslationQueryService;

    @Autowired
    public GenreCommandServiceImpl(
        GenreRepository genreRepository,
        GenreTranslationQueryService genreTranslationQueryService
    ) {
        this.genreRepository = genreRepository;
        this.genreTranslationQueryService = genreTranslationQueryService;
    }

    @Override
    public GenreResponseDto create(CreateGenreRequestDto genre) {
        return null;
    }

    @Override
    public List<GenreResponseDto> create(List<CreateGenreRequestDto> genre) {
        return List.of();
    }

    @Override
    public GenreResponseDto update(UpdateGenreRequestDto genre) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void delete(List<Long> id) {

    }
}
