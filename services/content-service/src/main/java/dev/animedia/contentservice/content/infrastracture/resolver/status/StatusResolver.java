package dev.animedia.contentservice.content.infrastracture.resolver.status;

import dev.animedia.contentservice.content.application.dto.status.StatusDto;
import dev.animedia.contentservice.content.application.resolver.StatusResolverInterface;
import dev.animedia.contentservice.status.application.usecase.GetStatusListUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StatusResolver implements StatusResolverInterface {
	private final GetStatusListUseCase getStatusListUseCase;
	private final StatusResolverMapper statusResolverMapper;

	@Autowired
	public StatusResolver(GetStatusListUseCase getStatusListUseCase, StatusResolverMapper statusResolverMapper) {
		this.getStatusListUseCase = getStatusListUseCase;
		this.statusResolverMapper = statusResolverMapper;
	}

	@Override
	public List<StatusDto> resolve(Set<String> statusIds) {
		if (statusIds == null || statusIds.isEmpty()) {
			return List.of();
		}

		Set<UUID> requestedStatusUuidSet = statusIds.stream().map(UUID::fromString).collect(Collectors.toSet());

		List<StatusDto> statusDtoList = getStatusListUseCase.get(
			List.copyOf(requestedStatusUuidSet),
			null
		).stream().map(statusResolverMapper::toContentStatusDto).toList();

		if (statusDtoList.size() != requestedStatusUuidSet.size()) {
			Set<UUID> foundStatusIds = statusDtoList.stream()
				.map(StatusDto::id)
				.collect(Collectors.toSet());

			List<UUID> notFoundIds = requestedStatusUuidSet.stream()
				.filter(id -> !foundStatusIds.contains(id))
				.toList();

			throw new StatusNotFoundException(notFoundIds);
		}

		return statusDtoList;
	}
}
