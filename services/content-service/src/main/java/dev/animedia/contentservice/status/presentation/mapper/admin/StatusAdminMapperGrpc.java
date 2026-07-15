package dev.animedia.contentservice.status.presentation.mapper.admin;

import dev.animedia.contentservice.status.application.dto.request.CreateStatusDto;
import dev.animedia.contentservice.status.application.dto.request.CreateStatusTranslationDto;
import dev.animedia.contentservice.status.application.dto.request.UpdateStatusDto;
import dev.animedia.contentservice.status.application.dto.request.UpdateStatusTranslationDto;
import dev.animedia.contentservice.status.application.dto.response.StatusDto;
import dev.animedia.contentservice.status.application.dto.response.StatusTranslationDto;
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

	public CreateStatusDto toStatusDto(
		StatusAdminProtoApi.CreateStatusRequest request
	) {
		if (request == null) return null;

		Set<CreateStatusTranslationDto> translations = request.getTranslationsList()
			.stream()
			.map(this::toStatusTranslationDto)
			.filter(Objects::nonNull)
			.collect(Collectors.toSet());

		return new CreateStatusDto(
			request.getAlias(),
			request.getSortOrder(),
			request.getActive(),
			translations
		);
	}

	public CreateStatusTranslationDto toStatusTranslationDto(
		StatusAdminProtoApi.CreateStatusTranslationRequest request
	) {
		if (request == null) return null;

		return new CreateStatusTranslationDto(
			request.getLanguageCode(),
			request.getName()
		);
	}

	public UpdateStatusDto toStatusDto(
		StatusAdminProtoApi.UpdateStatusRequest request
	) {
		if (request == null) return null;

		Set<UpdateStatusTranslationDto> translations = request.getTranslationsList()
			.stream()
			.map(this::toStatusTranslationDto)
			.filter(Objects::nonNull)
			.collect(Collectors.toSet());

		return new UpdateStatusDto(
			UUID.fromString(request.getId()),
			request.getSortOrder(),
			request.getActive(),
			translations
		);
	}

	public UpdateStatusTranslationDto toStatusTranslationDto(
		StatusAdminProtoApi.UpdateStatusTranslationRequest request
	) {
		if (request == null) return null;

		return new UpdateStatusTranslationDto(
			request.hasId() ? UUID.fromString(request.getId()) : null,
			request.getLanguageCode(),
			request.getName()
		);
	}

	public StatusAdminProto.StatusResponse toStatusResponseGrpc(
		StatusDto statusDto
	) {
		if (statusDto == null) return null;

		List<StatusAdminProto.StatusTranslationResponse> translations =
			statusDto.translations() != null
			? statusDto.translations()
				.stream()
				.map(std -> toStatusTranslationResponseGrpc(std, statusDto.id()))
				.filter(Objects::nonNull)
				.toList()
			: List.of();

		return StatusAdminProto.StatusResponse
			.newBuilder()
			.setId(String.valueOf(statusDto.id()))
			.setAlias(statusDto.alias())
			.setActive(statusDto.active())
			.setSortOrder(statusDto.sortOrder())
			.addAllTranslations(translations)
			.build();
	}

	public StatusAdminProto.StatusTranslationResponse toStatusTranslationResponseGrpc(
		StatusTranslationDto statusTranslationDto,
		UUID statusId
	) {
		if (statusTranslationDto == null) return null;

		return StatusAdminProto.StatusTranslationResponse
			.newBuilder()
			.setId(String.valueOf(statusTranslationDto.id()))
			.setStatusId(String.valueOf(statusId))
			.setLanguageCode(statusTranslationDto.languageCode())
			.setName(statusTranslationDto.name())
			.build();
	}
}
