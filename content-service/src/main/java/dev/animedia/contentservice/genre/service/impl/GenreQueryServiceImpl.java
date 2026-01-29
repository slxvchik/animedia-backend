package dev.animedia.contentservice.genre.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationResponseDto;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationsResponseDto;
import dev.animedia.contentservice.genre.repository.GenreRepository;
import dev.animedia.contentservice.genre.repository.GenreTranslationRepository;
import dev.animedia.contentservice.genre.service.GenreQueryService;

import java.util.List;

@Service
public class GenreQueryServiceImpl implements GenreQueryService {

    private final GenreRepository genreRepository;
    private final GenreTranslationRepository genreTranslationRepository;

    @Autowired
    public GenreQueryServiceImpl(GenreRepository genreRepository, GenreTranslationRepository genreTranslationRepository) {
        this.genreRepository = genreRepository;
        this.genreTranslationRepository = genreTranslationRepository;
    }

    @Override
    public Page<GenreWithTranslationResponseDto> findAll(Pageable pageable, String languageCode) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<GenreWithTranslationsResponseDto> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<GenreWithTranslationResponseDto> findByAliases(List<String> aliases, String languageCode) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<GenreWithTranslationsResponseDto> findByAliases(List<String> aliases) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<GenreWithTranslationResponseDto> findByIds(List<Long> ids, String languageCode) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<GenreWithTranslationsResponseDto> findByIds(List<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }
}
