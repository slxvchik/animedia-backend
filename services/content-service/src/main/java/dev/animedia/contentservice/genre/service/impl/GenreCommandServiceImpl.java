package dev.animedia.contentservice.genre.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.animedia.contentservice.app.exception.AppException;
import dev.animedia.contentservice.app.exception.AppExceptionStatus;
import dev.animedia.contentservice.genre.GenreConstants;
import dev.animedia.contentservice.genre.dto.request.GenreRequestDto;
import dev.animedia.contentservice.genre.dto.response.GenreResponseDto;
import dev.animedia.contentservice.genre.mapper.GenreMapper;
import dev.animedia.contentservice.genre.model.Genre;
import dev.animedia.contentservice.genre.repository.GenreRepository;
import dev.animedia.contentservice.genre.service.GenreCommandService;
import dev.animedia.contentservice.genre.service.GenreQueryService;

@Service
public class GenreCommandServiceImpl implements GenreCommandService {

    private final GenreRepository genreRepository;

    private final GenreQueryService genreQueryService;

    private final GenreMapper genreMapper;

    @Autowired
    public GenreCommandServiceImpl(
        GenreRepository genreRepository,
        GenreQueryService genreQueryService,
        GenreMapper genreMapper
    ) {
        this.genreRepository = genreRepository;
        this.genreQueryService = genreQueryService;
        this.genreMapper = genreMapper;
    }

    @Override
    public GenreResponseDto create(GenreRequestDto genreRequestDto) {

        var aliasExists = genreQueryService.existsByAlias(genreRequestDto.alias());
        if (aliasExists) throw new AppException(AppExceptionStatus.ALREADY_EXISTS, GenreConstants.GENRE_ALIAS_EXISTS_MESSAGE);

        var genre = genreMapper.toGenre(genreRequestDto);

        var savedGenre = genreRepository.save(genre);

        return genreMapper.toGenreResponseDto(savedGenre);
    }

    @Override
    public List<GenreResponseDto> create(List<GenreRequestDto> genresRequestDto) {

        Set<String> aliases = new HashSet<>();
        List<Genre> genres = new ArrayList<>();

        for (var createGenreDto : genresRequestDto) {
            if (!aliases.add(createGenreDto.alias())) continue;
            genres.add(genreMapper.toGenre(createGenreDto));
        }

        var genreAliasesExists = genreQueryService.existsAnyByAliases(List.copyOf(aliases));
        if (genreAliasesExists) throw new AppException(AppExceptionStatus.ALREADY_EXISTS, GenreConstants.GENRE_ALIAS_EXISTS_MESSAGE);

        var savedGenres = genreRepository.saveAll(genres);

        return genreMapper.toGenresResponseDto(savedGenres);
    }

    @Override
    public GenreResponseDto update(Long id, GenreRequestDto genreRequestDto) {

        var genre = genreRepository.findById(id)
            .orElseThrow(() -> new AppException(AppExceptionStatus.NOT_FOUND, GenreConstants.GENRES_NOT_FOUND_MESSAGE));

        var aliasExists = genreQueryService.existsByAliasExcludingId(genreRequestDto.alias(), id);
        if (aliasExists) throw new AppException(AppExceptionStatus.ALREADY_EXISTS, GenreConstants.GENRE_ALIAS_EXISTS_MESSAGE);

        genre.setAlias(genreRequestDto.alias());
        genre.setSort(genreRequestDto.sort());

        var updatedGenre = genreRepository.save(genre);

        return genreMapper.toGenreResponseDto(updatedGenre);
    }

    @Override
    public void delete(Long id) {
        var genreExists = genreQueryService.existsById(id);
        if (!genreExists) throw new AppException(AppExceptionStatus.NOT_FOUND, GenreConstants.GENRE_NOT_FOUND_MESSAGE);
        genreRepository.deleteById(id);
    }

    @Override
    public void delete(List<Long> ids) {
        var allGenresExists = genreQueryService.existsAllByIds(ids);
        if (!allGenresExists) throw new AppException(AppExceptionStatus.NOT_FOUND, GenreConstants.GENRE_NOT_FOUND_MESSAGE);
        genreRepository.deleteAllByIdInBatch(ids);
    }
}
