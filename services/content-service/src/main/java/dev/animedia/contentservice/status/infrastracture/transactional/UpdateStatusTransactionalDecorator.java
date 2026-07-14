package dev.animedia.contentservice.status.infrastracture.transactional;

import dev.animedia.contentservice.status.application.dto.request.UpdateStatusDto;
import dev.animedia.contentservice.status.application.usecase.admin.UpdateStatusUseCase;
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
	public void update(UpdateStatusDto statusDto) {
		updateStatusUseCase.update(statusDto);
	}
}
