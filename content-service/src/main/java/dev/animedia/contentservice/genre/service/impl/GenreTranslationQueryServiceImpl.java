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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        if (id == null) throw new EmptyRequestException();

        var genreTranslation = genreTranslationRepository.findById(id)
            .orElseThrow(GenreTranslationNotFoundException::new);

        return genreTranslationMapper.toGenreTranslationResponseDto(genreTranslation);
    }

    @Override
    public Page<GenreTranslationResponseDto> findByIds(List<Long> ids, Pageable pageable) {

        if (ids == null || ids.isEmpty()) throw new EmptyRequestException();

        Set<Long> uniqueIds = new HashSet<>(ids);

        var genreTranslations = genreTranslationRepository.findByIdIn(new ArrayList<>(uniqueIds), pageable);
        if (genreTranslations.getTotalElements() != uniqueIds.size()) throw new GenreTranslationsNotFoundException();

        return genreTranslationMapper.toPageGenreTranslationResponseDto(genreTranslations);
    }

    @Override
    public Page<GenreTranslationResponseDto> findByGenreId(Long genreId, Pageable pageable) {

        if (genreId == null) throw new EmptyRequestException();

        var genreTranslations = genreTranslationRepository.findByGenreId(genreId, pageable);
        if (genreTranslations.getTotalElements() == 0) throw new GenreTranslationsNotFoundException();
        
        return genreTranslationMapper.toPageGenreTranslationResponseDto(genreTranslations);
    }

    @Override
    public Page<GenreTranslationResponseDto> findByGenreIds(List<Long> genreIds, Pageable pageable) {

        if (genreIds == null || genreIds.isEmpty()) throw new EmptyRequestException();

        var genreTranslations = genreTranslationRepository.findByGenreIdIn(genreIds, pageable);
        if (genreTranslations.getTotalElements() == 0) throw new GenreTranslationsNotFoundException();

        return genreTranslationMapper.toPageGenreTranslationResponseDto(genreTranslations);
    }

    @Override
    public Page<GenreTranslationResponseDto> findByLanguageCode(String languageCode, Pageable pageable) {

        if (languageCode == null) throw new EmptyRequestException();

        var genreTranslations = genreTranslationRepository.findByLanguageCode(languageCode, pageable);
        if (genreTranslations.getTotalElements() == 0) throw new GenreTranslationsNotFoundException();

        return genreTranslationMapper.toPageGenreTranslationResponseDto(genreTranslations);
    }

    @Override
    public Page<GenreTranslationResponseDto> findByLanguageCodes(List<String> languageCodes, Pageable pageable) {

        if (languageCodes == null || languageCodes.isEmpty()) throw new EmptyRequestException();

        var genreTranslations = genreTranslationRepository.findByLanguageCodeIn(languageCodes, pageable);
        if (genreTranslations.getTotalElements() == 0) throw new GenreTranslationsNotFoundException();

        return genreTranslationMapper.toPageGenreTranslationResponseDto(genreTranslations);
    }

    @Override
    public GenreTranslationResponseDto findByGenreIdAndLanguageCode(GenreLanguagePair genreLanguagePair) {

        if (genreLanguagePair == null) throw new EmptyRequestException();

        var genreTranslations = genreTranslationRepository.findByGenreIdAndLanguageCode(genreLanguagePair.genreId(), genreLanguagePair.languageCode());
        if (genreTranslations.isEmpty()) throw new GenreTranslationNotFoundException();

        return genreTranslationMapper.toGenreTranslationResponseDto(genreTranslations.get());
    }

    @Override
    public Page<GenreTranslationResponseDto> findByGenreIdsAndLanguageCodes(List<GenreLanguagePair> genreIdsLanguageCodes, Pageable pageable) {

        if (genreIdsLanguageCodes == null || genreIdsLanguageCodes.isEmpty()) throw new EmptyRequestException();

        List<Object[]> genreIdsLanguageCodesTuple = genreIdsLanguageCodes.stream()
            .map(gl -> new Object[] { gl.genreId(), gl.languageCode() })
            .toList();

        var genreTranslations = genreTranslationRepository.findByGenreIdsAndLanguageCodes(genreIdsLanguageCodesTuple, pageable);

        return genreTranslationMapper.toPageGenreTranslationResponseDto(genreTranslations);
    }

    @Override
    public boolean existsById(Long id) {
        return genreTranslationRepository.existsById(id);
    }

    @Override
    public boolean existsAnyByIds(List<Long> ids) {

        if (ids == null) throw new EmptyRequestException();

        return genreTranslationRepository.existsByIdIn(ids);
    }

    @Override
    public boolean existsAllByIds(List<Long> ids) {

        if (ids == null || ids.isEmpty()) throw new EmptyRequestException();

        var uniqueIds = ids.stream().distinct().toList();

        var genreTranslations = genreTranslationRepository.findByIdIn(uniqueIds);

        return uniqueIds.size() == genreTranslations.size();
    }

    @Override
    public boolean existsByGenreIdAndLanguageCode(Long genreId, String languageCode) {

        if (genreId == null || languageCode == null) throw new EmptyRequestException();

        return genreTranslationRepository.existsByGenreIdAndLanguageCode(genreId, languageCode);
    }

    @Override
    public boolean existsAnyByGenreIdsAndLanguageCodes(List<GenreLanguagePair> genreIdsLanguageCodes) {

        if (genreIdsLanguageCodes == null || genreIdsLanguageCodes.isEmpty()) throw new EmptyRequestException();

        List<Object[]> genreIdsLanguageCodesTuple = genreIdsLanguageCodes.stream()
            .map(gl -> new Object[] { gl.genreId(), gl.languageCode() })
            .toList();

        return genreTranslationRepository.existsByGenreIdAndLanguageCodePairs(genreIdsLanguageCodesTuple);
    }

    @Override
    public boolean existsAllByGenreIdsAndLanguageCodes(List<GenreLanguagePair> genreIdsLanguageCodes) {

        if (genreIdsLanguageCodes == null || genreIdsLanguageCodes.isEmpty()) throw new EmptyRequestException();

        var uniqueGenreIdsLanguageCodes = genreIdsLanguageCodes.stream().distinct().toList();

        List<Object[]> genreIdsLanguageCodesTuple = uniqueGenreIdsLanguageCodes.stream()
            .map(gl -> new Object[] { gl.genreId(), gl.languageCode() })
            .toList();

        var genreTranslations = genreTranslationRepository.findByGenreIdsAndLanguageCodes(genreIdsLanguageCodesTuple);

        return uniqueGenreIdsLanguageCodes.size() == genreTranslations.size();
    }
}
