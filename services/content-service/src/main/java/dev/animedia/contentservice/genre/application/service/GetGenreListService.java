package dev.animedia.contentservice.genre.application.service;

import dev.animedia.contentservice.genre.application.dto.GenreDto;
import dev.animedia.contentservice.genre.application.mapper.GenreApplicationMapper;
import dev.animedia.contentservice.genre.application.usecase.GetGenreListUseCase;
import dev.animedia.contentservice.genre.domain.model.Genre;
import dev.animedia.contentservice.genre.domain.repository.GenreQueryRepository;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class GetGenreListService implements GetGenreListUseCase {
	private final GenreApplicationMapper genreApplicationMapper;
	private final GenreQueryRepository genreQueryRepository;

	public GetGenreListService(
		GenreApplicationMapper genreApplicationMapper,
		GenreQueryRepository genreQueryRepository
	) {
		this.genreApplicationMapper = genreApplicationMapper;
		this.genreQueryRepository = genreQueryRepository;
	}

	@Override
	public List<GenreDto> get(List<UUID> idList, @Nullable String languageCode) {
		List<UUID> distinctIdList = idList.stream().distinct().toList();
		List<Genre> genreList = genreQueryRepository.findByIdList(distinctIdList, languageCode);
		return genreList.stream()
			.map(genreApplicationMapper::toGenreDto)
			.toList();
	}
}
