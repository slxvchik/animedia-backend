package dev.animedia.contentservice.application.content.mapper;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import dev.animedia.contentservice.application.content.dto.ContentSearchDto;
import dev.animedia.contentservice.application.content.dto.ContentTranslationDto;
import dev.animedia.contentservice.application.genre.mapper.GenreApplicationMapper;
import dev.animedia.contentservice.application.status.mapper.StatusApplicationMapper;
import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.model.ContentSearchCriteria;
import dev.animedia.contentservice.domain.content.model.ContentTranslation;
import dev.animedia.contentservice.domain.content.model.ContentUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ContentApplicationMapper {
    private final StatusApplicationMapper statusApplicationMapper;
    private final GenreApplicationMapper genreApplicationMapper;

    @Autowired
    public ContentApplicationMapper(
        StatusApplicationMapper statusApplicationMapper,
        GenreApplicationMapper genreApplicationMapper
    ) {
        this.statusApplicationMapper = statusApplicationMapper;
        this.genreApplicationMapper = genreApplicationMapper;
    }

    public Content toContent(ContentDto contentDto) {
        return Content.builder()
            .uuid(contentDto.uuid())
            .alias(contentDto.alias())
            .type(contentDto.type())
            .season(contentDto.season())
            .status(
                statusApplicationMapper.toStatus(contentDto.status())
            )
            .coverUrl(contentDto.coverUrl())
            .trailerUrl(contentDto.trailerUrl())
            .releaseDate(contentDto.releaseDate())
            .createdAt(contentDto.createdAt())
            .updatedAt(contentDto.updatedAt())
            .active(contentDto.active())
            .sort(contentDto.sort())
            .languageCodeSet(contentDto.languageCodeSet())
            .genreSet(
                contentDto.genreSet().stream()
                    .map(genreApplicationMapper::toGenre)
                    .collect(Collectors.toSet())
            )
            .translationSet(
                contentDto.translationSet().stream()
                    .map(this::toContentTranslation)
                    .collect(Collectors.toSet())
            )
            .build();
    }

    public ContentTranslation toContentTranslation(ContentTranslationDto contentTranslationDto) {
        return new ContentTranslation(
            contentTranslationDto.uuid(),
            contentTranslationDto.languageCode(),
            contentTranslationDto.title(),
            contentTranslationDto.description()
        );
    }

    public ContentDto toContentDto(Content content) {
        return new ContentDto(
            content.getUuid(),
            content.getAlias(),
            content.getType(),
            content.getSeason(),
            statusApplicationMapper.toStatusDto(
                content.getStatus()
            ),
            content.getCoverUrl(),
            content.getTrailerUrl(),
            content.getReleaseDate(),
            content.getCreatedAt(),
            content.getUpdatedAt(),
            content.isActive(),
            content.getSort(),
            content.getLanguageCodeSet(),
            content.getGenreSet().stream()
                .map(genreApplicationMapper::toGenreDto)
                .collect(Collectors.toUnmodifiableSet()),
            content.getTranslationSet().stream()
                .map(this::toContentTranslationDto)
                .collect(Collectors.toUnmodifiableSet())
        );
    }

    public ContentTranslationDto toContentTranslationDto(ContentTranslation contentTranslation) {
        return new ContentTranslationDto(
            contentTranslation.getUuid(),
            contentTranslation.getLanguageCode(),
            contentTranslation.getTitle(),
            contentTranslation.getDescription()
        );
    }

    public ContentUpdate toContentUpdate(ContentDto contentDto) {
        return new ContentUpdate(
            contentDto.alias(),
            contentDto.type(),
            contentDto.season(),
            statusApplicationMapper.toStatus(contentDto.status()),
            contentDto.coverUrl(),
            contentDto.trailerUrl(),
            contentDto.releaseDate(),
            contentDto.active(),
            contentDto.sort(),
            contentDto.languageCodeSet(),
            contentDto.genreSet().stream()
                .map(genreApplicationMapper::toGenre)
                .collect(Collectors.toSet()),
            contentDto.translationSet().stream()
                .map(this::toContentTranslation)
                .collect(Collectors.toSet())
        );
    }

    public ContentSearchCriteria toContentSearchCriteria(ContentSearchDto contentSearchDto) {
        return new ContentSearchCriteria(
            contentSearchDto.uuidList(),
            contentSearchDto.aliasList(),
            contentSearchDto.titleList(),
            contentSearchDto.typeList(),
            contentSearchDto.seasonList(),
            contentSearchDto.statusIdList(),
            contentSearchDto.releaseDateFrom(),
            contentSearchDto.releaseDateTo(),
            contentSearchDto.active(),
            contentSearchDto.languageCodeList(),
            contentSearchDto.genreIdList()
        );
    }
}
