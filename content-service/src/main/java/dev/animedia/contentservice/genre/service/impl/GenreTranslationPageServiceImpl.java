package dev.animedia.contentservice.genre.service.impl;

import dev.animedia.contentservice.genre.dto.response.GenreTranslationResponseDto;
import dev.animedia.contentservice.genre.mapper.GenreTranslationMapper;
import dev.animedia.contentservice.genre.repository.GenreTranslationRepository;
import dev.animedia.contentservice.genre.service.GenreTranslationPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GenreTranslationPageServiceImpl implements GenreTranslationPageService {

	private final GenreTranslationRepository genreTranslationRepository;
	private final GenreTranslationMapper genreTranslationMapper;

	@Autowired
	public GenreTranslationPageServiceImpl(
		GenreTranslationRepository genreTranslationRepository,
		GenreTranslationMapper genreTranslationMapper
	) {
		this.genreTranslationRepository = genreTranslationRepository;
		this.genreTranslationMapper = genreTranslationMapper;
	}

	@Override
	public Page<GenreTranslationResponseDto> findAll(Pageable pageable) {
		var genresTranslations = genreTranslationRepository.findAll(pageable);
		return genreTranslationMapper.toPageGenreTranslationResponseDto(genresTranslations);
	}

	@Override
	public Page<GenreTranslationResponseDto> findByNameLike(String name, Pageable pageable) {
		var genresTranslations = genreTranslationRepository.findByNameLike(name, pageable);
		return genreTranslationMapper.toPageGenreTranslationResponseDto(genresTranslations);
	}

	@Override
	public Page<GenreTranslationResponseDto> findByGenreId(Long genreId, Pageable pageable) {
		var genreTranslations = genreTranslationRepository.findByGenreId(genreId, pageable);
		return genreTranslationMapper.toPageGenreTranslationResponseDto(genreTranslations);
	}

	@Override
	public Page<GenreTranslationResponseDto> findByLanguageCode(String languageCode, Pageable pageable) {
		var genreTranslations = genreTranslationRepository.findByLanguageCode(languageCode, pageable);
		return genreTranslationMapper.toPageGenreTranslationResponseDto(genreTranslations);
	}
}
