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

    @Autowired
    public GenreQueryServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Page<GenreWithTranslationsResponseDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<GenreWithTranslationsResponseDto> findByIds(List<Long> ids) {
        return List.of();
    }

    @Override
    public List<GenreWithTranslationsResponseDto> findByAliases(List<String> aliases) {
        return List.of();
    }

    @Override
    public Page<GenreWithTranslationResponseDto> findAllByLanguage(Pageable pageable, String languageCode) {
        return null;
    }

    @Override
    public List<GenreWithTranslationResponseDto> findByIdsAndLanguage(List<Long> ids, String languageCode) {
        return List.of();
    }

    @Override
    public List<GenreWithTranslationResponseDto> findByAliasesAndLanguage(List<String> aliases, String languageCode) {
        return List.of();
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }

    @Override
    public boolean existsAnyByIds(List<Long> ids) {
        return false;
    }

    @Override
    public boolean existsAllByIds(List<Long> ids) {
        return false;
    }

    @Override
    public boolean existsByAlias(String alias) {
        return false;
    }

    @Override
    public boolean existsAnyByAliases(List<String> aliases) {
        return false;
    }

    @Override
    public boolean existsAllByAliases(List<String> aliases) {
        return false;
    }

}
