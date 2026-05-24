package dev.animedia.contentservice.application.content.service;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import dev.animedia.contentservice.application.content.exception.ContentExistsException;
import dev.animedia.contentservice.application.content.mapper.ContentApplicationMapper;
import dev.animedia.contentservice.application.content.usecase.CreateContentUseCase;
import dev.animedia.contentservice.application.genre.dto.GenreDto;
import dev.animedia.contentservice.application.genre.exception.GenreNotFoundException;
import dev.animedia.contentservice.application.genre.mapper.GenreApplicationMapper;
import dev.animedia.contentservice.application.genre.usecase.GetGenreListUseCase;
import dev.animedia.contentservice.application.status.mapper.StatusApplicationMapper;
import dev.animedia.contentservice.application.status.usecase.GetStatusUseCase;
import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.repository.ContentCommandRepository;
import dev.animedia.contentservice.domain.content.repository.ContentQueryRepository;
import dev.animedia.contentservice.domain.genre.model.Genre;

import java.util.List;

public class CreateContentService implements CreateContentUseCase {
    private final ContentApplicationMapper contentApplicationMapper;
    private final StatusApplicationMapper statusApplicationMapper;
    private final GenreApplicationMapper genreApplicationMapper;
    private final ContentQueryRepository contentQueryRepository;
    private final ContentCommandRepository contentCommandRepository;
    private final GetStatusUseCase getStatusUseCase;
    private final GetGenreListUseCase getGenreListUseCase;

    public CreateContentService(
        ContentApplicationMapper contentApplicationMapper,
	    StatusApplicationMapper statusApplicationMapper,
	    GenreApplicationMapper genreApplicationMapper,
        ContentQueryRepository contentQueryRepository,
        ContentCommandRepository contentCommandRepository,
        GetStatusUseCase getStatusUseCase,
        GetGenreListUseCase getGenreListUseCase
    ) {
        this.contentApplicationMapper = contentApplicationMapper;
	    this.statusApplicationMapper = statusApplicationMapper;
	    this.genreApplicationMapper = genreApplicationMapper;
	    this.contentQueryRepository = contentQueryRepository;
        this.contentCommandRepository = contentCommandRepository;
        this.getStatusUseCase = getStatusUseCase;
        this.getGenreListUseCase = getGenreListUseCase;
    }

    @Override
    public ContentDto create(ContentDto contentDto) {

        Content content = contentApplicationMapper.toContent(
            contentDto,
            statusApplicationMapper::toStatus,
            genreApplicationMapper::toGenre
        );

        boolean contentExists = contentQueryRepository.exists(content.getAlias(), content.getType(), content.getSeason());
        if (contentExists) throw new ContentExistsException();

        // check status exists
        getStatusUseCase.get(content.getStatus().getId(), false, null);

        List<Long> inputGenreIdList = content.getGenreSet()
            .stream()
            .map(Genre::getId)
            .distinct()
            .toList();
        List<Long> foundGenreIdList = getGenreListUseCase.getList(inputGenreIdList, false, null)
            .stream()
            .map(GenreDto::id)
            .toList();
        if (inputGenreIdList.size() != foundGenreIdList.size()) {
            List<Long> genreNotFoundIdList = inputGenreIdList
                .stream()
                .filter(inputGenreId -> !foundGenreIdList.contains(inputGenreId))
                .toList();
            throw new GenreNotFoundException(genreNotFoundIdList);
        }

        Content saved = contentCommandRepository.create(content);

        return contentApplicationMapper.toContentDto(
            saved,
            statusApplicationMapper::toStatusDto,
            genreApplicationMapper::toGenreDto
        );
    }
}
