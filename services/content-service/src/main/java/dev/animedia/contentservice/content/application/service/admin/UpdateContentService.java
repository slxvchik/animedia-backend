package dev.animedia.contentservice.content.application.service.admin;

import dev.animedia.contentservice.content.application.dto.content.request.UpdateContentDto;
import dev.animedia.contentservice.content.application.dto.genre.GenreDto;
import dev.animedia.contentservice.content.application.dto.status.StatusDto;
import dev.animedia.contentservice.content.application.event.ContentUpdateEvent;
import dev.animedia.contentservice.content.application.exception.ContentNotFoundException;
import dev.animedia.contentservice.content.application.mapper.ContentApplicationMapper;
import dev.animedia.contentservice.content.application.resolver.GenreResolverInterface;
import dev.animedia.contentservice.content.application.resolver.StatusResolverInterface;
import dev.animedia.contentservice.content.application.usecase.admin.UpdateContentUseCase;
import dev.animedia.contentservice.content.domain.model.Content;
import dev.animedia.contentservice.content.domain.model.UpdateContent;
import dev.animedia.contentservice.content.domain.repository.ContentCommandRepository;
import dev.animedia.contentservice.content.domain.repository.ContentQueryRepository;
import dev.animedia.contentservice.shared.domain.event.EventDispatcher;

import java.util.List;
import java.util.Set;

public class UpdateContentService implements UpdateContentUseCase {
    private final ContentApplicationMapper contentApplicationMapper;

	private final StatusResolverInterface statusResolverInterface;
	private final GenreResolverInterface genreResolverInterface;

    private final ContentQueryRepository contentQueryRepository;
    private final ContentCommandRepository contentCommandRepository;

	private final EventDispatcher eventDispatcher;

	public UpdateContentService(
		ContentApplicationMapper contentApplicationMapper,
		StatusResolverInterface statusResolverInterface,
		GenreResolverInterface genreResolverInterface,
		ContentQueryRepository contentQueryRepository,
		ContentCommandRepository contentCommandRepository,
		EventDispatcher eventDispatcher
	) {
		this.contentApplicationMapper = contentApplicationMapper;
		this.statusResolverInterface = statusResolverInterface;
		this.genreResolverInterface = genreResolverInterface;
		this.contentQueryRepository = contentQueryRepository;
		this.contentCommandRepository = contentCommandRepository;
		this.eventDispatcher = eventDispatcher;
	}

	@Override
    public void update(UpdateContentDto contentRequestDto) {
        Content content = contentQueryRepository.find(contentRequestDto.id(), null)
            .orElseThrow(() -> new ContentNotFoundException(contentRequestDto.id()));

		// Check if statuses exists
		List<StatusDto> statusDtoList = statusResolverInterface.resolve(
			Set.of(contentRequestDto.statusId())
		);
		StatusDto statusDto = statusDtoList.isEmpty() ? null : statusDtoList.getFirst();

		// Check if genres exists
		List<GenreDto> genreDtoList = genreResolverInterface.resolve(contentRequestDto.genreIds());

        UpdateContent updateContent = contentApplicationMapper.toContentUpdate(
	        contentRequestDto
        );
        content.update(updateContent);
        contentCommandRepository.update(content);

		Content created = contentQueryRepository.find(content.getId(), null)
			.orElseThrow(() -> new ContentNotFoundException(content.getId()));

		eventDispatcher.dispatch(
			new ContentUpdateEvent(
				contentApplicationMapper.toContentResponseDto(
					created,
					statusDto,
					Set.copyOf(genreDtoList)
				)
			)
		);
    }
}
