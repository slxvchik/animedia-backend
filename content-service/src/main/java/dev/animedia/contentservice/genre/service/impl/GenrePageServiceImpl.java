package dev.animedia.contentservice.genre.service.impl;

import dev.animedia.contentservice.genre.dto.response.GenreResponseDto;
import dev.animedia.contentservice.genre.dto.response.GenreTranslationResponseDto;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationResponseDto;
import dev.animedia.contentservice.genre.mapper.GenreMapper;
import dev.animedia.contentservice.genre.model.Genre;
import dev.animedia.contentservice.genre.model.GenreTranslation;
import dev.animedia.contentservice.genre.service.GenrePageService;
import dev.animedia.contentservice.genre.service.GenreQueryService;
import dev.animedia.contentservice.genre.service.GenreTranslationPageService;
import dev.animedia.contentservice.genre.service.GenreTranslationQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationsResponseDto;
import dev.animedia.contentservice.genre.repository.GenreRepository;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

@Service
public class GenrePageServiceImpl implements GenrePageService {

    private final GenreRepository genreRepository;

    private final GenreTranslationQueryService genreTranslationQueryService;
    private final GenreMapper genreMapper;
    private final GenreTranslationPageService genreTranslationPageService;
    private final GenreQueryService genreQueryService;

    @Autowired
    public GenrePageServiceImpl(
        GenreRepository genreRepository,
        GenreTranslationQueryService genreTranslationQueryService,
        GenreMapper genreMapper,
        GenreTranslationPageService genreTranslationPageService,
        GenreQueryService genreQueryService
    ) {
        this.genreRepository = genreRepository;
        this.genreTranslationQueryService = genreTranslationQueryService;
        this.genreMapper = genreMapper;
        this.genreTranslationPageService = genreTranslationPageService;
        this.genreQueryService = genreQueryService;
    }

    @Override
    public Page<GenreWithTranslationsResponseDto> findAll(Pageable pageable) {

        var genres = genreRepository.findAll(pageable);

        if (genres.getContent().isEmpty()) return Page.empty(pageable);

        var genreIds = genres.map(Genre::getId).toList();
        var genresResponseDto = genreMapper.toGenresResponseDto(genres.getContent());

        var genresTranslationsResponseDto = genreTranslationQueryService.findByGenreIds(genreIds);

        List<GenreWithTranslationsResponseDto> genresWithTranslationsResponseDto = genreMapper.toGenresWithTranslationsResponseDto(genresResponseDto, genresTranslationsResponseDto);

        return PageableExecutionUtils.getPage(
            genresWithTranslationsResponseDto,
            pageable,
            genres::getTotalElements
        );
    }

    @Override
    public Page<GenreWithTranslationsResponseDto> findByAlias(String alias, Pageable pageable) {

        var genres = genreRepository.findByAliasLike(alias, pageable);

        if (genres.getContent().isEmpty()) return Page.empty(pageable);

        var genreIds = genres.map(Genre::getId).toList();
        var genresResponseDto = genreMapper.toGenresResponseDto(genres.getContent());

        var genresTranslationsResponseDto = genreTranslationQueryService.findByGenreIds(genreIds);

        List<GenreWithTranslationsResponseDto> genresWithTranslationsResponseDto = genreMapper.toGenresWithTranslationsResponseDto(genresResponseDto, genresTranslationsResponseDto);

        return PageableExecutionUtils.getPage(
            genresWithTranslationsResponseDto,
            pageable,
            genres::getTotalElements
        );
    }

    @Override
    public Page<GenreWithTranslationsResponseDto> findByAliases(List<String> aliases, Pageable pageable) {

        var uniqueAliases = new HashSet<>(aliases);

        var genres = genreRepository.findByAliasIn(List.copyOf(uniqueAliases), pageable);

        if (genres.getContent().isEmpty()) return Page.empty(pageable);

        var genreIds = genres.map(Genre::getId).toList();
        var genresResponseDto = genreMapper.toGenresResponseDto(genres.getContent());

        var genresTranslationsResponseDto = genreTranslationQueryService.findByGenreIds(genreIds);

        List<GenreWithTranslationsResponseDto> genresWithTranslationsResponseDto = genreMapper.toGenresWithTranslationsResponseDto(genresResponseDto, genresTranslationsResponseDto);

        return PageableExecutionUtils.getPage(
            genresWithTranslationsResponseDto,
            pageable,
            genres::getTotalElements
        );
    }

    @Override
    public Page<GenreWithTranslationResponseDto> findByAlias(String alias, String languageCode, Pageable pageable) {

        var genres = genreRepository.findByAliasLike(alias, pageable);

        if (genres.getContent().isEmpty()) return Page.empty(pageable);

        var genreIds = genres.map(Genre::getId).toList();
        var genresResponseDto = genreMapper.toGenresResponseDto(genres.getContent());

        var genresTranslationsResponseDto = genreTranslationQueryService.findByGenreIdsAndLanguageCode(genreIds, languageCode);

        List<GenreWithTranslationResponseDto> genresWithTranslationsResponseDto = genreMapper.toGenresWithTranslationResponseDto(genresResponseDto, genresTranslationsResponseDto);

        return PageableExecutionUtils.getPage(
            genresWithTranslationsResponseDto,
            pageable,
            genres::getTotalElements
        );
    }

    @Override
    public Page<GenreWithTranslationResponseDto> findByLanguage(String languageCode, Pageable pageable) {

        var genreTranslations = genreTranslationPageService.findByLanguageCode(languageCode, pageable);

        if (genreTranslations.getContent().isEmpty()) Page.empty(pageable);

        var genreIds = genreTranslations.map(GenreTranslationResponseDto::genreId).toList();

        List<GenreResponseDto> genres = genreQueryService.findByIds(genreIds);

        List<GenreWithTranslationResponseDto> genresWithTranslation = genreMapper.toGenresWithTranslationResponseDto(
            genres, genreTranslations.getContent()
        );

        return PageableExecutionUtils.getPage(
            genresWithTranslation,
            pageable,
            genreTranslations::getTotalElements
        );
    }

    @Override
    public Page<GenreWithTranslationResponseDto> findByAliasesAndLanguage(List<String> aliases, String languageCode, Pageable pageable) {

        Set<String> uniqueAliases = new HashSet<>(aliases);

        Page<Genre> genres = genreRepository.findByAliasIn(List.copyOf(uniqueAliases), pageable);

        if (genres.getContent().isEmpty()) return Page.empty(pageable);

        var foundGenresResponseDto = genreMapper.toGenresResponseDto(genres.getContent());
        var foundGenresId = genres.map(Genre::getId).toList();

        List<GenreTranslationResponseDto> genresTranslation = genreTranslationQueryService.findByGenreIdsAndLanguageCode(foundGenresId, languageCode);

        List<GenreWithTranslationResponseDto> genresWithTranslationResponseDto = genreMapper.toGenresWithTranslationResponseDto(
            foundGenresResponseDto,
            genresTranslation
        );

        return PageableExecutionUtils.getPage(
            genresWithTranslationResponseDto,
            pageable,
            genres::getTotalElements
        );
    }
}
