package dev.animedia.contentservice.genre.service.impl;

import java.util.*;

import dev.animedia.contentservice.genre.exception.*;
import dev.animedia.contentservice.genre.service.GenreQueryService;
import dev.animedia.contentservice.genre.service.GenreTranslationQueryService;
import dev.animedia.contentservice.language.service.LanguageQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.animedia.contentservice.genre.dto.request.CreateGenreTranslationRequestDto;
import dev.animedia.contentservice.genre.dto.request.UpdateGenreTranslationRequestDto;
import dev.animedia.contentservice.genre.dto.response.GenreTranslationResponseDto;
import dev.animedia.contentservice.genre.mapper.GenreTranslationMapper;
import dev.animedia.contentservice.genre.repository.GenreTranslationRepository;
import dev.animedia.contentservice.genre.service.GenreTranslationCommandService;
import dev.animedia.contentservice.language.exception.LanguageCodeNotFoundException;

@Service
public class GenreTranslationCommandServiceImpl implements GenreTranslationCommandService {

    private final GenreTranslationMapper genreTranslationMapper;

    private final GenreTranslationRepository genreTranslationRepository;

    private final GenreQueryService genreQueryService;
    private final LanguageQueryService languageQueryService;
    private final GenreTranslationQueryService genreTranslationQueryService;

    @Autowired
    public GenreTranslationCommandServiceImpl(
        GenreTranslationMapper genreTranslationMapper,
        GenreTranslationRepository genreTranslationRepository,
        GenreQueryService genreQueryService,
        LanguageQueryService languageQueryService,
        GenreTranslationQueryService genreTranslationQueryService
    ) {
        this.genreTranslationMapper = genreTranslationMapper;

        this.genreTranslationRepository = genreTranslationRepository;

        this.genreQueryService = genreQueryService;
        this.languageQueryService = languageQueryService;
        this.genreTranslationQueryService = genreTranslationQueryService;
    }

    @Override
    public GenreTranslationResponseDto create(CreateGenreTranslationRequestDto createGenreTranslationDto) {
        
        var genreExists = genreQueryService.existsById(
            createGenreTranslationDto.genreId()
        );
        if (!genreExists) throw new GenreNotFoundException();

        var languageExists = languageQueryService.existsByCode(
            createGenreTranslationDto.languageCode()
        );
        if (!languageExists) throw new LanguageCodeNotFoundException();

        var genreTranslationExists = genreTranslationQueryService.existsByGenreIdAndLanguageCode(
            createGenreTranslationDto.genreId(),
            createGenreTranslationDto.languageCode()
        );
        if (!genreTranslationExists) throw new GenreTranslationExistsException();

        var genreTranslation = genreTranslationMapper.toGenreTranslation(createGenreTranslationDto);

        var savedGenreTranslation = genreTranslationRepository.save(genreTranslation);

        return genreTranslationMapper.toGenreTranslationResponseDto(savedGenreTranslation);
    }

    @Override
    public GenreTranslationResponseDto update(UpdateGenreTranslationRequestDto updateGenreTranslationDto) {

        var genreTranslationDb = genreTranslationRepository.findById(updateGenreTranslationDto.id())
                .orElseThrow(GenreTranslationNotFoundException::new);

        genreTranslationDb.setName(updateGenreTranslationDto.name());
        genreTranslationDb.setDescription(updateGenreTranslationDto.description());

        var savedGenreTranslation = genreTranslationRepository.save(genreTranslationDb);

        return genreTranslationMapper.toGenreTranslationResponseDto(savedGenreTranslation);
    }

    @Override
    public void delete(Long genreTranslationId) {

        var genreTranslationExists = genreTranslationQueryService.existsById(genreTranslationId);
        if (!genreTranslationExists) throw new GenreTranslationNotFoundException();

        genreTranslationRepository.deleteById(genreTranslationId);
    }

    @Override
    public void delete(List<Long> genreTranslationIds) {

        var allGenreTranslationsExists = genreTranslationQueryService.existsAllByIds(genreTranslationIds);
        if (!allGenreTranslationsExists) throw new GenreTranslationsNotFoundException();

        genreTranslationRepository.deleteAllByIdInBatch(genreTranslationIds);
    }

}