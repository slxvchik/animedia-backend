package dev.animedia.contentservice.status.mapper;

import dev.animedia.contentservice.status.dto.request.CreateContentStatusTranslationRequestDto;
import dev.animedia.contentservice.status.dto.response.ContentStatusTranslationResponseDto;
import dev.animedia.contentservice.status.model.ContentStatus;
import dev.animedia.contentservice.status.model.ContentStatusTranslation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContentStatusTranslationMapper {

    @PersistenceContext
    private EntityManager entityManager;

    public ContentStatusTranslation toContentStatusTranslation(CreateContentStatusTranslationRequestDto createContentStatusTranslationRequestDto) {

        ContentStatus contentStatus = entityManager.getReference(ContentStatus.class, createContentStatusTranslationRequestDto.contentStatusId());

        ContentStatusTranslation contentStatusTranslation = new ContentStatusTranslation();
        contentStatusTranslation.setContentStatus(contentStatus);
        contentStatusTranslation.setLanguageCode(createContentStatusTranslationRequestDto.languageCode());
        contentStatusTranslation.setName(contentStatusTranslation.getName());

        return contentStatusTranslation;
    }

    public ContentStatusTranslationResponseDto toContentStatusTranslationResponseDto(ContentStatusTranslation contentStatusTranslation) {
        if (contentStatusTranslation == null) return null;
        return new ContentStatusTranslationResponseDto(
            contentStatusTranslation.getId(),
            contentStatusTranslation.getContentStatus().getId(),
            contentStatusTranslation.getLanguageCode(),
            contentStatusTranslation.getName()
        );
    }

    public List<ContentStatusTranslationResponseDto> toContentStatusTranslationsResponseDto(List<ContentStatusTranslation> contentStatusTranslations) {
        return contentStatusTranslations.stream().map(this::toContentStatusTranslationResponseDto).toList();
    }
}
