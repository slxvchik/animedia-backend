package dev.animedia.contentservice.content.presentation.mapper.user.response;

import dev.animedia.contentservice.content.application.dto.status.StatusDto;
import dev.animedia.contentservice.content.application.dto.status.StatusTranslationDto;
import dev.animedia.grpc.status.user.v1.StatusUserProto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class StatusResponseUserMapperGrpc {
	public StatusUserProto.StatusResponse toStatusResponseGrpc(
		StatusDto statusDto
	) {
		if (statusDto == null) return null;

		List<StatusUserProto.StatusTranslationResponse> translations =
			statusDto.translationSet() != null
			? statusDto.translationSet()
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

	private StatusUserProto.StatusTranslationResponse toStatusTranslationResponseGrpc(
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
