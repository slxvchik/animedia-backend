package dev.animedia.contentservice.application.content.service;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import dev.animedia.contentservice.application.content.exception.ContentNotFoundException;
import dev.animedia.contentservice.application.content.mapper.ContentApplicationMapper;
import dev.animedia.contentservice.application.content.usecase.UpdateContentUseCase;
import dev.animedia.contentservice.application.genre.mapper.GenreApplicationMapper;
import dev.animedia.contentservice.application.status.mapper.StatusApplicationMapper;
import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.model.ContentUpdate;
import dev.animedia.contentservice.domain.content.repository.ContentCommandRepository;
import dev.animedia.contentservice.domain.content.repository.ContentQueryRepository;

public class UpdateContentService implements UpdateContentUseCase {
    private final ContentApplicationMapper contentApplicationMapper;
    private final ContentQueryRepository contentQueryRepository;
    private final ContentCommandRepository contentCommandRepository;
    private final StatusApplicationMapper statusApplicationMapper;
    private final GenreApplicationMapper genreApplicationMapper;
    private final CheckContentRelationsExistsService checkContentRelationsExistsService;

    public UpdateContentService(
        ContentApplicationMapper contentApplicationMapper,
        ContentQueryRepository contentQueryRepository,
        ContentCommandRepository contentCommandRepository,
        StatusApplicationMapper statusApplicationMapper,
        GenreApplicationMapper genreApplicationMapper,
	    CheckContentRelationsExistsService checkContentRelationsExistsService
    ) {
        this.contentApplicationMapper = contentApplicationMapper;
        this.contentQueryRepository = contentQueryRepository;
        this.contentCommandRepository = contentCommandRepository;
        this.statusApplicationMapper = statusApplicationMapper;
        this.genreApplicationMapper = genreApplicationMapper;
	    this.checkContentRelationsExistsService = checkContentRelationsExistsService;
    }

    @Override
    public ContentDto update(ContentDto contentDto) {
        Content content = contentQueryRepository.find(contentDto.id(), null, null)
            .orElseThrow(ContentNotFoundException::new);

        checkContentRelationsExistsService.check(content);

        ContentUpdate contentUpdate = contentApplicationMapper.toContentUpdate(
            contentDto,
            statusApplicationMapper::toStatus,
            genreApplicationMapper::toGenre
        );
        content.update(contentUpdate);
        Content updated = contentCommandRepository.update(content);

        return contentApplicationMapper.toContentDto(
            updated,
            statusApplicationMapper::toStatusDto,
            genreApplicationMapper::toGenreDto
        );
    }
}
