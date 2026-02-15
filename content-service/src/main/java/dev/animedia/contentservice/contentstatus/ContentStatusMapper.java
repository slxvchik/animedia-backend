package dev.animedia.contentservice.contentstatus;

import dev.animedia.contentservice.contentstatus.dto.request.ContentStatusRequestDto;
import dev.animedia.contentservice.contentstatus.dto.request.CreateContentStatusTranslationRequestDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusResponseDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusWithTranslationResponseDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusWithTranslationsResponseDto;
import dev.animedia.contentservice.contentstatus.model.ContentStatus;
import dev.animedia.contentservice.contentstatus.model.ContentStatusTranslation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

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
}
