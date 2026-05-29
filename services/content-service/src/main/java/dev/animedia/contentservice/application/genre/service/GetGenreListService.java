package dev.animedia.contentservice.application.genre.service;

import dev.animedia.contentservice.application.genre.dto.GenreDto;
import dev.animedia.contentservice.application.genre.mapper.GenreApplicationMapper;
import dev.animedia.contentservice.application.genre.usecase.GetGenreListUseCase;
import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.genre.repository.GenreQueryRepository;
import jakarta.annotation.Nullable;

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
	public List<GenreDto> getList(List<UUID> idList, @Nullable Boolean active, @Nullable String languageCode) {
		List<UUID> distinctIdList = idList.stream().distinct().toList();
		List<Genre> genreList = genreQueryRepository.findByIdList(distinctIdList, active, languageCode);
		return genreList.stream()
			.map(genreApplicationMapper::toGenreDto)
			.toList();
	}
}
