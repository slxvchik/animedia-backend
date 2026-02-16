package dev.animedia.contentservice.contentstatus.mapper;

import dev.animedia.contentservice.contentstatus.dto.request.ContentStatusRequestDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusResponseDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusTranslationResponseDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusWithTranslationResponseDto;
import dev.animedia.contentservice.contentstatus.model.ContentStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

@Component
public class ContentStatusMapper {

	@PersistenceContext
	private EntityManager entityManager;

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
}
