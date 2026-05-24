package dev.animedia.contentservice.application.content.service;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import dev.animedia.contentservice.application.content.dto.ContentTranslationDto;
import dev.animedia.contentservice.application.content.exception.ContentNotFoundException;
import dev.animedia.contentservice.application.content.mapper.ContentApplicationMapper;
import dev.animedia.contentservice.application.content.usecase.SaveContentTranslationUseCase;
import dev.animedia.contentservice.application.genre.mapper.GenreApplicationMapper;
import dev.animedia.contentservice.application.status.mapper.StatusApplicationMapper;
import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.model.ContentTranslation;
import dev.animedia.contentservice.domain.content.repository.ContentCommandRepository;
import dev.animedia.contentservice.domain.content.repository.ContentQueryRepository;

import java.util.UUID;

public class SaveContentTranslationService implements SaveContentTranslationUseCase {
    private final ContentApplicationMapper contentApplicationMapper;
    private final ContentQueryRepository contentQueryRepository;
    private final ContentCommandRepository contentCommandRepository;
    private final StatusApplicationMapper statusApplicationMapper;
    private final GenreApplicationMapper genreApplicationMapper;

    public SaveContentTranslationService(
        ContentApplicationMapper contentApplicationMapper,
        ContentQueryRepository contentQueryRepository,
        ContentCommandRepository contentCommandRepository,
        StatusApplicationMapper statusApplicationMapper,
	    GenreApplicationMapper genreApplicationMapper
    ) {
        this.contentApplicationMapper = contentApplicationMapper;
        this.contentQueryRepository = contentQueryRepository;
        this.contentCommandRepository = contentCommandRepository;
	    this.statusApplicationMapper = statusApplicationMapper;
	    this.genreApplicationMapper = genreApplicationMapper;
    }

    @Override
    public ContentDto saveTranslation(UUID contentUuid, ContentTranslationDto contentTranslationDto) {
        Content content = contentQueryRepository.find(contentUuid, false, null)
            .orElseThrow(ContentNotFoundException::new);

        ContentTranslation contentTranslation = contentApplicationMapper.toContentTranslation(contentTranslationDto);
        content.saveTranslation(contentTranslation);

        Content updated = contentCommandRepository.update(content);

        return contentApplicationMapper.toContentDto(
            updated,
            statusApplicationMapper::toStatusDto,
            genreApplicationMapper::toGenreDto
        );
    }
}
