package dev.animedia.contentservice.content.infrastracture.persistence.mapper;

import dev.animedia.contentservice.content.domain.model.Content;
import dev.animedia.contentservice.content.domain.model.ContentTranslation;
import dev.animedia.contentservice.content.infrastracture.persistence.model.ContentEntity;
import dev.animedia.contentservice.content.infrastracture.persistence.model.ContentTranslationEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ContentPersistenceMapper {
    public Content toContent(
        ContentEntity ce
    ) {
        if (ce == null) return null;
        return Content.builder()
            .id(ce.getId())
            .alias(ce.getAlias())
            .type(ce.getContentType())
            .season(ce.getSeason())
            .statusId(ce.getStatusId())
            .coverUrlId(ce.getCoverImageId())
            .trailerUrlId(ce.getTrailerVideoId())
            .releaseDate(ce.getReleaseDate())
            .createdAt(ce.getCreatedAt())
            .updatedAt(ce.getUpdatedAt())
            .active(ce.getActive())
            .sort(ce.getSortOrder())
            .languageCodeSet(ce.getLanguageCodes())
            .genreIdSet(ce.getGenreIds())
            .translationSet(
                ce.getTranslations() == null ? null
                : ce.getTranslations()
                    .stream()
                    .map(this::toContentTranslation)
                    .collect(Collectors.toSet())
            )
            .build();
    }

    public ContentTranslation toContentTranslation(ContentTranslationEntity cte) {
        if (cte == null) return null;
        return new ContentTranslation(
            cte.getId(),
            cte.getLanguageCode(),
            cte.getTitle(),
            cte.getDescription()
        );
    }

    public ContentEntity toContentEntity(
        Content content
    ) {
        if (content == null) return null;

        ContentEntity ce = new ContentEntity();
        ce.setId(content.getId());
        ce.setAlias(content.getAlias());
        ce.setContentType(content.getType());
        ce.setSeason(content.getSeason());
        ce.setStatusId(content.getStatusId());
        ce.setCoverImageId(content.getCoverImageId());
        ce.setTrailerVideoId(content.getTrailerVideoId());
        ce.setReleaseDate(content.getReleaseDate());
        ce.setCreatedAt(content.getCreatedAt());
        ce.setUpdatedAt(content.getUpdatedAt());
        ce.setActive(content.getActive());
        ce.setSortOrder(content.getSort());

        ce.setLanguageCodes(content.getLanguageCodeSet());
        ce.setGenreIds(content.getGenreIdSet());

        ce.setTranslations(
            content.getTranslationSet() == null ? null
            : content.getTranslationSet().stream()
                .map(ct -> toContentTranslationEntity(ct, ce))
                .collect(Collectors.toSet())
        );

        return ce;
    }

    public ContentTranslationEntity toContentTranslationEntity(ContentTranslation contentTranslation, ContentEntity contentEntity) {
        if (contentTranslation == null) return null;

        ContentTranslationEntity cte = new ContentTranslationEntity();

        cte.setId(contentTranslation.getId());
        cte.setContentEntity(contentEntity);
        cte.setLanguageCode(contentTranslation.getLanguageCode());
        cte.setTitle(contentTranslation.getTitle());
        cte.setDescription(contentTranslation.getDescription());

        return cte;
    }
}