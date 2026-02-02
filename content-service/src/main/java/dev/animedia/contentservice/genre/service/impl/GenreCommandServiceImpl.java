package dev.animedia.contentservice.genre.service.impl;

import dev.animedia.contentservice.app.exception.common.EmptyRequestException;
import dev.animedia.contentservice.genre.exception.*;
import dev.animedia.contentservice.genre.mapper.GenreMapper;
import dev.animedia.contentservice.genre.model.Genre;
import dev.animedia.contentservice.genre.service.GenreQueryService;
import dev.animedia.contentservice.genre.service.GenreTranslationQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.animedia.contentservice.genre.dto.request.CreateGenreRequestDto;
import dev.animedia.contentservice.genre.dto.request.UpdateGenreRequestDto;
import dev.animedia.contentservice.genre.dto.response.GenreResponseDto;
import dev.animedia.contentservice.genre.repository.GenreRepository;
import dev.animedia.contentservice.genre.repository.GenreTranslationRepository;
import dev.animedia.contentservice.genre.service.GenreCommandService;

import java.util.*;
import java.util.regex.Pattern;

@Service
public class GenreCommandServiceImpl implements GenreCommandService {

    private final GenreRepository genreRepository;

    private final GenreQueryService genreQueryService;
    private final GenreTranslationQueryService genreTranslationQueryService;

    private final GenreMapper genreMapper;

    private static final Pattern ALIAS_PATTERN = Pattern.compile("^[a-z]{2,10}(?:-[a-z]{1,10}){0,5}$");

    @Autowired
    public GenreCommandServiceImpl(
        GenreRepository genreRepository,
        GenreQueryService genreQueryService,
        GenreTranslationQueryService genreTranslationQueryService,
        GenreMapper genreMapper
    ) {
        this.genreRepository = genreRepository;
        this.genreQueryService = genreQueryService;
        this.genreTranslationQueryService = genreTranslationQueryService;
        this.genreMapper = genreMapper;
    }

    @Override
    public GenreResponseDto create(CreateGenreRequestDto createGenreRequestDto) {

        if (createGenreRequestDto == null) throw new EmptyRequestException();

        if (createGenreRequestDto.alias() == null || createGenreRequestDto.alias().isBlank()) throw new GenreAliasEmptyException();

        if (!ALIAS_PATTERN.matcher(createGenreRequestDto.alias()).matches()) throw new GenreAliasInvalidCharsException();

        var aliasExists = genreQueryService.existsByAlias(createGenreRequestDto.alias());
        if (aliasExists) throw new GenreAliasExistsException();

        var genre = genreMapper.toGenre(createGenreRequestDto);

        var savedGenre = genreRepository.save(genre);

        return genreMapper.toGenreResponseDto(savedGenre);
    }

    @Override
    public List<GenreResponseDto> create(List<CreateGenreRequestDto> createGenresRequestDto) {

        if (createGenresRequestDto == null || createGenresRequestDto.isEmpty()) throw new EmptyRequestException();

        Set<String> aliases = new HashSet<>();

        List<Genre> genres = new ArrayList<>();

        for (var createGenreDto : createGenresRequestDto) {

            if (createGenreDto.alias() == null || createGenreDto.alias().isBlank()) throw new GenreAliasEmptyException();

            if (!aliases.add(createGenreDto.alias())) continue;

            if (!ALIAS_PATTERN.matcher(createGenreDto.alias()).matches()) throw new GenreAliasInvalidCharsException();

            genres.add(genreMapper.toGenre(createGenreDto));
        }

        var savedGenres = genreRepository.saveAll(genres);

        return genreMapper.toGenresResponseDto(savedGenres);
    }

    @Override
    public GenreResponseDto update(UpdateGenreRequestDto updateGenreRequestDto) {

        if (updateGenreRequestDto == null) throw new EmptyRequestException();

        var genre = genreRepository.findById(updateGenreRequestDto.id())
            .orElseThrow(GenreNotFoundException::new);

        if (updateGenreRequestDto.alias() == null || updateGenreRequestDto.alias().isBlank()) throw new GenreAliasEmptyException();

        if (!ALIAS_PATTERN.matcher(updateGenreRequestDto.alias()).matches()) throw new GenreAliasInvalidCharsException();

        genre.setAlias(updateGenreRequestDto.alias());
        genre.setSort(updateGenreRequestDto.sort());

        var updatedGenre = genreRepository.save(genre);

        return genreMapper.toGenreResponseDto(updatedGenre);
    }

    @Override
    public void delete(Long id) {

        if (id == null) throw new EmptyRequestException();

        var genreExists = genreQueryService.existsById(id);
        if (!genreExists) throw new GenreNotFoundException();

        genreRepository.deleteById(id);
    }

    @Override
    public void delete(List<Long> ids) {

        if (ids == null || ids.isEmpty()) throw new EmptyRequestException();

        var allGenresExists = genreQueryService.existsAllByIds(ids);
        if (!allGenresExists) throw new GenresNotFoundException();

        genreRepository.deleteAllByIdInBatch(ids);
    }
}
