package dev.animedia.contentservice.application.content.service;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import dev.animedia.contentservice.application.content.exception.ContentExistsException;
import dev.animedia.contentservice.application.content.mapper.ContentApplicationMapper;
import dev.animedia.contentservice.application.content.usecase.CreateContentUseCase;
import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.repository.ContentCommandRepository;
import dev.animedia.contentservice.domain.content.repository.ContentQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateContentService implements CreateContentUseCase {
    private final ContentApplicationMapper contentApplicationMapper;
    private final ContentQueryRepository contentQueryRepository;
    private final ContentCommandRepository contentCommandRepository;

    @Autowired
    public CreateContentService(
        ContentApplicationMapper contentApplicationMapper,
        ContentQueryRepository contentQueryRepository,
        ContentCommandRepository contentCommandRepository
    ) {
        this.contentApplicationMapper = contentApplicationMapper;
        this.contentQueryRepository = contentQueryRepository;
        this.contentCommandRepository = contentCommandRepository;
    }

    @Override
    public ContentDto create(ContentDto contentDto) {
        boolean contentExists = contentQueryRepository.exists(contentDto.alias(), contentDto.type(), contentDto.season());
        if (contentExists) throw new ContentExistsException();

        Content content = contentApplicationMapper.toContent(contentDto);
        Content saved = contentCommandRepository.create(content);

        return contentApplicationMapper.toContentDto(saved);
    }
}
