package dev.animedia.contentservice.application.content.service.user;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import dev.animedia.contentservice.application.content.exception.ContentNotFoundException;
import dev.animedia.contentservice.application.content.mapper.ContentApplicationMapper;
import dev.animedia.contentservice.application.content.usecase.user.GetContentByDetailsUseCase;
import dev.animedia.contentservice.application.genre.dto.GenreDto;
import dev.animedia.contentservice.application.genre.mapper.GenreApplicationMapper;
import dev.animedia.contentservice.application.status.dto.StatusDto;
import dev.animedia.contentservice.application.status.mapper.StatusApplicationMapper;
import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.model.ContentType;
import dev.animedia.contentservice.domain.content.repository.ContentQueryRepository;
import jakarta.annotation.Nullable;

import java.util.Set;
import java.util.stream.Collectors;

public class GetContentDetailService implements GetContentByDetailsUseCase {
	private final ContentApplicationMapper contentApplicationMapper;
	private final ContentQueryRepository contentQueryRepository;
	private final StatusApplicationMapper statusApplicationMapper;
	private final GenreApplicationMapper genreApplicationMapper;

	public GetContentDetailService(
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
	public ContentDto get(String alias, ContentType type, int season, @Nullable String languageCode,  @Nullable Boolean active) {
		Content content = contentQueryRepository.find(alias, type, season, languageCode)
			.orElseThrow(ContentNotFoundException::new);

		if (!content.getActive()) throw new ContentNotFoundException();

		StatusDto statusDto = statusApplicationMapper.toStatusDto(content.getStatus());
		Set<GenreDto> genreDtoSet = content.getGenreSet() != null
			? content.getGenreSet().stream()
				.map(genreApplicationMapper::toGenreDto)
				.collect(Collectors.toSet())
			: Set.of();

		return contentApplicationMapper.toContentDto(
			content,
			statusDto,
			genreDtoSet
		);
	}
}