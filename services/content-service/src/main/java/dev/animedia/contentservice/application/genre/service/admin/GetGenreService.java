package dev.animedia.contentservice.application.genre.service.admin;

import dev.animedia.contentservice.application.genre.dto.GenreDto;
import dev.animedia.contentservice.application.genre.exception.GenreNotFoundException;
import dev.animedia.contentservice.application.genre.mapper.GenreApplicationMapper;
import dev.animedia.contentservice.application.genre.usecase.admin.GetGenreUseCase;
import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.genre.repository.GenreQueryRepository;
import jakarta.annotation.Nullable;

import java.util.UUID;

public class GetGenreService implements GetGenreUseCase {
	private final GenreApplicationMapper genreApplicationMapper;
	private final GenreQueryRepository genreQueryRepository;

	public GetGenreService(
		GenreApplicationMapper genreApplicationMapper,
		GenreQueryRepository genreQueryRepository
	) {
		this.genreApplicationMapper = genreApplicationMapper;
		this.genreQueryRepository = genreQueryRepository;
	}

	@Override
	public GenreDto get(UUID id, @Nullable Boolean active, @Nullable String languageCode) {
		Genre genre = genreQueryRepository.findById(id, null, languageCode)
			.orElseThrow(GenreNotFoundException::new);
		return genreApplicationMapper.toGenreDto(genre);
	}
}
