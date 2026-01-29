package dev.animedia.contentservice.genre.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import dev.animedia.contentservice.genre.exception.GenreTranslationAlreadyExists;
import dev.animedia.contentservice.genre.model.Genre;
import dev.animedia.contentservice.genre.model.GenreTranslation;
import dev.animedia.contentservice.language.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.animedia.contentservice.genre.dto.request.CreateGenreTranslationRequestDto;
import dev.animedia.contentservice.genre.dto.request.UpdateGenreTranslationRequestDto;
import dev.animedia.contentservice.genre.dto.response.GenreTranslationResponseDto;
import dev.animedia.contentservice.genre.exception.GenreNotFoundException;
import dev.animedia.contentservice.genre.exception.GenreTranslationInvalidFields;
import dev.animedia.contentservice.genre.mapper.GenreTranslationMapper;
import dev.animedia.contentservice.genre.repository.GenreRepository;
import dev.animedia.contentservice.genre.repository.GenreTranslationRepository;
import dev.animedia.contentservice.genre.service.GenreTranslationCommandService;
import dev.animedia.contentservice.language.LanguageRepository;
import dev.animedia.contentservice.language.exception.LanguageCodeNotFoundException;
import org.yaml.snakeyaml.util.Tuple;

@Service
public class GenreTranslationCommandServiceImpl implements GenreTranslationCommandService {

    private GenreTranslationMapper genreTranslationMapper;

    private GenreTranslationRepository genreTranslationRepository;
    private GenreRepository genreRepository;
    private LanguageRepository languageRepository;

    @Autowired
    public GenreTranslationCommandServiceImpl(
        GenreTranslationMapper genreTranslationMapper,
        GenreTranslationRepository genreTranslationRepository,
        GenreRepository genreRepository,
        LanguageRepository languageRepository
    ) {
        this.genreTranslationMapper = genreTranslationMapper;
        this.genreTranslationRepository = genreTranslationRepository;
        this.genreRepository = genreRepository;
        this.languageRepository = languageRepository;
    }

    @Override
    public GenreTranslationResponseDto addTranslation(CreateGenreTranslationRequestDto genreTranslationDto) {
        
        if (genreTranslationDto == null) {
            throw new GenreTranslationInvalidFields();
        }
        
        var genreExists = genreRepository.existsById(genreTranslationDto.genreId());
        if (!genreExists) {
            throw new GenreNotFoundException();
        }

        var languageExists = languageRepository.existsById(genreTranslationDto.languageCode());
        if (!languageExists) {
            throw new LanguageCodeNotFoundException();
        }

        var genreTranslationExists = genreTranslationRepository.existsByGenreIdAndLanguageCode(
                genreTranslationDto.genreId(),
                genreTranslationDto.languageCode()
        );

        if (genreTranslationExists) {
            throw new GenreTranslationAlreadyExists();
        }

        var genreTranslation = genreTranslationMapper.toGenreTranslation(genreTranslationDto);

        genreTranslation = genreTranslationRepository.save(genreTranslation);

        return genreTranslationMapper.toGenreTranslationResponseDto(genreTranslation);
    }

    @Override
    public List<GenreTranslationResponseDto> addTranslations(List<CreateGenreTranslationRequestDto> genreTranslationsDto) {

        List<GenreTranslation> genreTranslations = genreTranslationMapper.toGenreTranslationsFromCreate(genreTranslationsDto);

        List<Tuple<Genre, Language>> genreTranslationsTuples = genreTranslations.stream()
                .map(gt -> new Tuple<>(gt.getGenre(), gt.getLanguage()))
                .collect(Collectors.toUnmodifiableList());

        genreTranslationRepository.existsByGenreIdAndLanguageCodePairs(null);

        return null;
    }

    @Override
    public void removeTranslation(Long genreTranslation) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeTranslations(List<Long> genreTranslations) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public GenreTranslationResponseDto updateTranslation(UpdateGenreTranslationRequestDto genreTranslationDto) {
        // TODO Auto-generated method stub
        return null;
    }

}