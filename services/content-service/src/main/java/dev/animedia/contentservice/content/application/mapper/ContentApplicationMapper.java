package dev.animedia.contentservice.content.application.mapper;

import dev.animedia.contentservice.content.application.dto.content.ContentRequestDto;
import dev.animedia.contentservice.content.application.dto.content.ContentResponseDto;
import dev.animedia.contentservice.content.application.dto.content.ContentTranslationDto;
import dev.animedia.contentservice.content.application.dto.genre.GenreDto;
import dev.animedia.contentservice.content.application.dto.status.StatusDto;
import dev.animedia.contentservice.content.domain.model.Content;
import dev.animedia.contentservice.content.domain.model.ContentTranslation;
import dev.animedia.contentservice.content.domain.model.ContentUpdate;

import java.util.Set;
import java.util.stream.Collectors;

public class ContentApplicationMapper {
    /**
     * For Create request
     */
    public Content toContent(
        ContentRequestDto contentRequestDto
    ) {
        if (contentRequestDto == null) return null;
        return Content.builder()
            .id(contentRequestDto.id())
            .alias(contentRequestDto.alias())
            .type(contentRequestDto.type())
            .season(contentRequestDto.season())
            .statusId(contentRequestDto.statusId())
            .coverUrlId(contentRequestDto.coverImageId())
            .trailerUrlId(contentRequestDto.trailerVideoId())
            .releaseDate(contentRequestDto.releaseDate())
            .createdAt(contentRequestDto.createdAt())
            .updatedAt(contentRequestDto.updatedAt())
            .active(contentRequestDto.active())
            .sort(contentRequestDto.sortOrder())
            .languageCodeSet(contentRequestDto.languageCodeSet())
            .genreIdSet(contentRequestDto.genreIdSet())
            .translationSet(
                contentRequestDto.translationSet() == null ? null
                : contentRequestDto.translationSet().stream()
                    .map(this::toContentTranslation)
                    .collect(Collectors.toSet())
            )
            .build();
    }

    /**
     * For requests (Create, Update)
     */
    public ContentTranslation toContentTranslation(ContentTranslationDto contentTranslationDto) {
        if (contentTranslationDto == null) return null;
        return new ContentTranslation(
            contentTranslationDto.id(),
            contentTranslationDto.languageCode(),
            contentTranslationDto.title(),
            contentTranslationDto.description()
        );
    }

    /**
     * For responses
     */
    public ContentResponseDto toContentResponseDto(
        Content content,
        StatusDto statusDto,
        Set<GenreDto> genreDtoSet
    ) {
        if (content == null) return null;
        return new ContentResponseDto(
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

	/**
	 * For responses
	 */
    public ContentTranslationDto toContentTranslationDto(ContentTranslation contentTranslation) {
        if (contentTranslation == null) return null;
        return new ContentTranslationDto(
            contentTranslation.getId(),
            contentTranslation.getLanguageCode(),
            contentTranslation.getTitle(),
            contentTranslation.getDescription()
        );
    }

	/**
	 * For Update request
	 */
    public ContentUpdate toContentUpdate(
        ContentRequestDto contentRequestDto
    ) {
        if (contentRequestDto == null) return null;
        return new ContentUpdate(
            contentRequestDto.statusId(),
            contentRequestDto.coverImageId(),
            contentRequestDto.trailerVideoId(),
            contentRequestDto.releaseDate(),
            contentRequestDto.active(),
            contentRequestDto.sortOrder(),
            contentRequestDto.languageCodeSet(),
            contentRequestDto.genreIdSet(),
            contentRequestDto.translationSet().stream()
                .map(this::toContentTranslation)
                .collect(Collectors.toSet())
        );
    }
}
