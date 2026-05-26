package dev.animedia.contentservice.application.content.mapper;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import dev.animedia.contentservice.application.content.dto.ContentSearchDto;
import dev.animedia.contentservice.application.content.dto.ContentTranslationDto;
import dev.animedia.contentservice.application.genre.dto.GenreDto;
import dev.animedia.contentservice.application.status.dto.StatusDto;
import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.model.ContentSearchCriteria;
import dev.animedia.contentservice.domain.content.model.ContentTranslation;
import dev.animedia.contentservice.domain.content.model.ContentUpdate;
import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.status.model.Status;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ContentApplicationMapper {
    public Content toContent(
        ContentDto contentDto,
        Function<StatusDto, Status> statusMapper,
        Function<GenreDto, Genre> genreMapper
    ) {
        if (contentDto == null) return null;
        return Content.builder()
            .id(contentDto.id())
            .alias(contentDto.alias())
            .type(contentDto.type())
            .season(contentDto.season())
            .status(
                statusMapper.apply(contentDto.status())
            )
            .coverUrl(contentDto.coverImageId())
            .trailerUrl(contentDto.trailerVideoId())
            .releaseDate(contentDto.releaseDate())
            .createdAt(contentDto.createdAt())
            .updatedAt(contentDto.updatedAt())
            .active(contentDto.active())
            .sort(contentDto.sort())
            .languageCodeSet(contentDto.languageCodeSet())
            .genreSet(
                contentDto.genreSet()
                    .stream()
                    .map(genreMapper)
                    .collect(Collectors.toSet())
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
            contentTranslationDto.uuid(),
            contentTranslationDto.languageCode(),
            contentTranslationDto.title(),
            contentTranslationDto.description()
        );
    }

    public ContentDto toContentDto(
        Content content,
        Function<Status, StatusDto> statusMapper,
        Function<Genre, GenreDto> genreMapper
    ) {
        if (content == null) return null;

        StatusDto statusDto = statusMapper.apply(content.getStatus());

        Set<GenreDto> genreDtoSet = content.getGenreSet().stream()
            .map(genreMapper)
            .collect(Collectors.toSet());

        return new ContentDto(
            content.getId(),
            content.getAlias(),
            content.getType(),
            content.getSeason(),
            statusDto,
            content.getCoverUrl(),
            content.getTrailerUrl(),
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
        Function<StatusDto, Status> statusMapper,
        Function<GenreDto, Genre> genreMapper
    ) {
        if (contentDto == null) return null;
        return new ContentUpdate(
            statusMapper.apply(contentDto.status()),
            contentDto.coverImageId(),
            contentDto.trailerVideoId(),
            contentDto.releaseDate(),
            contentDto.active(),
            contentDto.sort(),
            contentDto.languageCodeSet(),
            contentDto.genreSet()
                .stream()
                .map(genreMapper)
                .collect(Collectors.toSet()),
            contentDto.translationSet().stream()
                .map(this::toContentTranslation)
                .collect(Collectors.toSet())
        );
    }

    public ContentSearchCriteria toContentSearchCriteria(ContentSearchDto contentSearchDto) {
        if (contentSearchDto == null) {
            return new ContentSearchCriteria(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        }
        return new ContentSearchCriteria(
            contentSearchDto.uuid(),
            contentSearchDto.aliasList(),
            contentSearchDto.titleList(),
            contentSearchDto.typeList(),
            contentSearchDto.seasonList(),
            contentSearchDto.statusIdList(),
            contentSearchDto.releaseDateFrom(),
            contentSearchDto.releaseDateTo(),
            contentSearchDto.createdAtFrom(),
            contentSearchDto.createdAtTo(),
            contentSearchDto.updatedAtFrom(),
            contentSearchDto.updatedAtTo(),
            contentSearchDto.active(),
            contentSearchDto.languageCodeList(),
            contentSearchDto.genreIdList(),
            contentSearchDto.translateLanguageCode()
        );
    }
}
