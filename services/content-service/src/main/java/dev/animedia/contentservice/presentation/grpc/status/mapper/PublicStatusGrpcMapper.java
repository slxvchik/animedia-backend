package dev.animedia.contentservice.presentation.grpc.status.mapper;

import dev.animedia.contentservice.application.status.dto.StatusDto;
import dev.animedia.contentservice.application.status.dto.StatusSearchDto;
import dev.animedia.contentservice.application.status.dto.StatusTranslationDto;
import dev.animedia.grpc.status.PublicContentStatusProto.PublicSearchStatusRequest;
import dev.animedia.grpc.status.PublicContentStatusProto.PublicStatusResponse;
import dev.animedia.grpc.status.PublicContentStatusProto.PublicStatusTranslationResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class PublicStatusGrpcMapper {
	public StatusSearchDto toPublicStatusSearchDto(
		PublicSearchStatusRequest request,
		String languageCode
	) {
		if (request == null) return new StatusSearchDto(true, null, null, languageCode);
		return new StatusSearchDto(
			true,
			request.hasAlias() ? request.getAlias() : null,
			request.hasName() ? request.getName() : null,
			languageCode
		);
	}

	public PublicStatusResponse toPublicStatusResponse(
		StatusDto statusDto
	) {
		if (statusDto == null) return null;

		List<PublicStatusTranslationResponse> translationsProto =
			statusDto.translationSet() != null
			? statusDto.translationSet()
				.stream()
				.map(this::toPublicStatusTranslationResponse)
				.filter(Objects::nonNull)
				.toList()
			: List.of();

		return PublicStatusResponse.newBuilder()
			.setAlias(statusDto.alias())
			.addAllTranslations(translationsProto)
			.build();
	}

	private PublicStatusTranslationResponse toPublicStatusTranslationResponse(
		StatusTranslationDto statusTranslationDto
	) {
		if (statusTranslationDto == null) return null;

		return PublicStatusTranslationResponse.newBuilder()
			.setName(statusTranslationDto.name())
			.build();
	}
}