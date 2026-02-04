package dev.animedia.contentservice.genre.service.impl;

import dev.animedia.contentservice.app.exception.common.EmptyRequestException;
import dev.animedia.contentservice.genre.exception.*;
import dev.animedia.contentservice.genre.mapper.GenreMapper;
import dev.animedia.contentservice.genre.model.Genre;
import dev.animedia.contentservice.genre.service.GenreQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.animedia.contentservice.genre.dto.request.CreateGenreRequestDto;
import dev.animedia.contentservice.genre.dto.request.UpdateGenreRequestDto;
import dev.animedia.contentservice.genre.dto.response.GenreResponseDto;
import dev.animedia.contentservice.genre.repository.GenreRepository;
import dev.animedia.contentservice.genre.service.GenreCommandService;

import java.util.*;

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
    public GenreResponseDto create(CreateGenreRequestDto createGenreRequestDto) {

        /**
         * Вынести в Lombok
         * if (createGenreRequestDto.alias() == null || createGenreRequestDto.alias().isBlank()) throw new GenreAliasEmptyException();
         * if (!ALIAS_PATTERN.matcher(createGenreRequestDto.alias()).matches()) throw new GenreAliasInvalidCharsException();
         */

        var aliasExists = genreQueryService.existsByAlias(createGenreRequestDto.alias());
        if (aliasExists) throw new GenreAliasExistsException();

        var genre = genreMapper.toGenre(createGenreRequestDto);

        var savedGenre = genreRepository.save(genre);

        return genreMapper.toGenreResponseDto(savedGenre);
    }

    @Override
    public List<GenreResponseDto> create(List<CreateGenreRequestDto> createGenresRequestDto) {

        Set<String> aliases = new HashSet<>();

        List<Genre> genres = new ArrayList<>();

        for (var createGenreDto : createGenresRequestDto) {

            if (!aliases.add(createGenreDto.alias())) continue;

            genres.add(genreMapper.toGenre(createGenreDto));
        }

        var savedGenres = genreRepository.saveAll(genres);

        return genreMapper.toGenresResponseDto(savedGenres);
    }

    @Override
    public GenreResponseDto update(UpdateGenreRequestDto updateGenreRequestDto) {

        var genre = genreRepository.findById(updateGenreRequestDto.id())
            .orElseThrow(GenreNotFoundException::new);

        genre.setAlias(updateGenreRequestDto.alias());
        genre.setSort(updateGenreRequestDto.sort());

        var updatedGenre = genreRepository.save(genre);

        return genreMapper.toGenreResponseDto(updatedGenre);
    }

    @Override
    public void delete(Long id) {

        var genreExists = genreQueryService.existsById(id);
        if (!genreExists) throw new GenreNotFoundException();

        genreRepository.deleteById(id);
    }

    @Override
    public void delete(List<Long> ids) {

        var allGenresExists = genreQueryService.existsAllByIds(ids);
        if (!allGenresExists) throw new GenresNotFoundException();

        genreRepository.deleteAllByIdInBatch(ids);
    }
}
