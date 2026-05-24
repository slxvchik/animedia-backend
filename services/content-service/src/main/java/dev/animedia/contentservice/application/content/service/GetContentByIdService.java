package dev.animedia.contentservice.application.content.service;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import dev.animedia.contentservice.application.content.exception.ContentNotFoundException;
import dev.animedia.contentservice.application.content.mapper.ContentApplicationMapper;
import dev.animedia.contentservice.application.content.usecase.GetContentByIdUseCase;
import dev.animedia.contentservice.application.genre.mapper.GenreApplicationMapper;
import dev.animedia.contentservice.application.status.mapper.StatusApplicationMapper;
import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.repository.ContentQueryRepository;
import org.jspecify.annotations.Nullable;

import java.util.UUID;

public class GetContentByIdService implements GetContentByIdUseCase {
    private final ContentApplicationMapper contentApplicationMapper;
    private final ContentQueryRepository contentQueryRepository;
    private final StatusApplicationMapper statusApplicationMapper;
    private final GenreApplicationMapper genreApplicationMapper;

    public GetContentByIdService(
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
    public ContentDto get(UUID uuid, boolean onlyActive, @Nullable String languageCode) {
        Content content = contentQueryRepository.find(uuid, onlyActive, languageCode)
            .orElseThrow(ContentNotFoundException::new);

        return contentApplicationMapper.toContentDto(
            content,
            statusApplicationMapper::toStatusDto,
            genreApplicationMapper::toGenreDto
        );
    }
}
