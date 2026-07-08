package dev.animedia.contentservice.application.content.service.admin;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import dev.animedia.contentservice.application.content.mapper.ContentApplicationMapper;
import dev.animedia.contentservice.application.content.usecase.admin.GetAllContentUseCase;
import dev.animedia.contentservice.application.genre.mapper.GenreApplicationMapper;
import dev.animedia.contentservice.application.status.mapper.StatusApplicationMapper;
import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.repository.ContentQueryRepository;
import dev.animedia.contentservice.domain.shared.pagination.Page;
import dev.animedia.contentservice.domain.shared.pagination.Pageable;

import java.util.Set;
import java.util.stream.Collectors;

public class GetAllContentService implements GetAllContentUseCase {
    private final ContentApplicationMapper contentApplicationMapper;
    private final StatusApplicationMapper statusApplicationMapper;
    private final GenreApplicationMapper genreApplicationMapper;
    private final ContentQueryRepository contentQueryRepository;

    public GetAllContentService(
        ContentApplicationMapper contentApplicationMapper,
        StatusApplicationMapper statusApplicationMapper,
        GenreApplicationMapper genreApplicationMapper,
        ContentQueryRepository contentQueryRepository
    ) {
        this.contentApplicationMapper = contentApplicationMapper;
        this.statusApplicationMapper = statusApplicationMapper;
        this.genreApplicationMapper = genreApplicationMapper;
	    this.contentQueryRepository = contentQueryRepository;
    }

    @Override
    public Page<ContentDto> get(Pageable pageable) {
        Page<Content> contentPage = contentQueryRepository.findAll(pageable);
        return contentPage.changeContent(content -> contentApplicationMapper.toContentDto(
            content,
            statusApplicationMapper.toStatusDto(content.getStatus()),
            content.getGenreSet() != null
                ? content.getGenreSet().stream()
                    .map(genreApplicationMapper::toGenreDto)
                    .collect(Collectors.toSet())
                : Set.of()
        ));
    }
}