package dev.animedia.contentservice.application.status.mapper;

import dev.animedia.contentservice.application.status.dto.SearchStatusDto;
import dev.animedia.contentservice.application.status.dto.StatusDto;
import dev.animedia.contentservice.application.status.dto.StatusTranslationDto;
import dev.animedia.contentservice.domain.status.model.Status;
import dev.animedia.contentservice.domain.status.model.StatusSearchCriteria;
import dev.animedia.contentservice.domain.status.model.StatusTranslation;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class StatusApplicationMapper {
    public Status toStatus(StatusDto statusDto) {
        return new Status(
            statusDto.id(),
            statusDto.alias(),
            statusDto.sortOrder(),
            statusDto.translationSet()
                .stream()
                .map(this::toStatusTranslation)
                .collect(Collectors.toSet())
        );
    }

    public StatusTranslation toStatusTranslation(StatusTranslationDto statusTranslationDto) {
        return new StatusTranslation(
            statusTranslationDto.id(),
            statusTranslationDto.languageCode(),
            statusTranslationDto.name()
        );
    }

    public StatusDto toStatusDto(Status status) {
        return new StatusDto(
            status.getId(),
            status.getAlias(),
            status.getSortOrder(),
            status.getTranslationSet()
                .stream()
                .map(this::toStatusTranslationDto)
                .collect(Collectors.toSet())
        );
    }

    public StatusTranslationDto toStatusTranslationDto(StatusTranslation statusTranslation) {
        return new StatusTranslationDto(
            statusTranslation.getId(),
            statusTranslation.getLanguageCode(),
            statusTranslation.getName()
        );
    }

    public StatusSearchCriteria toStatusSearchCriteria(SearchStatusDto searchStatusDto) {
        return new StatusSearchCriteria(
            searchStatusDto.aliasList(),
            searchStatusDto.nameList(),
            searchStatusDto.languageCodeList()
        );
    }
}
