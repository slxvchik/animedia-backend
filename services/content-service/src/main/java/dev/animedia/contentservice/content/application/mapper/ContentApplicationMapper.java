package dev.animedia.contentservice.content.application.mapper;

import dev.animedia.contentservice.content.application.dto.content.request.CreateContentDto;
import dev.animedia.contentservice.content.application.dto.content.request.CreateContentTranslationDto;
import dev.animedia.contentservice.content.application.dto.content.request.UpdateContentDto;
import dev.animedia.contentservice.content.application.dto.content.request.UpdateContentTranslationDto;
import dev.animedia.contentservice.content.application.dto.content.response.ContentDto;
import dev.animedia.contentservice.content.application.dto.content.response.ContentTranslationDto;
import dev.animedia.contentservice.content.application.dto.genre.GenreDto;
import dev.animedia.contentservice.content.application.dto.status.StatusDto;
import dev.animedia.contentservice.content.domain.model.Content;
import dev.animedia.contentservice.content.domain.model.ContentTranslation;
import dev.animedia.contentservice.content.domain.model.UpdateContent;

import java.util.Set;
import java.util.stream.Collectors;

public class ContentApplicationMapper {
    public Content toContent(
        CreateContentDto createContentDto
    ) {
        if (createContentDto == null) return null;
        return Content.builder()
            .alias(createContentDto.alias())
            .type(createContentDto.type())
            .season(createContentDto.season())
            .statusId(createContentDto.statusId())
            .coverUrlId(createContentDto.coverImageId())
            .trailerUrlId(createContentDto.trailerVideoId())
            .releaseDate(createContentDto.releaseDate())
            .active(createContentDto.active())
            .sort(createContentDto.sortOrder())
            .languageCodes(createContentDto.languageCodes())
            .genreIds(createContentDto.genreIds())
            .translation(
                createContentDto.translations() == null ? null
                : createContentDto.translations().stream()
                    .map(this::toContentTranslation)
                    .collect(Collectors.toSet())
            )
            .build();
    }

    private ContentTranslation toContentTranslation(CreateContentTranslationDto contentTranslationDto) {
        if (contentTranslationDto == null) return null;
        return new ContentTranslation(
            null,
            contentTranslationDto.languageCode(),
            contentTranslationDto.title(),
            contentTranslationDto.description()
        );
    }

    /**
     * For Update request
     */
    public UpdateContent toContentUpdate(
        UpdateContentDto updateContentDto
    ) {
        if (updateContentDto == null) return null;
        return new UpdateContent(
            updateContentDto.statusId(),
            updateContentDto.coverImageId(),
            updateContentDto.trailerVideoId(),
            updateContentDto.releaseDate(),
            updateContentDto.active(),
            updateContentDto.sortOrder(),
            updateContentDto.languageCodes(),
            updateContentDto.genreIds(),
            updateContentDto.translations().stream()
                .map(this::toContentTranslation)
                .collect(Collectors.toSet())
        );
    }

    private ContentTranslation toContentTranslation(UpdateContentTranslationDto contentTranslationDto) {
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
    public ContentDto toContentResponseDto(
        Content content,
        StatusDto statusDto,
        Set<GenreDto> genreDtos
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
            content.getLanguageCodes(),
            genreDtos,
            content.getTranslations().stream()
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
}
