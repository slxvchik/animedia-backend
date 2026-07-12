package dev.animedia.contentservice.content.application.resolver;

import dev.animedia.contentservice.status.application.exception.StatusNotFoundException;
import dev.animedia.contentservice.status.domain.model.Status;
import dev.animedia.contentservice.status.domain.repository.StatusQueryRepository;

import java.util.UUID;

public class StatusDomainResolver {
	private final StatusQueryRepository statusQueryRepository;

	public StatusDomainResolver(StatusQueryRepository statusQueryRepository) {
		this.statusQueryRepository = statusQueryRepository;
	}

	public Status resolve(UUID statusId) {
		return statusQueryRepository.findById(statusId, null)
			.orElseThrow(() -> new StatusNotFoundException(statusId));
	}
}
