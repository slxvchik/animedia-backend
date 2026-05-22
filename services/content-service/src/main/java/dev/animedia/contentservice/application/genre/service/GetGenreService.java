package dev.animedia.contentservice.application.genre.service;

import dev.animedia.contentservice.application.genre.dto.GenreDto;
import dev.animedia.contentservice.application.genre.exception.GenreNotFoundException;
import dev.animedia.contentservice.application.genre.mapper.GenreApplicationMapper;
import dev.animedia.contentservice.application.genre.usecase.GetGenreUseCase;
import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.genre.repository.GenreQueryRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetGenreService implements GetGenreUseCase {
	private final GenreApplicationMapper genreApplicationMapper;
	private final GenreQueryRepository genreQueryRepository;

	@Autowired
	public GetGenreService(
		GenreApplicationMapper genreApplicationMapper,
		GenreQueryRepository genreQueryRepository
	) {
		this.genreApplicationMapper = genreApplicationMapper;
		this.genreQueryRepository = genreQueryRepository;
	}

	@Override
	public GenreDto get(Long id, boolean onlyActive, @Nullable String languageCode) {
		Genre genre = genreQueryRepository.findById(id, onlyActive, languageCode)
			.orElseThrow(GenreNotFoundException::new);
		return genreApplicationMapper.toGenreDto(genre);
	}
}
