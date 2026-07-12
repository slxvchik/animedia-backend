package dev.animedia.contentservice.genre.application.service;

import dev.animedia.contentservice.genre.application.dto.GenreDto;
import dev.animedia.contentservice.genre.application.mapper.GenreApplicationMapper;
import dev.animedia.contentservice.genre.application.usecase.IndexAllGenreUseCase;
import dev.animedia.contentservice.genre.domain.model.Genre;
import dev.animedia.contentservice.genre.domain.repository.GenreQueryRepository;
import dev.animedia.contentservice.shared.domain.pagination.Page;
import dev.animedia.contentservice.shared.domain.pagination.Pageable;

public class IndexAllGenreService implements IndexAllGenreUseCase {
	private final GenreApplicationMapper genreApplicationMapper;
	private final GenreQueryRepository genreQueryRepository;

	public IndexAllGenreService(
		GenreApplicationMapper genreApplicationMapper,
		GenreQueryRepository genreQueryRepository
	) {
		this.genreApplicationMapper = genreApplicationMapper;
		this.genreQueryRepository = genreQueryRepository;
	}

	@Override
	public Page<GenreDto> index(Pageable pageable) {
		Page<Genre> genrePage = genreQueryRepository.findAll(pageable);
		return genrePage.changeContent(genreApplicationMapper::toGenreDto);
	}
}
