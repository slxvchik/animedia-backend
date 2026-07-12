package dev.animedia.contentservice.content.infrastracture.transactional;

import dev.animedia.contentservice.content.application.dto.ContentDto;
import dev.animedia.contentservice.content.application.usecase.admin.CreateContentUseCase;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class CreateContentTransactionalDecorator implements CreateContentUseCase {
	private final CreateContentUseCase createContentUseCase;

	public CreateContentTransactionalDecorator(CreateContentUseCase createContentUseCase) {
		this.createContentUseCase = createContentUseCase;
	}

	@Transactional
	@Override
	public UUID create(ContentDto contentDto) {
		return createContentUseCase.create(contentDto);
	}
}
