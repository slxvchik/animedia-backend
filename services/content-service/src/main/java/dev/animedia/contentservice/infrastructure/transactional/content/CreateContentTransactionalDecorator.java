package dev.animedia.contentservice.infrastructure.transactional.content;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import dev.animedia.contentservice.application.content.usecase.CreateContentUseCase;
import org.springframework.transaction.annotation.Transactional;

public class CreateContentTransactionalDecorator implements CreateContentUseCase {
	private final CreateContentUseCase createContentUseCase;

	public CreateContentTransactionalDecorator(CreateContentUseCase createContentUseCase) {
		this.createContentUseCase = createContentUseCase;
	}

	@Transactional
	@Override
	public ContentDto create(ContentDto contentDto) {
		return createContentUseCase.create(contentDto);
	}
}
