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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public GenreResponseDto update(UpdateGenreRequestDto genre) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
