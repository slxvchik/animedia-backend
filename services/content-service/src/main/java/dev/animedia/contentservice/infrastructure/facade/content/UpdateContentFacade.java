package dev.animedia.contentservice.infrastructure.facade.content;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import dev.animedia.contentservice.application.content.usecase.UpdateContentUseCase;
import org.springframework.transaction.annotation.Transactional;

public class UpdateContentFacade implements UpdateContentUseCase {
	private final UpdateContentUseCase updateContentUseCase;

	public UpdateContentFacade(UpdateContentUseCase updateContentUseCase) {
		this.updateContentUseCase = updateContentUseCase;
	}

	@Transactional
	@Override
	public ContentDto update(ContentDto contentDto) {
		return updateContentUseCase.update(contentDto);
	}
}
