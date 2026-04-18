package dev.animedia.contentservice.application.content.service;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import dev.animedia.contentservice.application.content.exception.ContentNotFoundException;
import dev.animedia.contentservice.application.content.mapper.ContentApplicationMapper;
import dev.animedia.contentservice.application.content.usecase.GetContentByDetailsUseCase;
import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.model.ContentType;
import dev.animedia.contentservice.domain.content.repository.ContentQueryRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetContentByDetailsService implements GetContentByDetailsUseCase {
    private final ContentApplicationMapper contentApplicationMapper;
    private final ContentQueryRepository contentQueryRepository;

    @Autowired
    public GetContentByDetailsService(
        ContentApplicationMapper contentApplicationMapper,
        ContentQueryRepository contentQueryRepository
    ) {
        this.contentApplicationMapper = contentApplicationMapper;
        this.contentQueryRepository = contentQueryRepository;
    }

    @Override
    public ContentDto get(String alias, ContentType type, @Nullable Integer season, @Nullable String languageCode) {
        Content content = contentQueryRepository.find(alias, type, season, languageCode)
            .orElseThrow(ContentNotFoundException::new);
        return contentApplicationMapper.toContentDto(content);
    }
}
