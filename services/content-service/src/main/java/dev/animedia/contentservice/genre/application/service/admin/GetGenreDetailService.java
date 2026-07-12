package dev.animedia.contentservice.genre.application.service.admin;

import dev.animedia.contentservice.genre.application.dto.GenreDto;
import dev.animedia.contentservice.genre.application.exception.GenreNotFoundException;
import dev.animedia.contentservice.genre.application.mapper.GenreApplicationMapper;
import dev.animedia.contentservice.genre.application.usecase.admin.GetGenreDetailUseCase;
import dev.animedia.contentservice.genre.domain.model.Genre;
import dev.animedia.contentservice.genre.domain.repository.GenreQueryRepository;
import jakarta.annotation.Nullable;

import java.util.UUID;

public class GetGenreDetailService implements GetGenreDetailUseCase {
	private final GenreApplicationMapper genreApplicationMapper;
	private final GenreQueryRepository genreQueryRepository;

	public GetGenreDetailService(
		GenreApplicationMapper genreApplicationMapper,
		GenreQueryRepository genreQueryRepository
	) {
		this.genreApplicationMapper = genreApplicationMapper;
		this.genreQueryRepository = genreQueryRepository;
	}

	@Override
	public GenreDto get(UUID id, @Nullable String languageCode) {
		Genre genre = genreQueryRepository.findById(id, null)
			.orElseThrow(GenreNotFoundException::new);
		return genreApplicationMapper.toGenreDto(genre);
	}
}
