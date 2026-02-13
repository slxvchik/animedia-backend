package dev.animedia.contentservice.genre.service.impl;

import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationResponseDto;
import dev.animedia.contentservice.genre.mapper.GenreMapper;
import dev.animedia.contentservice.genre.repository.GenreNativeRepository;
import dev.animedia.contentservice.genre.service.GenrePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationsResponseDto;

import java.util.List;

@Service
public class GenrePageServiceImpl implements GenrePageService {

    private final GenreNativeRepository genreNativeRepository;

    private final GenreMapper genreMapper;

    @Autowired
    public GenrePageServiceImpl(
        GenreNativeRepository genreNativeRepository,
        GenreMapper genreMapper
    ) {
	    this.genreNativeRepository = genreNativeRepository;
        this.genreMapper = genreMapper;
    }

    @Override
    public Page<GenreWithTranslationsResponseDto> search(String alias, List<String> languageCodes, String name, Pageable pageable) {
        return genreNativeRepository.searchPage(alias, languageCodes, name, pageable);
    }

    @Override
    public Page<GenreWithTranslationResponseDto> search(String alias, String languageCode, String name, Pageable pageable) {
        Page<GenreWithTranslationsResponseDto> genresWithTranslationsResponseDto = genreNativeRepository.searchPage(alias, List.of(languageCode), name, pageable);
        var genresTranslations = genresWithTranslationsResponseDto.getContent();
        var genresTranslation = genreMapper.toGenresWithTranslationResponseDto(genresTranslations);
        return PageableExecutionUtils.getPage(
            genresTranslation,
            pageable,
            genresWithTranslationsResponseDto::getTotalElements
        );
    }

}
