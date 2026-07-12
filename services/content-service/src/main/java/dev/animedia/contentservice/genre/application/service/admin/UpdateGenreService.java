package dev.animedia.contentservice.genre.application.service.admin;

import dev.animedia.contentservice.genre.application.dto.GenreDto;
import dev.animedia.contentservice.genre.application.event.GenreUpdateEvent;
import dev.animedia.contentservice.genre.application.exception.GenreNotFoundException;
import dev.animedia.contentservice.genre.application.mapper.GenreApplicationMapper;
import dev.animedia.contentservice.genre.application.usecase.admin.UpdateGenreUseCase;
import dev.animedia.contentservice.genre.domain.model.Genre;
import dev.animedia.contentservice.genre.domain.repository.GenreCommandRepository;
import dev.animedia.contentservice.genre.domain.repository.GenreQueryRepository;
import dev.animedia.contentservice.shared.domain.event.EventDispatcherInterface;

import java.util.stream.Collectors;

public class UpdateGenreService implements UpdateGenreUseCase {
	private final GenreApplicationMapper genreApplicationMapper;
	private final GenreQueryRepository genreQueryRepository;
	private final GenreCommandRepository genreCommandRepository;
	private final EventDispatcherInterface eventDispatcherInterface;

	public UpdateGenreService(
		GenreApplicationMapper genreApplicationMapper,
		GenreQueryRepository genreQueryRepository,
		GenreCommandRepository genreCommandRepository, EventDispatcherInterface eventDispatcherInterface
	) {
		this.genreApplicationMapper = genreApplicationMapper;
		this.genreQueryRepository = genreQueryRepository;
		this.genreCommandRepository = genreCommandRepository;
		this.eventDispatcherInterface = eventDispatcherInterface;
	}

	@Override
	public void update(GenreDto genreDto) {
		Genre genre = genreQueryRepository.findById(genreDto.id(), null)
			.orElseThrow(GenreNotFoundException::new);

		genre.update(
			genreDto.sortOrder(),
			genreDto.active(),
			genreDto.translationSet()
				.stream()
				.map(genreApplicationMapper::toGenreTranslation)
				.collect(Collectors.toSet())
		);

		genreCommandRepository.update(genre);

		Genre updated = genreQueryRepository.findById(genre.getId(), null)
			.orElseThrow(() -> new GenreNotFoundException(genre.getId()));

		eventDispatcherInterface.dispatch(
			new GenreUpdateEvent(
				genreApplicationMapper.toGenreDto(updated)
			)
		);
	}
}