package dev.animedia.contentservice.application.content.resolver;

import dev.animedia.contentservice.application.status.exception.StatusNotFoundException;
import dev.animedia.contentservice.domain.status.model.Status;
import dev.animedia.contentservice.domain.status.repository.StatusQueryRepository;

import java.util.UUID;

public class StatusDomainResolver {
	private final StatusQueryRepository statusQueryRepository;

	public StatusDomainResolver(StatusQueryRepository statusQueryRepository) {
		this.statusQueryRepository = statusQueryRepository;
	}

	public Status resolve(UUID statusId) {
		return statusQueryRepository.findById(statusId, null, null)
			.orElseThrow(() -> new StatusNotFoundException(statusId));
	}
}
