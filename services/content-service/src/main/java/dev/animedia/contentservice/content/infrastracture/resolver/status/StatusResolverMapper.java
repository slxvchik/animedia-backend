package dev.animedia.contentservice.content.infrastracture.resolver.status;

import dev.animedia.contentservice.content.application.dto.status.StatusDto;
import dev.animedia.contentservice.content.application.dto.status.StatusTranslationDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class StatusResolverMapper {
	public StatusDto toContentStatusDto(dev.animedia.contentservice.status.application.dto.StatusDto statusDto) {
		if (statusDto == null) return null;
		return new StatusDto(
			statusDto.id(),
			statusDto.alias(),
			statusDto.sortOrder(),
			statusDto.active(),
			statusDto.translationSet().stream().map(this::toContentStatusTranslationDto).collect(Collectors.toSet())
		);
	}

	public StatusTranslationDto toContentStatusTranslationDto(dev.animedia.contentservice.status.application.dto.StatusTranslationDto statusTranslationDto) {
		if (statusTranslationDto == null) return null;
		return new StatusTranslationDto(
			statusTranslationDto.id(),
			statusTranslationDto.languageCode(),
			statusTranslationDto.name()
		);
	}
}
