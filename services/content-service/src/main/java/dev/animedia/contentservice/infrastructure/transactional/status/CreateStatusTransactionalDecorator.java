package dev.animedia.contentservice.infrastructure.transactional.status;

import dev.animedia.contentservice.application.status.dto.StatusDto;
import dev.animedia.contentservice.application.status.usecase.CreateStatusUseCase;
import org.springframework.transaction.annotation.Transactional;

public class CreateStatusTransactionalDecorator implements CreateStatusUseCase {
	private final CreateStatusUseCase createStatusUseCase;

	public CreateStatusTransactionalDecorator(CreateStatusUseCase createStatusUseCase) {
		this.createStatusUseCase = createStatusUseCase;
	}

	@Transactional
	@Override
	public StatusDto create(StatusDto statusDto) {
		return createStatusUseCase.create(statusDto);
	}
}
