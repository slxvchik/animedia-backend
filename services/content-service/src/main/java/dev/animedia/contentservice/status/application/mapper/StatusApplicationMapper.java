package dev.animedia.contentservice.status.application.mapper;

import dev.animedia.contentservice.status.application.dto.StatusDto;
import dev.animedia.contentservice.status.application.dto.StatusTranslationDto;
import dev.animedia.contentservice.status.domain.model.Status;
import dev.animedia.contentservice.status.domain.model.StatusTranslation;

import java.util.stream.Collectors;

public class StatusApplicationMapper {
    public Status toStatus(StatusDto statusDto) {
        if (statusDto == null) return null;
        return new Status(
            statusDto.id(),
            statusDto.alias(),
            statusDto.sortOrder(),
            statusDto.active(),
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
            status.getActive(),
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
}
