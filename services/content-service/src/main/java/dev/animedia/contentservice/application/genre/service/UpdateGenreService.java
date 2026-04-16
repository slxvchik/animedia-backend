package dev.animedia.contentservice.application.genre.service;

import dev.animedia.contentservice.application.genre.dto.GenreDto;
import dev.animedia.contentservice.application.genre.exception.GenreAliasExistsException;
import dev.animedia.contentservice.application.genre.exception.GenreNotFoundException;
import dev.animedia.contentservice.application.genre.mapper.GenreApplicationMapper;
import dev.animedia.contentservice.application.genre.usecase.UpdateGenreUseCase;
import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.genre.repository.GenreCommandRepository;
import dev.animedia.contentservice.domain.genre.repository.GenreQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UpdateGenreService implements UpdateGenreUseCase {
	private final GenreApplicationMapper genreApplicationMapper;
	private final GenreQueryRepository genreQueryRepository;
	private final GenreCommandRepository genreCommandRepository;

	@Autowired
	public UpdateGenreService(
		GenreApplicationMapper genreApplicationMapper,
		GenreQueryRepository genreQueryRepository,
		GenreCommandRepository genreCommandRepository
	) {
		this.genreApplicationMapper = genreApplicationMapper;
		this.genreQueryRepository = genreQueryRepository;
		this.genreCommandRepository = genreCommandRepository;
	}

	@Override
	public GenreDto update(GenreDto genreDto) {
		Genre genre = genreQueryRepository.findById(genreDto.id(), null)
			.orElseThrow(GenreNotFoundException::new);

		boolean aliasExists = genreQueryRepository.existsByAliasExcludeId(genre.getAlias(), genre.getId());
		if (aliasExists) throw new GenreAliasExistsException();

		genre.update(
			genreDto.alias(),
			genreDto.sortOrder(),
			genreDto.translationSet()
				.stream()
				.map(genreApplicationMapper::toGenreTranslation)
				.collect(Collectors.toSet())
		);

		Genre updatedGenre = genreCommandRepository.update(genre);

		return genreApplicationMapper.toGenreDto(updatedGenre);
	}
}