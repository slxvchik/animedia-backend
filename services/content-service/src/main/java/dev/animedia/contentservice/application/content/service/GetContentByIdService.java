package dev.animedia.contentservice.application.content.service;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import dev.animedia.contentservice.application.content.exception.ContentNotFoundException;
import dev.animedia.contentservice.application.content.mapper.ContentApplicationMapper;
import dev.animedia.contentservice.application.content.usecase.GetContentByIdUseCase;
import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.repository.ContentQueryRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetContentByIdService implements GetContentByIdUseCase {
    private final ContentApplicationMapper contentApplicationMapper;
    private final ContentQueryRepository contentQueryRepository;

    @Autowired
    public GetContentByIdService(
        ContentApplicationMapper contentApplicationMapper,
        ContentQueryRepository contentQueryRepository
    ) {
        this.contentApplicationMapper = contentApplicationMapper;
        this.contentQueryRepository = contentQueryRepository;
    }

    @Override
    public ContentDto get(UUID uuid, @Nullable String languageCode) {
        Content content = contentQueryRepository.find(uuid, languageCode)
            .orElseThrow(ContentNotFoundException::new);
        return contentApplicationMapper.toContentDto(content);
    }
}
