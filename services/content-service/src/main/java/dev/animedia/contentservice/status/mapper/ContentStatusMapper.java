package dev.animedia.contentservice.status.mapper;

import dev.animedia.contentservice.status.dto.request.ContentStatusRequestDto;
import dev.animedia.contentservice.status.dto.response.ContentStatusResponseDto;
import dev.animedia.contentservice.status.dto.response.ContentStatusTranslationResponseDto;
import dev.animedia.contentservice.status.dto.response.ContentStatusWithTranslationResponseDto;
import dev.animedia.contentservice.status.dto.response.ContentStatusWithTranslationsResponseDto;
import dev.animedia.contentservice.status.model.ContentStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ContentStatusMapper {

	public ContentStatus toContentStatus(ContentStatusRequestDto contentStatusRequestDto) {
		ContentStatus contentStatus = new ContentStatus();
		contentStatus.setAlias(contentStatusRequestDto.alias());
		return contentStatus;
	}

	public ContentStatusResponseDto toContentStatusResponseDto(ContentStatus contentStatus) {
		return new ContentStatusResponseDto(
			contentStatus.getId(),
			contentStatus.getAlias()
		);
	}

	public ContentStatusWithTranslationResponseDto toContentStatusWithTranslationResponseDto(ContentStatusResponseDto contentStatusResponseDto, ContentStatusTranslationResponseDto contentStatusTranslationResponseDto) {
		return contentStatusTranslationResponseDto == null ?
			new ContentStatusWithTranslationResponseDto(
				contentStatusResponseDto.id(),
				contentStatusResponseDto.alias(),
				null,
				null,
				null
			) : new ContentStatusWithTranslationResponseDto(
				contentStatusResponseDto.id(),
				contentStatusResponseDto.alias(),
				contentStatusTranslationResponseDto.id(),
				contentStatusTranslationResponseDto.languageCode(),
				contentStatusTranslationResponseDto.name()
			);
	}

	public List<ContentStatusWithTranslationsResponseDto> toContentStatusesWithTranslations(List<ContentStatusWithTranslationResponseDto> contentStatusesWithTranslation) {
		// ContentStatusId, translations
		Map<Long, List<ContentStatusTranslationResponseDto>> contentStatusTranslations = new HashMap<>();
		Map<Long, ContentStatusResponseDto> contentStatuses = new HashMap<>();

		for (var cswt : contentStatusesWithTranslation) {

			Long statusId = cswt.id();
			var translation = new ContentStatusTranslationResponseDto(cswt.contentStatusTranslationId(), statusId, cswt.languageCode(), cswt.name());

			contentStatusTranslations.computeIfAbsent(statusId, k -> new ArrayList<>())
				.add(translation);

			contentStatuses.computeIfAbsent(statusId, k ->
				new ContentStatusResponseDto(statusId, cswt.alias())
			);
		}

		return contentStatuses.values().stream()
			.map(status -> new ContentStatusWithTranslationsResponseDto(
				status.id(),
				status.alias(),
				contentStatusTranslations.get(status.id())
			))
			.toList();
	}
}
