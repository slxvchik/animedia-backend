package dev.animedia.contentservice.content.mapper;

import dev.animedia.contentservice.content.dto.request.ContentTranslationRequestDto;
import dev.animedia.contentservice.content.dto.response.ContentTranslationResponseDto;
import dev.animedia.contentservice.content.model.Content;
import dev.animedia.contentservice.content.model.ContentTranslation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

@Component
public class ContentTranslationMapper {

	@PersistenceContext
	private EntityManager entityManager;

	public ContentTranslationResponseDto toContentTranslationResponseDto(ContentTranslation contentTranslation) {
		return new ContentTranslationResponseDto(
			contentTranslation.getUuid().toString(),
			contentTranslation.getContent().getUuid().toString(),
			contentTranslation.getLanguageCode(),
			contentTranslation.getTitle(),
			contentTranslation.getDescription()
		);
	}
	public ContentTranslation toContentTranslation(ContentTranslationRequestDto dto) {

		Content content = entityManager.getReference(Content.class, dto.contentUuid());

		ContentTranslation contentTranslation = new ContentTranslation();

		contentTranslation.setContent(content);
		contentTranslation.setLanguageCode(dto.languageCode());
		contentTranslation.setTitle(dto.title());
		contentTranslation.setDescription(dto.description());

		return contentTranslation;
	}
}
