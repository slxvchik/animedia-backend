package dev.animedia.contentservice.presentation.grpc.status.mapper;

import dev.animedia.contentservice.application.status.dto.StatusDto;
import dev.animedia.contentservice.application.status.dto.StatusSearchDto;
import dev.animedia.contentservice.application.status.dto.StatusTranslationDto;
import dev.animedia.grpc.common.CommonProto.PaginationResponse;
import dev.animedia.grpc.status.PrivateContentStatusProto.*;
import dev.animedia.grpc.status.admin.v1.StatusAdminProto;
import dev.animedia.grpc.status.admin.v1.StatusAdminProtoApi;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class StatusAdminMapperGrpc {

	public StatusDto toStatusDto(
		StatusAdminProtoApi.CreateStatusRequest request
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
		StatusAdminProtoApi.CreateStatusTranslationRequest request
	) {
		if (request == null) return null;

		return new StatusTranslationDto(
			null,
			request.getLanguageCode(),
			request.getName()
		);
	}

	public StatusDto toStatusDto(
		StatusAdminProtoApi.UpdateStatusRequest request
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
		StatusAdminProtoApi.UpdateStatusTranslationRequest request
	) {
		if (request == null) return null;

		return new StatusTranslationDto(
			request.hasUuid() ? UUID.fromString(request.getUuid()) : null,
			request.getLanguageCode(),
			request.getName()
		);
	}

	public StatusAdminProto.StatusResponse toPrivateStatusResponse(
		StatusDto statusDto
	) {
		if (statusDto == null) return null;

		List<StatusAdminProto.StatusTranslationResponse> translations =
			statusDto.translationSet() != null
			? statusDto.translationSet()
				.stream()
				.map(std -> toPrivateStatusTranslationResponse(std, statusDto.id()))
				.filter(Objects::nonNull)
				.toList()
			: List.of();

		return StatusAdminProto.StatusResponse
			.newBuilder()
			.setUuid(String.valueOf(statusDto.id()))
			.setAlias(statusDto.alias())
			.setActive(statusDto.active())
			.setSortOrder(statusDto.sortOrder())
			.addAllTranslations(translations)
			.build();
	}

	private StatusAdminProto.StatusTranslationResponse toPrivateStatusTranslationResponse(
		StatusTranslationDto statusTranslationDto,
		UUID statusId
	) {
		if (statusTranslationDto == null) return null;

		return StatusAdminProto.StatusTranslationResponse
			.newBuilder()
			.setUuid(String.valueOf(statusTranslationDto.id()))
			.setContentStatusUuid(String.valueOf(statusId))
			.setLanguageCode(statusTranslationDto.languageCode())
			.setName(statusTranslationDto.name())
			.build();
	}
}
