package dev.animedia.contentservice.infrastructure.transactional.content;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import dev.animedia.contentservice.application.content.usecase.admin.CreateContentUseCase;
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
