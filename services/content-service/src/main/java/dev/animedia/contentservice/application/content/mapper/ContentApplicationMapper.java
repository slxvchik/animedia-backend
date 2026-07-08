package dev.animedia.contentservice.application.content.mapper;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import dev.animedia.contentservice.application.content.dto.ContentTranslationDto;
import dev.animedia.contentservice.application.genre.dto.GenreDto;
import dev.animedia.contentservice.application.status.dto.StatusDto;
import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.model.ContentTranslation;
import dev.animedia.contentservice.domain.content.model.ContentUpdate;
import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.status.model.Status;

import java.util.Set;
import java.util.stream.Collectors;

public class ContentApplicationMapper {
    public Content toContent(
        ContentDto contentDto,
        Status status,
        Set<Genre> genreSet
    ) {
        if (contentDto == null) return null;
        return Content.builder()
            .id(contentDto.id())
            .alias(contentDto.alias())
            .type(contentDto.type())
            .season(contentDto.season())
            .status(
                status
            )
            .coverUrl(contentDto.coverImageId())
            .trailerUrl(contentDto.trailerVideoId())
            .releaseDate(contentDto.releaseDate())
            .createdAt(contentDto.createdAt())
            .updatedAt(contentDto.updatedAt())
            .active(contentDto.active())
            .sort(contentDto.sortOrder())
            .languageCodeSet(contentDto.languageCodeSet())
            .genreSet(
                genreSet
            )
            .translationSet(
                contentDto.translationSet() == null ? null
                : contentDto.translationSet().stream()
                    .map(this::toContentTranslation)
                    .collect(Collectors.toSet())
            )
            .build();
    }

    public ContentTranslation toContentTranslation(ContentTranslationDto contentTranslationDto) {
        if (contentTranslationDto == null) return null;
        return new ContentTranslation(
            contentTranslationDto.id(),
            contentTranslationDto.languageCode(),
            contentTranslationDto.title(),
            contentTranslationDto.description()
        );
    }

    public ContentDto toContentDto(
        Content content,
        StatusDto statusDto,
        Set<GenreDto> genreDtoSet
    ) {
        if (content == null) return null;
        return new ContentDto(
            content.getId(),
            content.getAlias(),
            content.getType(),
            content.getSeason(),
            statusDto,
            content.getCoverImageId(),
            content.getTrailerVideoId(),
            content.getReleaseDate(),
            content.getCreatedAt(),
            content.getUpdatedAt(),
            content.getActive(),
            content.getSort(),
            content.getLanguageCodeSet(),
            genreDtoSet,
            content.getTranslationSet().stream()
                .map(this::toContentTranslationDto)
                .collect(Collectors.toUnmodifiableSet())
        );
    }

    public ContentTranslationDto toContentTranslationDto(ContentTranslation contentTranslation) {
        if (contentTranslation == null) return null;
        return new ContentTranslationDto(
            contentTranslation.getId(),
            contentTranslation.getLanguageCode(),
            contentTranslation.getTitle(),
            contentTranslation.getDescription()
        );
    }

    public ContentUpdate toContentUpdate(
        ContentDto contentDto,
        Status status,
        Set<Genre> genreSet
    ) {
        if (contentDto == null) return null;
        return new ContentUpdate(
            status,
            contentDto.coverImageId(),
            contentDto.trailerVideoId(),
            contentDto.releaseDate(),
            contentDto.active(),
            contentDto.sortOrder(),
            contentDto.languageCodeSet(),
            genreSet,
            contentDto.translationSet().stream()
                .map(this::toContentTranslation)
                .collect(Collectors.toSet())
        );
    }
}
