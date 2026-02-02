package dev.animedia.contentservice.genre.service.impl;

import java.util.*;

import dev.animedia.contentservice.app.exception.common.EmptyRequestException;
import dev.animedia.contentservice.genre.dto.GenreLanguagePair;
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
        
        if (createGenreTranslationDto == null) throw new EmptyRequestException();

        if (createGenreTranslationDto.name() == null || createGenreTranslationDto.name().isBlank()) throw new GenreTranslationsNameEmptyException();

        var genreExists = genreQueryService.existsById(
            createGenreTranslationDto.genreId()
        );
        if (genreExists) throw new GenreNotFoundException();

        var languageExists = languageQueryService.existsByCode(
            createGenreTranslationDto.languageCode()
        );
        if (languageExists) throw new LanguageCodeNotFoundException();

        var genreTranslationExists = genreTranslationQueryService.existsByGenreIdAndLanguageCode(
            createGenreTranslationDto.genreId(),
            createGenreTranslationDto.languageCode()
        );
        if (genreTranslationExists) throw new GenreTranslationAlreadyExistsException();

        var genreTranslation = genreTranslationMapper.toGenreTranslation(createGenreTranslationDto);

        var savedGenreTranslation = genreTranslationRepository.save(genreTranslation);

        return genreTranslationMapper.toGenreTranslationResponseDto(savedGenreTranslation);
    }

    @Override
    public List<GenreTranslationResponseDto> create(List<CreateGenreTranslationRequestDto> createGenreTranslationsRequestDto) {

        if (createGenreTranslationsRequestDto.isEmpty()) throw new EmptyRequestException();

        List<CreateGenreTranslationRequestDto> uniqueCreateGenreTranslationsRequestDto = new ArrayList<>();

        Set<GenreLanguagePair> genreLanguagePairs = new HashSet<>();
        List<Long> genreIds = new ArrayList<>();
        List<String> languageCodes = new ArrayList<>();

        for (var createGenreTranslationDto : createGenreTranslationsRequestDto) {

            if (createGenreTranslationDto.name() == null || createGenreTranslationDto.name().isBlank()) throw new GenreTranslationsNameEmptyException();

            var genreLanguagePair = new GenreLanguagePair(
                createGenreTranslationDto.genreId(),
                createGenreTranslationDto.languageCode()
            );

            if (!genreLanguagePairs.add(genreLanguagePair)) continue;

            uniqueCreateGenreTranslationsRequestDto.add(createGenreTranslationDto);

            genreIds.add(createGenreTranslationDto.genreId());
            languageCodes.add(createGenreTranslationDto.languageCode());
        }

        var anyGenreTranslationExists = genreTranslationQueryService.existsAnyByGenreIdsAndLanguageCodes(List.copyOf(genreLanguagePairs));
        if (anyGenreTranslationExists) throw new GenreTranslationsAlreadyExistsException();

        var allGenresExists = genreQueryService.existsAllByIds(genreIds);
        if (!allGenresExists) throw new GenresNotFoundException();

        var allLanguageCodesExists = languageQueryService.existsAllByCodes(languageCodes);
        if (!allLanguageCodesExists) throw new LanguageCodeNotFoundException();

        var genreTranslations = genreTranslationMapper.toGenreTranslationsFromCreate(uniqueCreateGenreTranslationsRequestDto);

        var savedGenreTranslations = genreTranslationRepository.saveAll(genreTranslations);

        return genreTranslationMapper.toGenreTranslationsResponseDto(savedGenreTranslations);
    }

    @Override
    public GenreTranslationResponseDto update(UpdateGenreTranslationRequestDto updateGenreTranslationDto) {

        if (updateGenreTranslationDto == null) throw new EmptyRequestException();

        if (updateGenreTranslationDto.name() == null || updateGenreTranslationDto.name().isBlank()) {
            throw new GenreTranslationNameEmptyException();
        }

        var genreTranslationDb = genreTranslationRepository.findById(updateGenreTranslationDto.id())
                .orElseThrow(GenreTranslationNotFoundException::new);

        genreTranslationDb.setName(updateGenreTranslationDto.name());
        genreTranslationDb.setDescription(updateGenreTranslationDto.description());

        var savedGenreTranslation = genreTranslationRepository.save(genreTranslationDb);

        return genreTranslationMapper.toGenreTranslationResponseDto(savedGenreTranslation);
    }

    @Override
    public void delete(Long genreTranslationId) {

        if (genreTranslationId == null) throw new EmptyRequestException();

        var genreTranslationExists = genreTranslationQueryService.existsById(genreTranslationId);
        if (!genreTranslationExists) throw new GenreTranslationNotFoundException();

        genreTranslationRepository.deleteById(genreTranslationId);
    }

    @Override
    public void delete(List<Long> genreTranslationIds) {

        if (genreTranslationIds.isEmpty()) throw new EmptyRequestException();

        var allGenreTranslationsExists = genreTranslationQueryService.existsAllByIds(genreTranslationIds);
        if (!allGenreTranslationsExists) throw new GenreTranslationsNotFoundException();

        genreTranslationRepository.deleteAllByIdInBatch(genreTranslationIds);
    }

}