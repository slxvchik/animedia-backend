package dev.animedia.contentservice.genre.service.impl;

import dev.animedia.contentservice.genre.dto.response.GenreResponseDto;
import dev.animedia.contentservice.genre.dto.response.GenreTranslationResponseDto;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationResponseDto;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationsResponseDto;
import dev.animedia.contentservice.genre.exception.GenreNotFoundException;
import dev.animedia.contentservice.genre.exception.GenresNotFoundException;
import dev.animedia.contentservice.genre.mapper.GenreMapper;
import dev.animedia.contentservice.genre.model.Genre;
import dev.animedia.contentservice.genre.repository.GenreRepository;
import dev.animedia.contentservice.genre.service.GenreQueryService;
import dev.animedia.contentservice.genre.service.GenreTranslationQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		var genre = genreRepository.findById(id).orElseThrow(GenreNotFoundException::new);
		return genreMapper.toGenreResponseDto(genre);
	}

	@Override
	public List<GenreResponseDto> findByIds(List<Long> ids) {
		var genres = genreRepository.findByIdIn(ids);
		return genreMapper.toGenresResponseDto(genres);
	}

	@Override
	public List<GenreResponseDto> findByAliases(List<String> aliases) {
		var genres = genreRepository.findByAliasIn(aliases);
		return genreMapper.toGenresResponseDto(genres);
	}

	@Override
	public List<GenreWithTranslationResponseDto> findByIdsAndLanguageCode(List<Long> ids, String languageCode) {

		var genres = genreRepository.findByIdIn(ids);

		if (genres.isEmpty()) return List.of();

		var genreIds = genres.stream().map(Genre::getId).toList();

		var genresTranslationResponseDto = genreTranslationQueryService.findByGenreIdsAndLanguageCode(genreIds, languageCode);

		var genresResponseDto = genreMapper.toGenresResponseDto(genres);

		return genreMapper.toGenresWithTranslationResponseDto(genresResponseDto, genresTranslationResponseDto);
	}

	@Override
	public List<GenreWithTranslationResponseDto> findByAliasAndLanguage(String alias, String languageCode) {
		return null;
	}

	@Override
	public List<GenreWithTranslationResponseDto> findByAliasesAndLanguage(List<String> aliases, String languageCode) {
		return List.of();
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
}
