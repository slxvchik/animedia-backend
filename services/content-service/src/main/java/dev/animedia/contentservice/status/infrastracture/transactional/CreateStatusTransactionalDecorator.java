package dev.animedia.contentservice.status.infrastracture.transactional;

import dev.animedia.contentservice.status.application.dto.StatusDto;
import dev.animedia.contentservice.status.application.usecase.admin.CreateStatusUseCase;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class CreateStatusTransactionalDecorator implements CreateStatusUseCase {
	private final CreateStatusUseCase createStatusUseCase;

	public CreateStatusTransactionalDecorator(CreateStatusUseCase createStatusUseCase) {
		this.createStatusUseCase = createStatusUseCase;
	}

	@Transactional
	@Override
	public UUID create(StatusDto statusDto) {
		return createStatusUseCase.create(statusDto);
	}
}
