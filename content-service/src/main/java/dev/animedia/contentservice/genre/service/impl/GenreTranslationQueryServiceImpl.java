package dev.animedia.contentservice.genre.service.impl;

import dev.animedia.contentservice.app.exception.common.EmptyRequestException;
import dev.animedia.contentservice.genre.dto.GenreLanguagePair;
import dev.animedia.contentservice.genre.dto.response.GenreTranslationResponseDto;
import dev.animedia.contentservice.genre.exception.GenreTranslationNotFoundException;
import dev.animedia.contentservice.genre.exception.GenreTranslationsNotFoundException;
import dev.animedia.contentservice.genre.mapper.GenreTranslationMapper;
import dev.animedia.contentservice.genre.repository.GenreTranslationRepository;
import dev.animedia.contentservice.genre.service.GenreTranslationQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GenreTranslationQueryServiceImpl implements GenreTranslationQueryService {

    private final GenreTranslationRepository genreTranslationRepository;

    private final GenreTranslationMapper genreTranslationMapper;

    @Autowired
    public GenreTranslationQueryServiceImpl(
        GenreTranslationRepository genreTranslationRepository,
        GenreTranslationMapper genreTranslationMapper
    ) {
        this.genreTranslationRepository = genreTranslationRepository;
        this.genreTranslationMapper = genreTranslationMapper;
    }

    @Override
    public List<GenreTranslationResponseDto> findAll() {

        var genreTranslations = genreTranslationRepository.findAll();

        return genreTranslationMapper.toGenreTranslationResponseDto(genreTranslations);
    }

    @Override
    public GenreTranslationResponseDto findById(Long id) {

        var genreTranslation = genreTranslationRepository.findById(id)
            .orElseThrow(GenreTranslationNotFoundException::new);

        return genreTranslationMapper.toGenreTranslationResponseDto(genreTranslation);
    }

    @Override
    public List<GenreTranslationResponseDto> findByIds(List<Long> ids) {

        Set<Long> uniqueIds = new HashSet<>(ids);

        var genreTranslations = genreTranslationRepository.findByIdIn(List.copyOf(uniqueIds));
        if (genreTranslations.size() != uniqueIds.size()) throw new GenreTranslationsNotFoundException();

        return genreTranslationMapper.toGenreTranslationResponseDto(genreTranslations);
    }

    @Override
    public List<GenreTranslationResponseDto> findByGenreId(Long genreId) {

        var genreTranslations = genreTranslationRepository.findByGenreId(genreId);
        if (genreTranslations.isEmpty()) throw new GenreTranslationsNotFoundException();
        
        return genreTranslationMapper.toGenreTranslationResponseDto(genreTranslations);
    }

    @Override
    public List<GenreTranslationResponseDto> findByGenreIdsAndLanguageCode(List<Long> genreIds, String languageCode) {

        Set<Long> uniqueGenreIds = new HashSet<>(genreIds);

        var genreTranslations = genreTranslationRepository.findByGenreIdInAndLanguageCode(List.copyOf(uniqueGenreIds), languageCode);
        if (genreTranslations.size() != uniqueGenreIds.size()) throw new GenreTranslationsNotFoundException();

        return genreTranslationMapper.toGenreTranslationResponseDto(genreTranslations);
    }

    @Override
    public boolean existsById(Long id) {
        return genreTranslationRepository.existsById(id);
    }

    @Override
    public boolean existsAnyByIds(List<Long> ids) {
        return genreTranslationRepository.existsByIdIn(ids);
    }

    @Override
    public boolean existsAllByIds(List<Long> ids) {

        Set<Long> uniqueIds = new HashSet<>(ids);

        var genreTranslations = genreTranslationRepository.findByIdIn(List.copyOf(uniqueIds));

        return uniqueIds.size() == genreTranslations.size();
    }

    @Override
    public boolean existsByGenreIdAndLanguageCode(Long genreId, String languageCode) {

        return genreTranslationRepository.existsByGenreIdAndLanguageCode(genreId, languageCode);
    }

    @Override
    public boolean existsAnyByGenreIdsAndLanguageCodes(List<GenreLanguagePair> genreIdsLanguageCodes) {

        List<Object[]> genreIdsLanguageCodesTuple = genreIdsLanguageCodes.stream()
            .map(gl -> new Object[] { gl.genreId(), gl.languageCode() })
            .toList();

        return genreTranslationRepository.existsByGenreIdAndLanguageCodePairs(genreIdsLanguageCodesTuple);
    }

    @Override
    public boolean existsAllByGenreIdsAndLanguageCodes(List<GenreLanguagePair> genreIdsLanguageCodes) {

        Set<GenreLanguagePair> uniqueGenreIdsLanguageCodes = new HashSet<>(genreIdsLanguageCodes);

        List<Object[]> genreIdsLanguageCodesTuple = uniqueGenreIdsLanguageCodes.stream()
            .map(gl -> new Object[] { gl.genreId(), gl.languageCode() })
            .toList();

        var genreTranslations = genreTranslationRepository.findByGenreIdsAndLanguageCodes(genreIdsLanguageCodesTuple);

        return uniqueGenreIdsLanguageCodes.size() == genreTranslations.size();
    }
}
