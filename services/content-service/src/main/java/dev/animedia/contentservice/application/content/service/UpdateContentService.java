package dev.animedia.contentservice.application.content.service;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import dev.animedia.contentservice.application.content.exception.ContentNotFoundException;
import dev.animedia.contentservice.application.content.mapper.ContentApplicationMapper;
import dev.animedia.contentservice.application.content.usecase.UpdateContentUseCase;
import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.model.ContentUpdate;
import dev.animedia.contentservice.domain.content.repository.ContentCommandRepository;
import dev.animedia.contentservice.domain.content.repository.ContentQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateContentService implements UpdateContentUseCase {
    private final ContentApplicationMapper contentApplicationMapper;
    private final ContentQueryRepository contentQueryRepository;
    private final ContentCommandRepository contentCommandRepository;

    @Autowired
    public UpdateContentService(
        ContentApplicationMapper contentApplicationMapper,
        ContentQueryRepository contentQueryRepository,
        ContentCommandRepository contentCommandRepository
    ) {
        this.contentApplicationMapper = contentApplicationMapper;
        this.contentQueryRepository = contentQueryRepository;
        this.contentCommandRepository = contentCommandRepository;
    }

    @Override
    public ContentDto update(ContentDto contentDto) {
        Content content = contentQueryRepository.find(contentDto.uuid(), null)
            .orElseThrow(ContentNotFoundException::new);

        ContentUpdate contentUpdate = contentApplicationMapper.toContentUpdate(contentDto);
        content.update(contentUpdate);
        Content updated = contentCommandRepository.update(content);

        return contentApplicationMapper.toContentDto(updated);
    }
}
