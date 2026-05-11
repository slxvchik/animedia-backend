package dev.animedia.contentservice.infrastructure.content.persistence.mapper;

import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.model.ContentTranslation;
import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.status.model.Status;
import dev.animedia.contentservice.infrastructure.content.persistence.model.ContentEntity;
import dev.animedia.contentservice.infrastructure.content.persistence.model.ContentTranslationEntity;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ContentPersistenceMapper {
    public Content toContent(ContentEntity ce, Set<ContentTranslationEntity> cteSet, Status status, Set<Genre> genreSet) {
        return Content.builder()
            .id(ce.getId())
            .alias(ce.getAlias())
            .type(ce.getContentType())
            .season(ce.getSeason())
            .status(status)
            .coverUrl(ce.getCoverUrl())
            .trailerUrl(ce.getTrailerUrl())
            .releaseDate(ce.getReleaseDate())
            .createdAt(ce.getCreatedAt())
            .updatedAt(ce.getUpdatedAt())
            .active(ce.getActive())
            .sort(ce.getSortOrder())
            .languageCodeSet(ce.getLanguageCodeSet())
            .genreSet(genreSet)
            .translationSet(
                cteSet
                    .stream()
                    .map(this::toContentTranslation)
                    .collect(Collectors.toSet())
            )
            .build();
    }
    public ContentTranslation toContentTranslation(ContentTranslationEntity cte) {
        return new ContentTranslation(
            cte.getId(),
            cte.getLanguageCode(),
            cte.getTitle(),
            cte.getDescription()
        );
    }
}
