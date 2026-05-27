package dev.animedia.contentservice.infrastructure.persistence.content.mapper;

import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.model.ContentTranslation;
import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.status.model.Status;
import dev.animedia.contentservice.infrastructure.persistence.content.model.ContentEntity;
import dev.animedia.contentservice.infrastructure.persistence.content.model.ContentTranslationEntity;
import dev.animedia.contentservice.infrastructure.persistence.genre.model.GenreEntity;
import dev.animedia.contentservice.infrastructure.persistence.status.model.StatusEntity;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ContentPersistenceMapper {
    public Content toContent(
        ContentEntity ce,
        Function<StatusEntity, Status> statusMapper,
        Function<GenreEntity, Genre> genreMapper
    ) {
        if (ce == null) return null;
        return Content.builder()
            .id(ce.getId())
            .alias(ce.getAlias())
            .type(ce.getContentType())
            .season(ce.getSeason())
            .status(
                ce.getStatusEntity() == null ? null
                : statusMapper.apply(ce.getStatusEntity())
            )
            .coverUrl(ce.getCoverImageId())
            .trailerUrl(ce.getTrailerVideoId())
            .releaseDate(ce.getReleaseDate())
            .createdAt(ce.getCreatedAt())
            .updatedAt(ce.getUpdatedAt())
            .active(ce.getActive())
            .sort(ce.getSortOrder())
            .languageCodeSet(ce.getLanguageCodes())
            .genreSet(
                ce.getGenres() == null ? null
                : ce.getGenres()
                    .stream()
                    .map(genreMapper)
                    .collect(Collectors.toSet())
            )
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
        Content content,
        Function<Status, StatusEntity> statusMapper,
        Function<Genre, GenreEntity> genreMapper
    ) {
        if (content == null) return null;

        ContentEntity ce = new ContentEntity();
        ce.setId(content.getId());
        ce.setAlias(content.getAlias());
        ce.setContentType(content.getType());
        ce.setSeason(content.getSeason());
        ce.setStatusEntity(
            content.getStatus() == null ? null
            : statusMapper.apply(content.getStatus())
        );
        ce.setCoverImageId(content.getCoverUrl());
        ce.setTrailerVideoId(content.getTrailerUrl());
        ce.setReleaseDate(content.getReleaseDate());
        ce.setCreatedAt(content.getCreatedAt());
        ce.setUpdatedAt(content.getUpdatedAt());
        ce.setActive(content.getActive());
        ce.setSortOrder(content.getSort());

        ce.setLanguageCodes(content.getLanguageCodeSet());
        ce.setGenres(
            content.getGenreSet() == null ? null
            : content.getGenreSet()
                .stream()
                .map(genreMapper)
                .collect(Collectors.toSet())
        );

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