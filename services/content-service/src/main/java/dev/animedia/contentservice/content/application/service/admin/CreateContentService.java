package dev.animedia.contentservice.content.application.service.admin;

import dev.animedia.contentservice.content.application.dto.content.ContentRequestDto;
import dev.animedia.contentservice.content.application.dto.genre.GenreDto;
import dev.animedia.contentservice.content.application.dto.status.StatusDto;
import dev.animedia.contentservice.content.application.event.ContentCreateEvent;
import dev.animedia.contentservice.content.application.exception.ContentExistsException;
import dev.animedia.contentservice.content.application.exception.ContentNotFoundException;
import dev.animedia.contentservice.content.application.mapper.ContentApplicationMapper;
import dev.animedia.contentservice.content.application.resolver.GenreResolverInterface;
import dev.animedia.contentservice.content.application.resolver.StatusResolverInterface;
import dev.animedia.contentservice.content.application.usecase.admin.CreateContentUseCase;
import dev.animedia.contentservice.content.domain.model.Content;
import dev.animedia.contentservice.content.domain.repository.ContentCommandRepository;
import dev.animedia.contentservice.content.domain.repository.ContentQueryRepository;
import dev.animedia.contentservice.shared.domain.event.EventDispatcherInterface;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class CreateContentService implements CreateContentUseCase {
    private final ContentApplicationMapper contentApplicationMapper;

	private final StatusResolverInterface statusResolverInterface;
	private final GenreResolverInterface genreResolverInterface;

    private final ContentQueryRepository contentQueryRepository;
    private final ContentCommandRepository contentCommandRepository;

	private final EventDispatcherInterface eventDispatcherInterface;

    public CreateContentService(
        ContentApplicationMapper contentApplicationMapper,
	    StatusResolverInterface statusResolverInterface,
	    GenreResolverInterface genreResolverInterface,
        ContentQueryRepository contentQueryRepository,
        ContentCommandRepository contentCommandRepository,
	    EventDispatcherInterface eventDispatcherInterface
    ) {
        this.contentApplicationMapper = contentApplicationMapper;
	    this.statusResolverInterface = statusResolverInterface;
	    this.genreResolverInterface = genreResolverInterface;
	    this.contentQueryRepository = contentQueryRepository;
        this.contentCommandRepository = contentCommandRepository;
	    this.eventDispatcherInterface = eventDispatcherInterface;
    }

    @Override
    public UUID create(ContentRequestDto contentRequestDto) {

	    // Check if statuses exists
	    List<StatusDto> statusDtoList = statusResolverInterface.resolve(
			Set.of(contentRequestDto.statusId())
	    );

	    // Check if genres exists
	    List<GenreDto> genreDtoList = genreResolverInterface.resolve(contentRequestDto.genreIdSet());

        Content content = contentApplicationMapper.toContent(
	        contentRequestDto
        );

        boolean contentExists = contentQueryRepository.exists(content.getAlias(), content.getType(), content.getSeason());
        if (contentExists) throw new ContentExistsException(content.getAlias(), content.getType(), content.getSeason());

        UUID createdId = contentCommandRepository.create(content);

		Content created = contentQueryRepository.find(createdId, null)
			.orElseThrow(() -> new ContentNotFoundException(createdId));

		eventDispatcherInterface.dispatch(
			new ContentCreateEvent(
				contentApplicationMapper.toContentResponseDto(
					created,
					statusDtoList.getFirst(),
					Set.copyOf(genreDtoList)
				)
			)
		);

		return createdId;
    }
}
