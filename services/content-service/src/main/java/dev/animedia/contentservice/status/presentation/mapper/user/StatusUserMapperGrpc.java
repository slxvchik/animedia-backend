package dev.animedia.contentservice.status.presentation.mapper.user;

import dev.animedia.contentservice.status.application.dto.StatusDto;
import dev.animedia.contentservice.status.application.dto.StatusTranslationDto;
import dev.animedia.grpc.status.user.v1.StatusUserProto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class StatusUserMapperGrpc {
	public StatusUserProto.StatusResponse toStatusGrpcResponse(
		StatusDto statusDto
	) {
		if (statusDto == null) return null;

		List<StatusUserProto.StatusTranslationResponse> translations =
			statusDto.translationSet() != null
			? statusDto.translationSet()
				.stream()
				.map(this::toStatusTranslationGrpcResponse)
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

	private StatusUserProto.StatusTranslationResponse toStatusTranslationGrpcResponse(
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
