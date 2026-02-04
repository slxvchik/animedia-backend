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
    public Page<GenreTranslationResponseDto> findAll(Pageable pageable) {

        var genreTranslations = genreTranslationRepository.findAll(pageable);

        return genreTranslationMapper.toPageGenreTranslationResponseDto(genreTranslations);
    }

    @Override
    public GenreTranslationResponseDto findById(Long id) {

        var genreTranslation = genreTranslationRepository.findById(id)
            .orElseThrow(GenreTranslationNotFoundException::new);

        return genreTranslationMapper.toGenreTranslationResponseDto(genreTranslation);
    }

    @Override
    public Page<GenreTranslationResponseDto> findByIds(List<Long> ids, Pageable pageable) {

        Set<Long> uniqueIds = new HashSet<>(ids);

        var genreTranslations = genreTranslationRepository.findByIdIn(List.copyOf(uniqueIds), pageable);
        if (genreTranslations.getTotalElements() != uniqueIds.size()) throw new GenreTranslationsNotFoundException();

        return genreTranslationMapper.toPageGenreTranslationResponseDto(genreTranslations);
    }

    @Override
    public Page<GenreTranslationResponseDto> findByGenreId(Long genreId, Pageable pageable) {

        var genreTranslations = genreTranslationRepository.findByGenreId(genreId, pageable);
        if (genreTranslations.getTotalElements() == 0) throw new GenreTranslationsNotFoundException();
        
        return genreTranslationMapper.toPageGenreTranslationResponseDto(genreTranslations);
    }

    @Override
    public Page<GenreTranslationResponseDto> findByGenreIds(List<Long> genreIds, Pageable pageable) {

        var genreTranslations = genreTranslationRepository.findByGenreIdIn(genreIds, pageable);
        if (genreTranslations.getTotalElements() == 0) throw new GenreTranslationsNotFoundException();

        return genreTranslationMapper.toPageGenreTranslationResponseDto(genreTranslations);
    }

    @Override
    public Page<GenreTranslationResponseDto> findByGenreIdsAndLanguageCode(List<Long> genreIds, String languageCode, Pageable pageable) {

        Set<Long> uniqueGenreIds = new HashSet<>(genreIds);

        var genreTranslations = genreTranslationRepository.findByGenreIdInAndLanguageCode(List.copyOf(uniqueGenreIds), languageCode, pageable);
        if (genreTranslations.getTotalElements() != uniqueGenreIds.size()) throw new GenreTranslationsNotFoundException();

        return genreTranslationMapper.toPageGenreTranslationResponseDto(genreTranslations);
    }

    @Override
    public Page<GenreTranslationResponseDto> findByLanguageCode(String languageCode, Pageable pageable) {

        var genreTranslations = genreTranslationRepository.findByLanguageCode(languageCode, pageable);
        if (genreTranslations.getTotalElements() == 0) throw new GenreTranslationsNotFoundException();

        return genreTranslationMapper.toPageGenreTranslationResponseDto(genreTranslations);
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
