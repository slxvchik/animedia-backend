package dev.animedia.contentservice.old.genre.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.animedia.contentservice.old.app.exception.AppException;
import dev.animedia.contentservice.old.app.exception.AppExceptionStatus;
import dev.animedia.contentservice.old.genre.GenreConstants;
import dev.animedia.contentservice.old.genre.dto.response.GenreTranslationResponseDto;
import dev.animedia.contentservice.old.genre.mapper.GenreTranslationMapper;
import dev.animedia.contentservice.old.genre.repository.GenreTranslationRepository;
import dev.animedia.contentservice.old.genre.service.GenreTranslationQueryService;

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
    public GenreTranslationResponseDto findById(Long id) {
        var genreTranslation = genreTranslationRepository.findById(id)
            .orElseThrow(() -> new AppException(AppExceptionStatus.NOT_FOUND, GenreConstants.GENRE_TRANSLATION_NOT_FOUND_MESSAGE));
        return genreTranslationMapper.toGenreTranslationResponseDto(genreTranslation);
    }

    @Override
    public List<GenreTranslationResponseDto> findByIds(List<Long> ids) {
        Set<Long> uniqueIds = new HashSet<>(ids);
        var genreTranslations = genreTranslationRepository.findAllById(uniqueIds);
        if (genreTranslations.size() != uniqueIds.size()) throw new AppException(AppExceptionStatus.NOT_FOUND, GenreConstants.GENRE_TRANSLATIONS_NOT_FOUND_MESSAGE);
        return genreTranslationMapper.toGenreTranslationsResponseDto(genreTranslations);
    }

    @Override
    public List<GenreTranslationResponseDto> findByGenreIds(List<Long> genreIds) {
        Set<Long> uniqueGenreIds = new HashSet<>(genreIds);
        var genreTranslations = genreTranslationRepository.findByGenreIdIn(List.copyOf(uniqueGenreIds));
        return genreTranslationMapper.toGenreTranslationsResponseDto(genreTranslations);
    }

    @Override
    public List<GenreTranslationResponseDto> findByGenreIdsAndLanguageCode(List<Long> genreIds, String languageCode) {
        Set<Long> uniqueGenreIds = new HashSet<>(genreIds);
        var genreTranslations = genreTranslationRepository.findByGenreIdInAndLanguageCode(List.copyOf(uniqueGenreIds), languageCode);
        return genreTranslationMapper.toGenreTranslationsResponseDto(genreTranslations);
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
        var genreTranslations = genreTranslationRepository.findAllById(uniqueIds);
        return uniqueIds.size() == genreTranslations.size();
    }

    @Override
    public boolean existsByGenreIdAndLanguageCode(Long genreId, String languageCode) {
        return genreTranslationRepository.existsByGenreIdAndLanguageCode(genreId, languageCode);
    }
}
