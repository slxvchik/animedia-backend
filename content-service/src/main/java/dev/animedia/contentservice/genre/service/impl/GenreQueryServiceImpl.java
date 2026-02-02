package dev.animedia.contentservice.genre.service.impl;

import dev.animedia.contentservice.app.exception.common.EmptyRequestException;
import dev.animedia.contentservice.genre.dto.response.GenreTranslationResponseDto;
import dev.animedia.contentservice.genre.mapper.GenreMapper;
import dev.animedia.contentservice.genre.model.Genre;
import dev.animedia.contentservice.genre.service.GenreTranslationQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationResponseDto;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationsResponseDto;
import dev.animedia.contentservice.genre.repository.GenreRepository;
import dev.animedia.contentservice.genre.service.GenreQueryService;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

@Service
public class GenreQueryServiceImpl implements GenreQueryService {

    private final GenreRepository genreRepository;

    private final GenreTranslationQueryService genreTranslationQueryService;
    private final GenreMapper genreMapper;

    @Autowired
    public GenreQueryServiceImpl(
        GenreRepository genreRepository,
        GenreTranslationQueryService genreTranslationQueryService,
        GenreMapper genreMapper
    ) {
        this.genreRepository = genreRepository;
        this.genreTranslationQueryService = genreTranslationQueryService;
        this.genreMapper = genreMapper;
    }

    @Override
    public Page<GenreWithTranslationsResponseDto> findAll(Pageable pageable) {

        var genres = genreRepository.findAll(Pageable.unpaged());

        var genreIds = genres.stream()
            .map(Genre::getId)
            .toList();

        var genreTranslations = genreTranslationQueryService.findByGenreIds(genreIds, pageable);

        List<GenreWithTranslationsResponseDto> genresWithTranslationsResponseDto = genreMapper.toGenresWithTranslationsResponseDto(
            genres.getContent(),
            genreTranslations.getContent()
        );

        return PageableExecutionUtils.getPage(
            genresWithTranslationsResponseDto,
            pageable,
            genreTranslations::getTotalElements
        );
    }

    @Override
    public Page<GenreWithTranslationsResponseDto> findByIds(List<Long> ids, Pageable pageable) {

        if (ids == null || ids.isEmpty()) throw new EmptyRequestException();

        Set<Long> uniqueIds = new HashSet<>(ids);

        var genres = genreRepository.findByIdIn(List.copyOf(uniqueIds), Pageable.unpaged());

        var genreIds = genres.stream()
            .map(Genre::getId)
            .toList();

        var genreTranslations = genreTranslationQueryService.findByGenreIds(genreIds, pageable);

        List<GenreWithTranslationsResponseDto> genresWithTranslationsResponseDto = genreMapper.toGenresWithTranslationsResponseDto(
            genres.getContent(),
            genreTranslations.getContent()
        );

        return PageableExecutionUtils.getPage(
            genresWithTranslationsResponseDto,
            pageable,
            genreTranslations::getTotalElements
        );
    }

    @Override
    public Page<GenreWithTranslationsResponseDto> findByAliases(List<String> aliases, Pageable pageable) {

        if (aliases == null || aliases.isEmpty()) throw new EmptyRequestException();

        Set<String> uniqueAliases = new HashSet<>(aliases);

        var genres = genreRepository.findByAliasIn(List.copyOf(uniqueAliases), Pageable.unpaged());

        var genreIds = genres.stream()
            .map(Genre::getId)
            .toList();

        var genreTranslations = genreTranslationQueryService.findByGenreIds(genreIds, pageable);

        List<GenreWithTranslationsResponseDto> genresWithTranslationsResponseDto = genreMapper.toGenresWithTranslationsResponseDto(
            genres.getContent(),
            genreTranslations.getContent()
        );

        return PageableExecutionUtils.getPage(
            genresWithTranslationsResponseDto,
            pageable,
            genreTranslations::getTotalElements
        );
    }

    @Override
    public Page<GenreWithTranslationResponseDto> findByLanguage(String languageCode, Pageable pageable) {

        if (languageCode == null) throw new EmptyRequestException();

        Page<GenreTranslationResponseDto> genreTranslations = genreTranslationQueryService.findByLanguageCode(languageCode, pageable);

        var genreIds = genreTranslations.stream()
            .map(GenreTranslationResponseDto::genreId)
            .distinct()
            .toList();

        var genres = genreRepository.findByIdIn(genreIds, Pageable.unpaged());

        List<GenreWithTranslationResponseDto> genresWithTranslationResponseDto = genreMapper.toGenresWithTranslationResponseDto(
            genres.getContent(),
            genreTranslations.getContent()
        );

        return PageableExecutionUtils.getPage(
            genresWithTranslationResponseDto,
            pageable,
            genreTranslations::getTotalElements
        );
    }

    @Override
    public Page<GenreWithTranslationResponseDto> findByIdsAndLanguageCode(List<Long> ids, String languageCode, Pageable pageable) {

        if (ids == null || ids.isEmpty() || languageCode == null) throw new EmptyRequestException();

        Page<GenreTranslationResponseDto> genresTranslation = genreTranslationQueryService.findByGenreIdsAndLanguageCode(ids, languageCode, pageable);

        var genreIds = genresTranslation.stream()
            .map(GenreTranslationResponseDto::genreId)
            .distinct()
            .toList();

        var genres = genreRepository.findByIdIn(genreIds, Pageable.unpaged());

        List<GenreWithTranslationResponseDto> genresWithTranslationResponseDto = genreMapper.toGenresWithTranslationResponseDto(
            genres.getContent(),
            genresTranslation.getContent()
        );

        return PageableExecutionUtils.getPage(
            genresWithTranslationResponseDto,
            pageable,
            genresTranslation::getTotalElements
        );
    }

    @Override
    public Page<GenreWithTranslationResponseDto> findByAliasesAndLanguage(List<String> aliases, String languageCode, Pageable pageable) {

        if (aliases == null || aliases.isEmpty() || languageCode == null) throw new EmptyRequestException();

        Set<String> uniqueAliases = new HashSet<>(aliases);

        var genres = genreRepository.findByAliasIn(List.copyOf(uniqueAliases), Pageable.unpaged());

        var genreIds = genres.stream()
            .map(Genre::getId)
            .distinct()
            .toList();

        Page<GenreTranslationResponseDto> genresTranslation = genreTranslationQueryService.findByGenreIdsAndLanguageCode(genreIds, languageCode, pageable);

        List<GenreWithTranslationResponseDto> genresWithTranslationResponseDto = genreMapper.toGenresWithTranslationResponseDto(
            genres.getContent(),
            genresTranslation.getContent()
        );

        return PageableExecutionUtils.getPage(
            genresWithTranslationResponseDto,
            pageable,
            genresTranslation::getTotalElements
        );
    }

    @Override
    public boolean existsById(Long id) {

        if (id == null) throw new EmptyRequestException();

        return genreRepository.existsById(id);
    }

    @Override
    public boolean existsAnyByIds(List<Long> ids) {

        if (ids == null || ids.isEmpty()) throw new EmptyRequestException();

        return genreRepository.existsByIdIn(ids);
    }

    @Override
    public boolean existsAllByIds(List<Long> ids) {

        if (ids == null || ids.isEmpty()) throw new EmptyRequestException();

        Set<Long> uniqueIds = new HashSet<>(ids);

        var genres = genreRepository.findAllById(uniqueIds);

        return uniqueIds.size() == genres.size();
    }

    @Override
    public boolean existsByAlias(String alias) {

        if (alias == null) throw new EmptyRequestException();

        return genreRepository.existsByAlias(alias);
    }

    @Override
    public boolean existsAnyByAliases(List<String> aliases) {

        if (aliases == null || aliases.isEmpty()) throw new EmptyRequestException();

        return genreRepository.existsByAliasIn(aliases);
    }

    @Override
    public boolean existsAllByAliases(List<String> aliases) {

        if (aliases == null || aliases.isEmpty()) throw new EmptyRequestException();

        Set<String> uniqueAliases = new HashSet<>(aliases);

        var genres = genreRepository.findByAliasIn(List.copyOf(uniqueAliases));

        return uniqueAliases.size() == genres.size();
    }

}
