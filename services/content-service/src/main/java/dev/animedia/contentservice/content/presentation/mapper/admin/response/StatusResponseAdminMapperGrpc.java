package dev.animedia.contentservice.content.presentation.mapper.admin.response;

import dev.animedia.contentservice.content.application.dto.status.StatusDto;
import dev.animedia.contentservice.content.application.dto.status.StatusTranslationDto;
import dev.animedia.grpc.status.admin.v1.StatusAdminProto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
public class StatusResponseAdminMapperGrpc {
	public StatusAdminProto.StatusResponse toStatusResponseGrpc(
		StatusDto statusDto
	) {
		if (statusDto == null) return null;

		List<StatusAdminProto.StatusTranslationResponse> translations =
			statusDto.translationSet() != null
			? statusDto.translationSet()
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

	private StatusAdminProto.StatusTranslationResponse toStatusTranslationResponseGrpc(
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
