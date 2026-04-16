package dev.animedia.contentservice.application.genre.service;

import dev.animedia.contentservice.application.genre.dto.GenreDto;
import dev.animedia.contentservice.application.genre.exception.GenreAliasExistsException;
import dev.animedia.contentservice.application.genre.mapper.GenreApplicationMapper;
import dev.animedia.contentservice.application.genre.usecase.CreateGenreUseCase;
import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.genre.repository.GenreCommandRepository;
import dev.animedia.contentservice.domain.genre.repository.GenreQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateGenreService implements CreateGenreUseCase {
	private final GenreApplicationMapper genreApplicationMapper;
	private final GenreQueryRepository genreQueryRepository;
	private final GenreCommandRepository commandRepository;

	@Autowired
	public CreateGenreService(
		GenreApplicationMapper genreApplicationMapper,
		GenreQueryRepository genreQueryRepository,
		GenreCommandRepository commandRepository
	) {
		this.genreApplicationMapper = genreApplicationMapper;
		this.genreQueryRepository = genreQueryRepository;
		this.commandRepository = commandRepository;
	}


	@Override
	public GenreDto create(GenreDto genreDto) {
		Genre genre = genreApplicationMapper.toGenre(genreDto);

		boolean aliasExists = genreQueryRepository.existsByAlias(genreDto.alias());
		if (aliasExists) throw new GenreAliasExistsException();

		Genre created = commandRepository.create(genre);
		return genreApplicationMapper.toGenreDto(created);
	}
}
