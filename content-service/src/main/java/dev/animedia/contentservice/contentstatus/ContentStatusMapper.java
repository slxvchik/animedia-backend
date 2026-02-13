package dev.animedia.contentservice.contentstatus;

import dev.animedia.contentservice.contentstatus.dto.request.ContentStatusRequestDto;
import dev.animedia.contentservice.contentstatus.dto.request.ContentStatusTranslationRequestDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusResponseDto;
import dev.animedia.contentservice.contentstatus.model.ContentStatus;
import dev.animedia.contentservice.contentstatus.model.ContentStatusTranslation;
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

	public ContentStatusTranslation toContentStatusTranslation(ContentStatusTranslationRequestDto contentStatusTranslationRequestDto) {
		ContentStatusTranslation contentStatusTranslation = new ContentStatusTranslation();
		contentStatusTranslation.setContentStatus(contentStatusTranslationRequestDto.contentStatusId());
		contentStatusTranslation.setLanguage(contentStatusTranslationRequestDto.languageCode());
		contentStatusTranslation.setName(contentStatusTranslationRequestDto.name());
		return contentStatusTranslation;
	}
}
