package dev.animedia.contentservice.status.presentation.mapper.user;

import dev.animedia.contentservice.status.application.dto.response.StatusDto;
import dev.animedia.contentservice.status.application.dto.response.StatusTranslationDto;
import dev.animedia.grpc.status.user.v1.StatusUserProto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class StatusUserMapperGrpc {
	public StatusUserProto.StatusResponse toStatusResponseGrpc(
		StatusDto statusDto
	) {
		if (statusDto == null) return null;

		List<StatusUserProto.StatusTranslationResponse> translations =
			statusDto.translations() != null
			? statusDto.translations()
				.stream()
				.map(this::toStatusTranslationResponseGrpc)
				.filter(Objects::nonNull)
				.toList()
			: List.of();

		return StatusUserProto.StatusResponse
			.newBuilder()
			.setAlias(statusDto.alias())
			.setSortOrder(statusDto.sortOrder())
			.addAllTranslations(translations)
			.build();
	}

	public StatusUserProto.StatusTranslationResponse toStatusTranslationResponseGrpc(
		StatusTranslationDto statusTranslationDto
	) {
		if (statusTranslationDto == null) return null;

		return StatusUserProto.StatusTranslationResponse
			.newBuilder()
			.setLanguageCode(statusTranslationDto.languageCode())
			.setName(statusTranslationDto.name())
			.build();
	}
}
