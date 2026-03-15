package dev.animedia.contentservice.genre.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.animedia.contentservice.app.exception.AppException;
import dev.animedia.contentservice.app.exception.AppExceptionStatus;
import dev.animedia.contentservice.genre.GenreConstants;
import dev.animedia.contentservice.genre.dto.response.GenreResponseDto;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationResponseDto;
import dev.animedia.contentservice.genre.mapper.GenreMapper;
import dev.animedia.contentservice.genre.model.Genre;
import dev.animedia.contentservice.genre.repository.GenreRepository;
import dev.animedia.contentservice.genre.service.GenreQueryService;
import dev.animedia.contentservice.genre.service.GenreTranslationQueryService;

@Service
public class GenreQueryServiceImpl implements GenreQueryService {

	private final GenreRepository genreRepository;

	private final GenreMapper genreMapper;
	private final GenreTranslationQueryService genreTranslationQueryService;

	@Autowired
	public GenreQueryServiceImpl(
		GenreRepository genreRepository,
		GenreMapper genreMapper,
		GenreTranslationQueryService genreTranslationQueryService
	) {
		this.genreRepository = genreRepository;
		this.genreMapper = genreMapper;
		this.genreTranslationQueryService = genreTranslationQueryService;
	}

	@Override
	public GenreResponseDto findById(Long id) {
		var genre = genreRepository.findById(id)
			.orElseThrow(() -> new AppException(AppExceptionStatus.NOT_FOUND, GenreConstants.GENRE_NOT_FOUND_MESSAGE));
		return genreMapper.toGenreResponseDto(genre);
	}

	@Override
	public List<GenreResponseDto> findByIds(List<Long> ids) {
		Set<Long> uniqueIds = new HashSet<>(ids);
		var genres = genreRepository.findAllById(uniqueIds);
		if (genres.size() != uniqueIds.size()) throw new AppException(AppExceptionStatus.NOT_FOUND, GenreConstants.GENRES_NOT_FOUND_MESSAGE);
		return genreMapper.toGenreListResponseDto(genres);
	}

	@Override
	public List<GenreResponseDto> findByAliases(List<String> aliases) {
		Set<String> uniqueAliases = new HashSet<>(aliases);
		var genres = genreRepository.findByAliasIn(List.copyOf(uniqueAliases));
		if (genres.size() != uniqueAliases.size()) throw new AppException(AppExceptionStatus.NOT_FOUND, GenreConstants.GENRES_NOT_FOUND_MESSAGE);
		return genreMapper.toGenreListResponseDto(genres);
	}

	@Override
	public List<GenreWithTranslationResponseDto> findByIdsAndLanguageCode(List<Long> ids, String languageCode) {
		var genres = genreRepository.findAllById(ids);
		if (genres.isEmpty()) return List.of();
		var genreIds = genres.stream().map(Genre::getId).toList();
		var genresTranslationResponseDto = genreTranslationQueryService.findByGenreIdsAndLanguageCode(genreIds, languageCode);
		var genresResponseDto = genreMapper.toGenreListResponseDto(genres);
		return genreMapper.toGenreListWithTranslationResponseDto(genresResponseDto, genresTranslationResponseDto);
	}

	@Override
	public List<GenreWithTranslationResponseDto> findByAliasesAndLanguageCode(List<String> aliases, String languageCode) {
		var genres = genreRepository.findByAliasIn(aliases);
		if (genres.isEmpty()) return List.of();
		var genreIds = genres.stream().map(Genre::getId).toList();
		var genresTranslations = genreTranslationQueryService.findByGenreIds(genreIds);
		var genresResponseDto = genreMapper.toGenreListResponseDto(genres);
		return genreMapper.toGenreListWithTranslationResponseDto(genresResponseDto, genresTranslations);
	}

	@Override
	public boolean existsById(Long id) {
		return genreRepository.existsById(id);
	}

	@Override
	public boolean existsAnyByIds(List<Long> ids) {
		return genreRepository.existsByIdIn(ids);
	}

	@Override
	public boolean existsAllByIds(List<Long> ids) {
		Set<Long> uniqueIds = new HashSet<>(ids);
		var genres = genreRepository.findAllById(uniqueIds);
		return uniqueIds.size() == genres.size();
	}

	@Override
	public boolean existsByAlias(String alias) {
		return genreRepository.existsByAlias(alias);
	}

	@Override
	public boolean existsAnyByAliases(List<String> aliases) {
		return genreRepository.existsByAliasIn(aliases);
	}

	@Override
	public boolean existsAllByAliases(List<String> aliases) {
		Set<String> uniqueAliases = new HashSet<>(aliases);
		var genres = genreRepository.findByAliasIn(List.copyOf(uniqueAliases));
		return uniqueAliases.size() == genres.size();
	}

	@Override
	public boolean existsByAliasExcludingId(String alias, Long id) {
		return genreRepository.existsByAliasAndIdIsNot(alias, id);
	}
}
