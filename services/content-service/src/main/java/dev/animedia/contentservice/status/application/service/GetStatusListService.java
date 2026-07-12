package dev.animedia.contentservice.status.application.service;

import dev.animedia.contentservice.status.application.dto.StatusDto;
import dev.animedia.contentservice.status.application.mapper.StatusApplicationMapper;
import dev.animedia.contentservice.status.application.usecase.GetStatusListUseCase;
import dev.animedia.contentservice.status.domain.model.Status;
import dev.animedia.contentservice.status.domain.repository.StatusQueryRepository;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class GetStatusListService implements GetStatusListUseCase {
	private final StatusApplicationMapper statusApplicationMapper;
	private final StatusQueryRepository statusQueryRepository;

	public GetStatusListService(
		StatusApplicationMapper statusApplicationMapper,
		StatusQueryRepository statusQueryRepository
	) {
		this.statusApplicationMapper = statusApplicationMapper;
		this.statusQueryRepository = statusQueryRepository;
	}

	@Override
	public List<StatusDto> get(List<UUID> idList, @Nullable String languageCode) {
		List<UUID> distinctIdList = idList.stream().distinct().toList();
		List<Status> statusList = statusQueryRepository.findByIdList(distinctIdList, languageCode);
		return statusList
			.stream()
			.map(statusApplicationMapper::toStatusDto)
			.toList();
	}
}
