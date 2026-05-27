package dev.animedia.contentservice.presentation.grpc.status.mapper;

import dev.animedia.contentservice.application.status.dto.StatusDto;
import dev.animedia.contentservice.application.status.dto.StatusSearchDto;
import dev.animedia.contentservice.application.status.dto.StatusTranslationDto;
import dev.animedia.grpc.status.PublicContentStatusProto;
import dev.animedia.grpc.status.PublicContentStatusProto.PublicSearchStatusRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PublicStatusGrpcMapper {
	public StatusSearchDto toPublicStatusSearchDto(
		PublicSearchStatusRequest request,
		String languageCode
	) {
		return new StatusSearchDto(
			true,
			request.hasAlias() ? request.getAlias() : null,
			request.hasName() ? request.getName() : null,
			languageCode
		);
	}

	public PublicContentStatusProto.PublicStatusResponse toPublicStatusResponse(
		StatusDto statusDto
	) {
		List<PublicContentStatusProto.PublicStatusTranslationResponse> translationsProto = (statusDto.translationSet() != null)
			? statusDto.translationSet()
				.stream()
				.map(this::toPublicStatusTranslationResponse)
				.toList()
			: List.of();
		return PublicContentStatusProto.PublicStatusResponse.newBuilder()
			.setAlias(statusDto.alias())
			.addAllTranslations(translationsProto)
			.build();
	}

	public PublicContentStatusProto.PublicStatusTranslationResponse toPublicStatusTranslationResponse(
		StatusTranslationDto statusTranslationDto
	) {
		return PublicContentStatusProto.PublicStatusTranslationResponse.newBuilder()
			.setName(statusTranslationDto.name())
			.build();
	}
}