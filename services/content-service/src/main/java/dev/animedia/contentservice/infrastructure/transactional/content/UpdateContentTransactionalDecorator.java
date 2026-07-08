package dev.animedia.contentservice.infrastructure.transactional.content;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import dev.animedia.contentservice.application.content.usecase.admin.UpdateContentUseCase;
import org.springframework.transaction.annotation.Transactional;

public class UpdateContentTransactionalDecorator implements UpdateContentUseCase {
	private final UpdateContentUseCase updateContentUseCase;

	public UpdateContentTransactionalDecorator(UpdateContentUseCase updateContentUseCase) {
		this.updateContentUseCase = updateContentUseCase;
	}

	@Transactional
	@Override
	public void update(ContentDto contentDto) {
		updateContentUseCase.update(contentDto);
	}
}
