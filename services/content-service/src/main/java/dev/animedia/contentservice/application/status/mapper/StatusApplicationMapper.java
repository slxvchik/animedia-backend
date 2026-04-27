package dev.animedia.contentservice.application.status.mapper;

import dev.animedia.contentservice.application.status.dto.StatusSearchDto;
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
        if (statusDto == null) return null;
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
        if (statusTranslationDto == null) return null;
        return new StatusTranslation(
            statusTranslationDto.id(),
            statusTranslationDto.languageCode(),
            statusTranslationDto.name()
        );
    }

    public StatusDto toStatusDto(Status status) {
        if (status == null) return null;
        return new StatusDto(
            status.getId(),
            status.getAlias(),
            status.getSortOrder(),
            status.getTranslationSet()
                .stream()
                .map(this::toStatusTranslationDto)
                .collect(Collectors.toUnmodifiableSet())
        );
    }

    public StatusTranslationDto toStatusTranslationDto(StatusTranslation statusTranslation) {
        if (statusTranslation == null) return null;
        return new StatusTranslationDto(
            statusTranslation.getId(),
            statusTranslation.getLanguageCode(),
            statusTranslation.getName()
        );
    }

    public StatusSearchCriteria toStatusSearchCriteria(StatusSearchDto searchStatusDto) {
        return new StatusSearchCriteria(
            searchStatusDto.alias(),
            searchStatusDto.name(),
            searchStatusDto.languageCode()
        );
    }
}
