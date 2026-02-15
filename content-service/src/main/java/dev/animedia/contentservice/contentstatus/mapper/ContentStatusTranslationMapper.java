package dev.animedia.contentservice.contentstatus.mapper;

import dev.animedia.contentservice.contentstatus.dto.request.CreateContentStatusTranslationRequestDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusTranslationResponseDto;
import dev.animedia.contentservice.contentstatus.model.ContentStatus;
import dev.animedia.contentservice.contentstatus.model.ContentStatusTranslation;
import dev.animedia.contentservice.language.Language;
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
        Language language = entityManager.getReference(Language.class, createContentStatusTranslationRequestDto.languageCode());

        ContentStatusTranslation contentStatusTranslation = new ContentStatusTranslation();
        contentStatusTranslation.setContentStatus(contentStatus);
        contentStatusTranslation.setLanguage(language);
        contentStatusTranslation.setName(contentStatusTranslation.getName());

        return contentStatusTranslation;
    }

    public ContentStatusTranslationResponseDto toContentStatusTranslationResponseDto(ContentStatusTranslation contentStatusTranslation) {
        return new ContentStatusTranslationResponseDto(
            contentStatusTranslation.getId(),
            contentStatusTranslation.getContentStatus().getId(),
            contentStatusTranslation.getLanguage().getCode(),
            contentStatusTranslation.getName()
        );
    }

    public List<ContentStatusTranslationResponseDto> toContentStatusTranslationsResponseDto(List<>) {
        return
    }
}
