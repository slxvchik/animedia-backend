package dev.animedia.contentservice.content.infrastracture.transactional;

import dev.animedia.contentservice.content.application.dto.ContentDto;
import dev.animedia.contentservice.content.application.usecase.admin.UpdateContentUseCase;
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
