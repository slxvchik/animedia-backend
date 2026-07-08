package dev.animedia.contentservice.infrastructure.transactional.status;

import dev.animedia.contentservice.application.status.dto.StatusDto;
import dev.animedia.contentservice.application.status.usecase.admin.UpdateStatusUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateStatusTransactionalDecorator implements UpdateStatusUseCase {

	private final UpdateStatusUseCase updateStatusUseCase;

	@Autowired
	public UpdateStatusTransactionalDecorator(UpdateStatusUseCase updateStatusUseCase) {
		this.updateStatusUseCase = updateStatusUseCase;
	}

	@Transactional
	@Override
	public void update(StatusDto statusDto) {
		updateStatusUseCase.update(statusDto);
	}
}
