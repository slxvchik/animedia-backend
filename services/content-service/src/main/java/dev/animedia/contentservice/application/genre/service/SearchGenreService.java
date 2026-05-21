package dev.animedia.contentservice.application.genre.service;

import dev.animedia.contentservice.application.genre.dto.GenreDto;
import dev.animedia.contentservice.application.genre.dto.GenreSearchDto;
import dev.animedia.contentservice.application.genre.mapper.GenreApplicationMapper;
import dev.animedia.contentservice.application.genre.usecase.SearchGenreUseCase;
import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.genre.model.GenreSearchCriteria;
import dev.animedia.contentservice.domain.genre.repository.GenreQueryRepository;
import dev.animedia.contentservice.domain.shared.model.Page;
import dev.animedia.contentservice.domain.shared.model.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchGenreService implements SearchGenreUseCase {
	private final GenreApplicationMapper genreApplicationMapper;
	private final GenreQueryRepository genreQueryRepository;

	@Autowired
	public SearchGenreService(
		GenreApplicationMapper genreApplicationMapper,
		GenreQueryRepository genreQueryRepository
	) {
		this.genreApplicationMapper = genreApplicationMapper;
		this.genreQueryRepository = genreQueryRepository;
	}

	@Override
	public Page<GenreDto> search(GenreSearchDto searchGenreDto, Pageable pageable) {
		GenreSearchCriteria genreSearchCriteria = genreApplicationMapper.toGenreSearchCriteria(searchGenreDto);
		Page<Genre> genrePage = genreQueryRepository.search(genreSearchCriteria, pageable);
		return genrePage.changeContent(genreApplicationMapper::toGenreDto);
	}
}
