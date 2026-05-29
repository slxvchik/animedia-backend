package dev.animedia.contentservice.presentation.grpc.status.mapper;

import dev.animedia.contentservice.application.status.dto.StatusDto;
import dev.animedia.contentservice.application.status.dto.StatusSearchDto;
import dev.animedia.contentservice.application.status.dto.StatusTranslationDto;
import dev.animedia.grpc.common.CommonProto.PaginationResponse;
import dev.animedia.grpc.status.PrivateContentStatusProto.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PrivateStatusGrpcMapper {
	public StatusSearchDto toPrivateStatusSearchDto(
		PrivateSearchStatusRequest request
	) {
		if (request == null) return new StatusSearchDto(null, null, null, null);
		return new StatusSearchDto(
			request.hasActive() ? request.getActive() : null,
			request.hasAlias() ? request.getAlias() : null,
			request.hasName() ? request.getName() : null,
			request.hasLanguageCode() ? request.getLanguageCode() : null
		);
	}

	public StatusDto toStatusDto(
		CreateStatusRequest request
	) {
		if (request == null) return null;

		Set<StatusTranslationDto> translations = request.getTranslationsList()
			.stream()
			.map(this::toStatusTranslationDto)
			.filter(Objects::nonNull)
			.collect(Collectors.toSet());

		return new StatusDto(
			null,
			request.getAlias(),
			request.getSortOrder(),
			request.getActive(),
			translations
		);
	}

	private StatusTranslationDto toStatusTranslationDto(
		CreateStatusTranslationRequest request
	) {
		if (request == null) return null;

		return new StatusTranslationDto(
			null,
			request.getLanguageCode(),
			request.getName()
		);
	}

	public StatusDto toStatusDto(
		UpdateStatusRequest request
	) {
		if (request == null) return null;

		Set<StatusTranslationDto> translations = request.getTranslationsList()
			.stream()
			.map(this::toStatusTranslationDto)
			.filter(Objects::nonNull)
			.collect(Collectors.toSet());

		return new StatusDto(
			UUID.fromString(request.getUuid()),
			null,
			request.getSortOrder(),
			request.getActive(),
			translations
		);
	}

	private StatusTranslationDto toStatusTranslationDto(
		UpdateStatusTranslationRequest request
	) {
		if (request == null) return null;

		return new StatusTranslationDto(
			request.hasUuid() ? UUID.fromString(request.getUuid()) : null,
			request.getLanguageCode(),
			request.getName()
		);
	}

	public PrivateSearchStatusResponse toPrivateSearchStatusResponse(
		List<PrivateStatusResponse> statusResponseList,
		PaginationResponse paginationResponse
	) {
		return PrivateSearchStatusResponse
			.newBuilder()
			.addAllStatuses(statusResponseList)
			.setPagination(paginationResponse)
			.build();
	}

	public PrivateStatusResponse toPrivateStatusResponse(
		StatusDto statusDto
	) {
		if (statusDto == null) return null;

		List<PrivateStatusTranslationResponse> translations =
			statusDto.translationSet() != null
			? statusDto.translationSet()
				.stream()
				.map(std -> toPrivateStatusTranslationResponse(std, statusDto.id()))
				.filter(Objects::nonNull)
				.toList()
			: List.of();

		return PrivateStatusResponse
			.newBuilder()
			.setUuid(String.valueOf(statusDto.id()))
			.setAlias(statusDto.alias())
			.setActive(statusDto.active())
			.setSortOrder(statusDto.sortOrder())
			.addAllTranslations(translations)
			.build();
	}

	private PrivateStatusTranslationResponse toPrivateStatusTranslationResponse(
		StatusTranslationDto statusTranslationDto,
		UUID statusId
	) {
		if (statusTranslationDto == null) return null;

		return PrivateStatusTranslationResponse
			.newBuilder()
			.setUuid(String.valueOf(statusTranslationDto.id()))
			.setContentStatusUuid(String.valueOf(statusId))
			.setLanguageCode(statusTranslationDto.languageCode())
			.setName(statusTranslationDto.name())
			.build();
	}
}
