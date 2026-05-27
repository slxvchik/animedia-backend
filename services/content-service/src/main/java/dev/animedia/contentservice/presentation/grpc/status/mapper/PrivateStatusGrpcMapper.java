package dev.animedia.contentservice.presentation.grpc.status.mapper;

import dev.animedia.contentservice.application.status.dto.StatusDto;
import dev.animedia.contentservice.application.status.dto.StatusSearchDto;
import dev.animedia.contentservice.application.status.dto.StatusTranslationDto;
import dev.animedia.grpc.status.PrivateContentStatusProto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PrivateStatusGrpcMapper {
	public StatusSearchDto toPrivateStatusSearchDto(
		PrivateContentStatusProto.PrivateSearchStatusRequest request
	) {
		return new StatusSearchDto(
			request.hasActive() ? request.getActive() : null,
			request.hasAlias() ? request.getAlias() : null,
			request.hasName() ? request.getName() : null,
			request.hasLanguageCode() ? request.getLanguageCode() : null
		);
	}

	public StatusDto toStatusDto(
		PrivateContentStatusProto.CreateStatusRequest request
	) {
		Set<StatusTranslationDto> translations = request.getTranslationsList()
			.stream()
			.map(this::toStatusTranslationDto)
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
		PrivateContentStatusProto.CreateStatusTranslationRequest request
	) {
		return new StatusTranslationDto(
			null,
			request.getLanguageCode(),
			request.getName()
		);
	}

	public StatusDto toStatusDto(
		PrivateContentStatusProto.UpdateStatusRequest request
	) {
		return new StatusDto(
			request.getId(),
			null,
			request.getSortOrder(),
			request.getActive()

		)
	}

	private StatusTranslationDto toStatusTranslationDto(
		PrivateContentStatusProto.UpdateStatusTranslationRequest request
	) {
		return new StatusTranslationDto(
			request.getId(),
			request.getLanguageCode(),
			request.getName()
		);
	}

	public PrivateContentStatusProto.PrivateStatusResponse toPrivateStatusResponse(
		StatusDto statusDto
	) {
		List<PrivateContentStatusProto.PrivateStatusTranslationResponse> translations =
			statusDto.translationSet() != null
			? statusDto.translationSet()
				.stream()
				.map(std -> toPrivateStatusTranslationResponse(std, statusDto.id()))
				.toList()
			: List.of();

		return PrivateContentStatusProto.PrivateStatusResponse
			.newBuilder()
			.setId(statusDto.id())
			.setAlias(statusDto.alias())
			.setActive(statusDto.active())
			.setSortOrder(statusDto.sortOrder())
			.addAllTranslations(translations)
			.build();
	}

	private PrivateContentStatusProto.PrivateStatusTranslationResponse toPrivateStatusTranslationResponse(
		StatusTranslationDto statusTranslationDto,
		Long statusId
	) {
		return PrivateContentStatusProto.PrivateStatusTranslationResponse
			.newBuilder()
			.setId(statusTranslationDto.id())
			.setContentStatusId(statusId)
			.setLanguageCode(statusTranslationDto.languageCode())
			.setName(statusTranslationDto.name())
			.build();
	}
}
