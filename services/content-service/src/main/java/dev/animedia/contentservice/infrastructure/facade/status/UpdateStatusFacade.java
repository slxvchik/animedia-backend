package dev.animedia.contentservice.infrastructure.facade.status;

import dev.animedia.contentservice.application.status.dto.StatusDto;
import dev.animedia.contentservice.application.status.usecase.UpdateStatusUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateStatusFacade implements UpdateStatusUseCase {

	private final UpdateStatusUseCase updateStatusUseCase;

	@Autowired
	public UpdateStatusFacade(UpdateStatusUseCase updateStatusUseCase) {
		this.updateStatusUseCase = updateStatusUseCase;
	}

	@Transactional
	@Override
	public StatusDto update(StatusDto statusDto) {
		return updateStatusUseCase.update(statusDto);
	}
}
