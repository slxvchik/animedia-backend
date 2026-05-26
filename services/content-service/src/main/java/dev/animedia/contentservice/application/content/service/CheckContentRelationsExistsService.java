package dev.animedia.contentservice.application.content.service;

import dev.animedia.contentservice.application.genre.dto.GenreDto;
import dev.animedia.contentservice.application.genre.exception.GenreNotFoundException;
import dev.animedia.contentservice.application.genre.usecase.GetGenreListUseCase;
import dev.animedia.contentservice.application.status.usecase.GetStatusUseCase;
import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.genre.model.Genre;

import java.util.List;

public final class CheckContentRelationsExistsService implements CheckContentRelationsExistsUseCase {
	private final GetStatusUseCase getStatusUseCase;
	private final GetGenreListUseCase getGenreListUseCase;

	public CheckContentRelationsExistsService(
		GetStatusUseCase getStatusUseCase,
		GetGenreListUseCase getGenreListUseCase
	) {
		this.getStatusUseCase = getStatusUseCase;
		this.getGenreListUseCase = getGenreListUseCase;
	}

	@Override
	public void check(Content content) {

		getStatusUseCase.get(content.getStatus().getId(), false, null);

		List<Long> inputGenreIdList = content.getGenreSet()
			.stream()
			.map(Genre::getId)
			.distinct()
			.toList();
		List<Long> foundGenreIdList = getGenreListUseCase.getList(inputGenreIdList, false, null)
			.stream()
			.map(GenreDto::id)
			.toList();
		if (inputGenreIdList.size() != foundGenreIdList.size()) {
			List<Long> genreNotFoundIdList = inputGenreIdList
				.stream()
				.filter(inputGenreId -> !foundGenreIdList.contains(inputGenreId))
				.toList();
			throw new GenreNotFoundException(genreNotFoundIdList);
		}
	}
}
