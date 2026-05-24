package dev.animedia.contentservice.application.content.service;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import dev.animedia.contentservice.application.content.exception.ContentNotFoundException;
import dev.animedia.contentservice.application.content.mapper.ContentApplicationMapper;
import dev.animedia.contentservice.application.content.usecase.GetContentByDetailsUseCase;
import dev.animedia.contentservice.application.genre.mapper.GenreApplicationMapper;
import dev.animedia.contentservice.application.status.mapper.StatusApplicationMapper;
import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.model.ContentType;
import dev.animedia.contentservice.domain.content.repository.ContentQueryRepository;
import org.jspecify.annotations.Nullable;

public class GetContentByDetailsService implements GetContentByDetailsUseCase {
	private final ContentApplicationMapper contentApplicationMapper;
	private final ContentQueryRepository contentQueryRepository;
	private final StatusApplicationMapper statusApplicationMapper;
	private final GenreApplicationMapper genreApplicationMapper;

	public GetContentByDetailsService(
		ContentApplicationMapper contentApplicationMapper,
		ContentQueryRepository contentQueryRepository,
		StatusApplicationMapper statusApplicationMapper,
		GenreApplicationMapper genreApplicationMapper
	) {
		this.contentApplicationMapper = contentApplicationMapper;
		this.contentQueryRepository = contentQueryRepository;
		this.statusApplicationMapper = statusApplicationMapper;
		this.genreApplicationMapper = genreApplicationMapper;
	}

	@Override
	public ContentDto get(String alias, ContentType type, @Nullable Integer season, boolean onlyActive, @Nullable String languageCode) {
		Content content = contentQueryRepository.find(alias, type, season, onlyActive, languageCode)
			.orElseThrow(ContentNotFoundException::new);

		return contentApplicationMapper.toContentDto(
			content,
			statusApplicationMapper::toStatusDto,
			genreApplicationMapper::toGenreDto
		);
	}
}