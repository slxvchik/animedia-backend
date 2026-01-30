package dev.animedia.contentservice.genre.service.impl;

import dev.animedia.contentservice.genre.dto.GenreLanguagePair;
import dev.animedia.contentservice.genre.dto.response.GenreTranslationResponseDto;
import dev.animedia.contentservice.genre.mapper.GenreTranslationMapper;
import dev.animedia.contentservice.genre.model.GenreTranslation;
import dev.animedia.contentservice.genre.repository.GenreTranslationRepository;
import dev.animedia.contentservice.genre.service.GenreTranslationQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return null;
    }

    @Override
    public List<GenreTranslationResponseDto> findByIds(List<Long> ids) {
        return List.of();
    }

    @Override
    public GenreTranslationResponseDto findByGenreId(Long genreId) {
        return null;
    }

    @Override
    public List<GenreTranslationResponseDto> findByGenreIds(List<Long> genreIds) {
        return List.of();
    }

    @Override
    public GenreTranslationResponseDto findByLanguageCode(String languageCode) {
        return null;
    }

    @Override
    public List<GenreTranslationResponseDto> findByLanguageCodes(List<String> languageCodes) {
        return List.of();
    }

    @Override
    public GenreTranslationResponseDto findByGenreIdAndLanguageCode(Long genreId, String languageCode) {
        return null;
    }

    @Override
    public List<GenreTranslationResponseDto> findByGenreIdsAndLanguageCodes(List<GenreLanguagePair> genreIdsLanguageCodes) {
        return List.of();
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }

    @Override
    public boolean existsAnyByIds(List<Long> ids) {
        return false;
    }

    @Override
    public boolean existsAllByIds(List<Long> ids) {
        return false;
    }

    @Override
    public boolean existsByGenreIdAndLanguageCode(Long id, String languageCode) {
        return false;
    }

    @Override
    public boolean existsAnyByGenreIdsAndLanguageCodes(List<GenreLanguagePair> genreIdsLanguageCodes) {
        return false;
    }

    @Override
    public boolean existsAllByGenreIdsAndLanguageCodes(List<GenreLanguagePair> genreIdsLanguageCodes) {
        return false;
    }
}
