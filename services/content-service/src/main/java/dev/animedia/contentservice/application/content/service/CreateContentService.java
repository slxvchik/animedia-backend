package dev.animedia.contentservice.application.content.service;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import dev.animedia.contentservice.application.content.exception.ContentExistsException;
import dev.animedia.contentservice.application.content.mapper.ContentApplicationMapper;
import dev.animedia.contentservice.application.content.usecase.CreateContentUseCase;
import dev.animedia.contentservice.application.genre.mapper.GenreApplicationMapper;
import dev.animedia.contentservice.application.status.mapper.StatusApplicationMapper;
import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.repository.ContentCommandRepository;
import dev.animedia.contentservice.domain.content.repository.ContentQueryRepository;

public class CreateContentService implements CreateContentUseCase {
    private final ContentApplicationMapper contentApplicationMapper;
    private final StatusApplicationMapper statusApplicationMapper;
    private final GenreApplicationMapper genreApplicationMapper;
    private final ContentQueryRepository contentQueryRepository;
    private final ContentCommandRepository contentCommandRepository;
    private final CheckContentRelationsExistsService checkContentRelationsExistsService;

    public CreateContentService(
        ContentApplicationMapper contentApplicationMapper,
	    StatusApplicationMapper statusApplicationMapper,
	    GenreApplicationMapper genreApplicationMapper,
        ContentQueryRepository contentQueryRepository,
        ContentCommandRepository contentCommandRepository,
	    CheckContentRelationsExistsService checkContentRelationsExistsService
    ) {
        this.contentApplicationMapper = contentApplicationMapper;
	    this.statusApplicationMapper = statusApplicationMapper;
	    this.genreApplicationMapper = genreApplicationMapper;
	    this.contentQueryRepository = contentQueryRepository;
        this.contentCommandRepository = contentCommandRepository;
	    this.checkContentRelationsExistsService = checkContentRelationsExistsService;
    }

    @Override
    public ContentDto create(ContentDto contentDto) {

        Content content = contentApplicationMapper.toContent(
            contentDto,
            statusApplicationMapper::toStatus,
            genreApplicationMapper::toGenre
        );

        boolean contentExists = contentQueryRepository.exists(content.getAlias(), content.getType(), content.getSeason());
        if (contentExists) throw new ContentExistsException(content.getAlias(), content.getType(), content.getSeason());

        checkContentRelationsExistsService.check(content);

        Content saved = contentCommandRepository.create(content);

        return contentApplicationMapper.toContentDto(
            saved,
            statusApplicationMapper::toStatusDto,
            genreApplicationMapper::toGenreDto
        );
    }
}
