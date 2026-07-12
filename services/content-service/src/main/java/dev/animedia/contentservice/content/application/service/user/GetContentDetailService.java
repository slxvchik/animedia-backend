package dev.animedia.contentservice.content.application.service.user;

import dev.animedia.contentservice.content.application.dto.content.ContentRequestDto;
import dev.animedia.contentservice.content.application.exception.ContentNotFoundException;
import dev.animedia.contentservice.content.application.mapper.ContentApplicationMapper;
import dev.animedia.contentservice.content.application.usecase.user.GetContentDetailUseCase;
import dev.animedia.contentservice.genre.application.dto.GenreDto;
import dev.animedia.contentservice.genre.application.mapper.GenreApplicationMapper;
import dev.animedia.contentservice.status.application.dto.StatusDto;
import dev.animedia.contentservice.status.application.mapper.StatusApplicationMapper;
import dev.animedia.contentservice.content.domain.model.Content;
import dev.animedia.contentservice.content.domain.model.ContentType;
import dev.animedia.contentservice.content.domain.repository.ContentQueryRepository;

import java.util.Set;
import java.util.stream.Collectors;

public class GetContentDetailService implements GetContentDetailUseCase {
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
	public ContentRequestDto get(String alias, ContentType type, int season, String languageCode) {
		Content content = contentQueryRepository.find(alias, type, season, languageCode)
			.orElseThrow(ContentNotFoundException::new);

		if (!content.getActive()) throw new ContentNotFoundException();

		StatusDto statusDto = statusApplicationMapper.toStatusDto(content.getStatusId());
		Set<GenreDto> genreDtoSet = content.getGenreIdSet() != null
			? content.getGenreIdSet().stream()
				.map(genreApplicationMapper::toGenreDto)
				.collect(Collectors.toSet())
			: Set.of();

		return contentApplicationMapper.toContentResponseDto(
			content,
			statusDto,
			genreDtoSet
		);
	}
}