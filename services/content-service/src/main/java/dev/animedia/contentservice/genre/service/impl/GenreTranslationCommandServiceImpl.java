package dev.animedia.contentservice.genre.service.impl;

import java.util.*;

import dev.animedia.contentservice.app.exception.AppException;
import dev.animedia.contentservice.genre.GenreConstants;
import dev.animedia.contentservice.genre.service.GenreQueryService;
import dev.animedia.contentservice.genre.service.GenreTranslationQueryService;
import io.grpc.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.animedia.contentservice.genre.dto.request.GenreTranslationRequestDto;
import dev.animedia.contentservice.genre.dto.response.GenreTranslationResponseDto;
import dev.animedia.contentservice.genre.mapper.GenreTranslationMapper;
import dev.animedia.contentservice.genre.repository.GenreTranslationRepository;
import dev.animedia.contentservice.genre.service.GenreTranslationCommandService;

@Service
public class GenreTranslationCommandServiceImpl implements GenreTranslationCommandService {

    private final GenreTranslationMapper genreTranslationMapper;

    private final GenreTranslationRepository genreTranslationRepository;

    private final GenreQueryService genreQueryService;
    private final GenreTranslationQueryService genreTranslationQueryService;

    @Autowired
    public GenreTranslationCommandServiceImpl(
        GenreTranslationMapper genreTranslationMapper,
        GenreTranslationRepository genreTranslationRepository,
        GenreQueryService genreQueryService,
        GenreTranslationQueryService genreTranslationQueryService
    ) {
        this.genreTranslationMapper = genreTranslationMapper;

        this.genreTranslationRepository = genreTranslationRepository;

        this.genreQueryService = genreQueryService;
        this.genreTranslationQueryService = genreTranslationQueryService;
    }

    @Override
    public GenreTranslationResponseDto create(GenreTranslationRequestDto genreTranslationDto) {

        var genreExists = genreQueryService.existsById(
            genreTranslationDto.genreId()
        );
        if (!genreExists) throw new AppException(Status.Code.NOT_FOUND, GenreConstants.GENRE_NOT_FOUND_MESSAGE);

        var genreTranslationExists = genreTranslationQueryService.existsByGenreIdAndLanguageCode(
            genreTranslationDto.genreId(),
            genreTranslationDto.languageCode()
        );
        if (genreTranslationExists) throw new AppException(Status.Code.ALREADY_EXISTS, GenreConstants.GENRE_TRANSLATION_EXISTS_MESSAGE);

        var genreTranslation = genreTranslationMapper.toGenreTranslation(genreTranslationDto);

        var savedGenreTranslation = genreTranslationRepository.save(genreTranslation);

        return genreTranslationMapper.toGenreTranslationResponseDto(savedGenreTranslation);
    }

    @Override
    public GenreTranslationResponseDto update(Long id, GenreTranslationRequestDto genreTranslationDto) {

        var genreTranslationDb = genreTranslationRepository.findById(id)
                .orElseThrow(() -> new AppException(Status.Code.NOT_FOUND, GenreConstants.GENRE_NOT_FOUND_MESSAGE));

        genreTranslationDb.setName(genreTranslationDto.name());
        genreTranslationDb.setDescription(genreTranslationDto.description());

        var savedGenreTranslation = genreTranslationRepository.save(genreTranslationDb);

        return genreTranslationMapper.toGenreTranslationResponseDto(savedGenreTranslation);
    }

    @Override
    public void delete(Long genreTranslationId) {

        var genreTranslationExists = genreTranslationQueryService.existsById(genreTranslationId);
        if (!genreTranslationExists) throw new AppException(Status.Code.NOT_FOUND, GenreConstants.GENRE_NOT_FOUND_MESSAGE);

        genreTranslationRepository.deleteById(genreTranslationId);
    }

    @Override
    public void delete(List<Long> genreTranslationIds) {

        var allGenreTranslationsExists = genreTranslationQueryService.existsAllByIds(genreTranslationIds);
        if (!allGenreTranslationsExists) throw new AppException(Status.Code.NOT_FOUND, GenreConstants.GENRES_NOT_FOUND_MESSAGE);

        genreTranslationRepository.deleteAllByIdInBatch(genreTranslationIds);
    }

}