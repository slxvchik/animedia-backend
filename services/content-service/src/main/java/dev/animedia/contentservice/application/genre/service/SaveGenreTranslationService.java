package dev.animedia.contentservice.application.genre.service;

import dev.animedia.contentservice.application.genre.dto.GenreTranslationDto;
import dev.animedia.contentservice.application.genre.exception.GenreNotFoundException;
import dev.animedia.contentservice.application.genre.mapper.GenreApplicationMapper;
import dev.animedia.contentservice.application.genre.usecase.SaveGenreTranslationUseCase;
import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.genre.model.GenreTranslation;
import dev.animedia.contentservice.domain.genre.repository.GenreCommandRepository;
import dev.animedia.contentservice.domain.genre.repository.GenreQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveGenreTranslationService implements SaveGenreTranslationUseCase {
	private final GenreQueryRepository genreQueryRepository;
	private final GenreCommandRepository genreCommandRepository;
	private final GenreApplicationMapper genreApplicationMapper;

	@Autowired
	public SaveGenreTranslationService(
		GenreQueryRepository genreQueryRepository,
		GenreCommandRepository genreCommandRepository,
		GenreApplicationMapper genreApplicationMapper
	) {
		this.genreQueryRepository = genreQueryRepository;
		this.genreCommandRepository = genreCommandRepository;
		this.genreApplicationMapper = genreApplicationMapper;
	}

	@Override
	public void saveTranslation(Long genreId, GenreTranslationDto genreTranslationDto) {
		Genre genre = genreQueryRepository.findById(genreId, null)
			.orElseThrow(GenreNotFoundException::new);

		GenreTranslation translation = genreApplicationMapper.toGenreTranslation(genreTranslationDto);

		genre.saveTranslation(translation);

		genreCommandRepository.update(genre);
	}
}
