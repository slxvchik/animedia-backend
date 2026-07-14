package dev.animedia.contentservice.content.application.service.user;

import dev.animedia.contentservice.content.application.dto.content.ContentResponseDto;
import dev.animedia.contentservice.content.application.dto.genre.GenreDto;
import dev.animedia.contentservice.content.application.dto.status.StatusDto;
import dev.animedia.contentservice.content.application.exception.ContentNotFoundException;
import dev.animedia.contentservice.content.application.mapper.ContentApplicationMapper;
import dev.animedia.contentservice.content.application.resolver.GenreResolverInterface;
import dev.animedia.contentservice.content.application.resolver.StatusResolverInterface;
import dev.animedia.contentservice.content.application.usecase.user.GetContentDetailUseCase;
import dev.animedia.contentservice.content.domain.model.Content;
import dev.animedia.contentservice.content.domain.model.ContentType;
import dev.animedia.contentservice.content.domain.repository.ContentQueryRepository;

import java.util.List;
import java.util.Set;

public class GetContentDetailService implements GetContentDetailUseCase {
	private final ContentApplicationMapper contentApplicationMapper;
	private final ContentQueryRepository contentQueryRepository;
	private final StatusResolverInterface statusResolverInterface;
	private final GenreResolverInterface genreResolverInterface;

	public GetContentDetailService(
		ContentApplicationMapper contentApplicationMapper,
		ContentQueryRepository contentQueryRepository,
		StatusResolverInterface statusResolverInterface,
		GenreResolverInterface genreResolverInterface
	) {
		this.contentApplicationMapper = contentApplicationMapper;
		this.contentQueryRepository = contentQueryRepository;
		this.statusResolverInterface = statusResolverInterface;
		this.genreResolverInterface = genreResolverInterface;
	}

	@Override
	public ContentResponseDto get(String alias, ContentType type, int season, String languageCode) {
		Content content = contentQueryRepository.find(alias, type, season, languageCode)
			.orElseThrow(ContentNotFoundException::new);

		if (!content.getActive()) throw new ContentNotFoundException();

		List<StatusDto> statusDtoList = statusResolverInterface.resolve(Set.of(content.getStatusId()));
		StatusDto statusDto = statusDtoList.isEmpty() ? null : statusDtoList.getFirst();

		List<GenreDto> genreDtoList = genreResolverInterface.resolve(content.getGenreIds());

		return contentApplicationMapper.toContentResponseDto(
			content,
			statusDto,
			Set.copyOf(genreDtoList)
		);
	}
}