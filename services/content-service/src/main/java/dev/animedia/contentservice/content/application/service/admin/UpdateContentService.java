package dev.animedia.contentservice.content.application.service.admin;

import dev.animedia.contentservice.content.application.dto.ContentDto;
import dev.animedia.contentservice.content.application.exception.ContentNotFoundException;
import dev.animedia.contentservice.content.application.mapper.ContentApplicationMapper;
import dev.animedia.contentservice.content.application.resolver.GenreDomainResolver;
import dev.animedia.contentservice.content.application.resolver.StatusDomainResolver;
import dev.animedia.contentservice.content.application.usecase.admin.UpdateContentUseCase;
import dev.animedia.contentservice.genre.application.dto.GenreDto;
import dev.animedia.contentservice.content.application.event.ContentUpdateEvent;
import dev.animedia.contentservice.genre.application.mapper.GenreApplicationMapper;
import dev.animedia.contentservice.status.application.dto.StatusDto;
import dev.animedia.contentservice.status.application.mapper.StatusApplicationMapper;
import dev.animedia.contentservice.content.domain.model.Content;
import dev.animedia.contentservice.content.domain.model.ContentUpdate;
import dev.animedia.contentservice.content.domain.repository.ContentCommandRepository;
import dev.animedia.contentservice.content.domain.repository.ContentQueryRepository;
import dev.animedia.contentservice.genre.domain.model.Genre;
import dev.animedia.contentservice.shared.domain.event.EventDispatcherInterface;
import dev.animedia.contentservice.status.domain.model.Status;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class UpdateContentService implements UpdateContentUseCase {
    private final ContentApplicationMapper contentApplicationMapper;

	private final StatusDomainResolver statusDomainResolver;
	private final StatusApplicationMapper statusApplicationMapper;

	private final GenreDomainResolver genreDomainResolver;
	private final GenreApplicationMapper genreApplicationMapper;

    private final ContentQueryRepository contentQueryRepository;
    private final ContentCommandRepository contentCommandRepository;

	private final EventDispatcherInterface eventDispatcherInterface;

	public UpdateContentService(
		ContentApplicationMapper contentApplicationMapper,
		StatusDomainResolver statusDomainResolver,
		StatusApplicationMapper statusApplicationMapper,
		GenreDomainResolver genreDomainResolver,
		GenreApplicationMapper genreApplicationMapper,
		ContentQueryRepository contentQueryRepository,
		ContentCommandRepository contentCommandRepository,
		EventDispatcherInterface eventDispatcherInterface
	) {
		this.contentApplicationMapper = contentApplicationMapper;
		this.statusDomainResolver = statusDomainResolver;
		this.statusApplicationMapper = statusApplicationMapper;
		this.genreDomainResolver = genreDomainResolver;
		this.genreApplicationMapper = genreApplicationMapper;
		this.contentQueryRepository = contentQueryRepository;
		this.contentCommandRepository = contentCommandRepository;
		this.eventDispatcherInterface = eventDispatcherInterface;
	}

	@Override
    public void update(ContentDto contentDto) {
        Content content = contentQueryRepository.find(contentDto.id(), null)
            .orElseThrow(() -> new ContentNotFoundException(contentDto.id()));

        UUID statusId = contentDto.status().id();
        Status status = statusDomainResolver.resolve(statusId);

        Set<UUID> requestedGenreIdSet = contentDto.genreSet().stream()
            .map(GenreDto::id)
            .collect(Collectors.toSet());
        Set<Genre> genreSet = genreDomainResolver.resolve(requestedGenreIdSet);

        ContentUpdate contentUpdate = contentApplicationMapper.toContentUpdate(
            contentDto,
            status,
            genreSet
        );
        content.update(contentUpdate);
        contentCommandRepository.update(content);

		Content created = contentQueryRepository.find(content.getId(), null)
			.orElseThrow(() -> new ContentNotFoundException(content.getId()));

		StatusDto statusDto = statusApplicationMapper.toStatusDto(status);
		Set<GenreDto> genreDtoSet = genreSet.stream().map(genreApplicationMapper::toGenreDto).collect(Collectors.toSet());
		eventDispatcherInterface.dispatch(
			new ContentUpdateEvent(
				contentApplicationMapper.toContentDto(
					created,
					statusDto,
					genreDtoSet
				)
			)
		);
    }
}
