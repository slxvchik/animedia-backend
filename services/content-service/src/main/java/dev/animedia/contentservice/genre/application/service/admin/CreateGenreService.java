package dev.animedia.contentservice.genre.application.service.admin;

import dev.animedia.contentservice.genre.application.dto.request.CreateGenreDto;
import dev.animedia.contentservice.genre.application.event.GenreCreateEvent;
import dev.animedia.contentservice.genre.application.exception.GenreAliasExistsException;
import dev.animedia.contentservice.genre.application.exception.GenreNotFoundException;
import dev.animedia.contentservice.genre.application.mapper.GenreApplicationMapper;
import dev.animedia.contentservice.genre.application.usecase.admin.CreateGenreUseCase;
import dev.animedia.contentservice.genre.domain.model.Genre;
import dev.animedia.contentservice.genre.domain.repository.GenreCommandRepository;
import dev.animedia.contentservice.genre.domain.repository.GenreQueryRepository;
import dev.animedia.contentservice.shared.domain.event.EventDispatcher;

import java.util.UUID;

public class CreateGenreService implements CreateGenreUseCase {
	private final GenreApplicationMapper genreApplicationMapper;
	private final GenreQueryRepository genreQueryRepository;
	private final GenreCommandRepository commandRepository;
	private final EventDispatcher eventDispatcher;

	public CreateGenreService(
		GenreApplicationMapper genreApplicationMapper,
		GenreQueryRepository genreQueryRepository,
		GenreCommandRepository commandRepository,
		EventDispatcher eventDispatcher
	) {
		this.genreApplicationMapper = genreApplicationMapper;
		this.genreQueryRepository = genreQueryRepository;
		this.commandRepository = commandRepository;
		this.eventDispatcher = eventDispatcher;
	}


	@Override
	public UUID create(CreateGenreDto genreDto) {
		Genre genre = genreApplicationMapper.toGenre(genreDto);

		boolean aliasExists = genreQueryRepository.existsByAlias(genreDto.alias());
		if (aliasExists) throw new GenreAliasExistsException(genreDto.alias());

		UUID createdId = commandRepository.create(genre);

		Genre created = genreQueryRepository.findById(createdId, null)
			.orElseThrow(() -> new GenreNotFoundException(createdId));

		eventDispatcher.dispatch(
			new GenreCreateEvent(
				genreApplicationMapper.toGenreDto(created)
			)
		);

		return createdId;
	}
}
