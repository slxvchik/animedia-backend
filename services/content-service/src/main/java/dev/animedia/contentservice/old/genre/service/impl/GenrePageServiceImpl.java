package dev.animedia.contentservice.old.genre.service.impl;

import dev.animedia.contentservice.old.genre.dto.response.GenreWithTranslationResponseDto;
import dev.animedia.contentservice.old.genre.mapper.GenreMapper;
import dev.animedia.contentservice.old.genre.repository.GenreNativeRepository;
import dev.animedia.contentservice.old.genre.service.GenrePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import dev.animedia.contentservice.old.genre.dto.response.GenreWithTranslationListResponseDto;

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
    public Page<GenreWithTranslationListResponseDto> search(List<String> aliases, List<String> names, List<String> languageCodes, Pageable pageable) {
        return genreNativeRepository.searchPage(aliases, names, languageCodes, pageable);
    }

    @Override
    public Page<GenreWithTranslationResponseDto> search(List<String> aliases, List<String> names, String languageCode, Pageable pageable) {
        Page<GenreWithTranslationListResponseDto> genresWithTranslationsResponseDto = genreNativeRepository.searchPage(aliases, names, List.of(languageCode), pageable);
        var genresTranslations = genresWithTranslationsResponseDto.getContent();
        var genresTranslation = genreMapper.toGenreListWithTranslationResponseDto(genresTranslations);
        return PageableExecutionUtils.getPage(
            genresTranslation,
            genresWithTranslationsResponseDto.getPageable(),
            genresWithTranslationsResponseDto::getTotalElements
        );
    }

}
