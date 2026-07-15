package dev.animedia.contentservice.content.application.service.admin;

import dev.animedia.contentservice.content.application.dto.content.request.CreateContentDto;
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
import dev.animedia.contentservice.shared.domain.event.EventDispatcher;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class CreateContentService implements CreateContentUseCase {
    private final ContentApplicationMapper contentApplicationMapper;

	private final StatusResolverInterface statusResolverInterface;
	private final GenreResolverInterface genreResolverInterface;

    private final ContentQueryRepository contentQueryRepository;
    private final ContentCommandRepository contentCommandRepository;

	private final EventDispatcher eventDispatcher;

    public CreateContentService(
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
    public UUID create(CreateContentDto createContentDto) {

	    // Check if statuses exists
	    List<StatusDto> statusesDto = statusResolverInterface.resolve(
			Set.of(createContentDto.statusId())
	    );
		StatusDto statusDto = statusesDto.isEmpty() ? null : statusesDto.getFirst();

	    // Check if genres exists
	    List<GenreDto> genresDto = genreResolverInterface.resolve(createContentDto.genreIds());

        Content content = contentApplicationMapper.toContent(
	        createContentDto
        );

        boolean contentExists = contentQueryRepository.exists(content.getAlias(), content.getType(), content.getSeason());
        if (contentExists) throw new ContentExistsException(content.getAlias(), content.getType(), content.getSeason());

        UUID createdId = contentCommandRepository.create(content);

		Content created = contentQueryRepository.find(createdId, null)
			.orElseThrow(() -> new ContentNotFoundException(createdId));

		eventDispatcher.dispatch(
			new ContentCreateEvent(
				contentApplicationMapper.toContentResponseDto(
					created,
					statusDto,
					Set.copyOf(genresDto)
				)
			)
		);

		return createdId;
    }
}
