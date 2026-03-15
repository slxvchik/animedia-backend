package dev.animedia.contentservice.status.mapper;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import dev.animedia.contentservice.status.dto.request.ContentStatusRequestDto;
import dev.animedia.contentservice.status.dto.request.CreateContentStatusTranslationRequestDto;
import dev.animedia.contentservice.status.dto.request.UpdateContentStatusTranslationRequestDto;
import dev.animedia.contentservice.status.dto.response.ContentStatusResponseDto;
import dev.animedia.contentservice.status.dto.response.ContentStatusTranslationResponseDto;
import dev.animedia.contentservice.status.dto.response.ContentStatusWithTranslationResponseDto;
import dev.animedia.contentservice.status.dto.response.ContentStatusWithTranslationListResponseDto;
import dev.animedia.grpc.common.CommonProto;
import dev.animedia.grpc.status.ContentStatusCommonProto;
import dev.animedia.grpc.status.PrivateContentStatusProto;
import dev.animedia.grpc.status.PublicContentStatusProto;
import dev.animedia.grpc.status.PrivateContentStatusTranslationProto;

@Component
public class GrpcContentStatusMapper {

	public PrivateContentStatusProto.PrivateSearchResponse toPrivateSearchResponse(Page<ContentStatusWithTranslationListResponseDto> contentStatuses, CommonProto.PaginationResponse paginationResponse) {

		List<ContentStatusCommonProto.ContentStatusWithTranslationsResponse> protoContentStatuses = contentStatuses.stream()
			.map(contentStatus -> {

				var protoContentStatus = ContentStatusCommonProto.ContentStatusResponse.newBuilder()
					.setId(contentStatus.id())
					.setAlias(contentStatus.alias())
					.build();

				var translations = contentStatus.translations().stream()
					.map(this::toProtoContentStatusTranslation).toList();

				return ContentStatusCommonProto.ContentStatusWithTranslationsResponse.newBuilder()
					.setContentStatus(protoContentStatus)
					.addAllTranslations(translations)
					.build();
			}).toList();

		return PrivateContentStatusProto.PrivateSearchResponse.newBuilder()
			.addAllContentStatuses(protoContentStatuses)
			.setPagination(paginationResponse)
			.build();
	}

	public ContentStatusCommonProto.ContentStatusResponse toProtoContentStatus(ContentStatusResponseDto contentStatusResponseDto) {
		return ContentStatusCommonProto.ContentStatusResponse.newBuilder()
			.setId(contentStatusResponseDto.id())
			.setAlias(contentStatusResponseDto.alias())
			.build();
	}

	public ContentStatusCommonProto.ContentStatusWithTranslationResponse toProtoContentStatusWithTranslation(ContentStatusWithTranslationResponseDto contentStatus) {
		var protoContentStatus = ContentStatusCommonProto.ContentStatusResponse.newBuilder()
			.setId(contentStatus.id())
			.setAlias(contentStatus.alias())
			.build();

		var protoTranslation = ContentStatusCommonProto.ContentStatusTranslationResponse.newBuilder()
			.setId(contentStatus.contentStatusTranslationId())
			.setContentStatusId(contentStatus.id())
			.setLanguageCode(contentStatus.languageCode())
			.setName(contentStatus.name())
			.build();

		return ContentStatusCommonProto.ContentStatusWithTranslationResponse.newBuilder()
			.setContentStatus(protoContentStatus)
			.setTranslation(protoTranslation)
			.build();
	}

	public ContentStatusCommonProto.ContentStatusWithTranslationsResponse toProtoContentStatusWithTranslations(ContentStatusWithTranslationListResponseDto contentStatus) {
		var protoContentStatus = ContentStatusCommonProto.ContentStatusResponse.newBuilder()
			.setId(contentStatus.id())
			.setAlias(contentStatus.alias())
			.build();

		var protoTranslations = contentStatus.translations().stream()
			.map(this::toProtoContentStatusTranslation).toList();

		return ContentStatusCommonProto.ContentStatusWithTranslationsResponse.newBuilder()
			.setContentStatus(protoContentStatus)
			.addAllTranslations(protoTranslations)
			.build();
	}

	public PublicContentStatusProto.PublicSearchResponse toPublicSearchResponse(
		Page<ContentStatusWithTranslationResponseDto> contentStatuses,
		CommonProto.PaginationResponse paginationResponse
	) {
		var protoContentStatuses = contentStatuses.stream()
			.map(this::toProtoContentStatusWithTranslation)
			.toList();

		return PublicContentStatusProto.PublicSearchResponse.newBuilder()
			.addAllStatuses(protoContentStatuses)
			.setPagination(paginationResponse)
			.build();
	}

	public ContentStatusCommonProto.ContentStatusTranslationResponse toProtoContentStatusTranslation(ContentStatusTranslationResponseDto contentStatusTranslation) {
		return ContentStatusCommonProto.ContentStatusTranslationResponse.newBuilder()
			.setId(contentStatusTranslation.id())
			.setContentStatusId(contentStatusTranslation.contentStatusId())
			.setLanguageCode(contentStatusTranslation.languageCode())
			.setName(contentStatusTranslation.name())
			.build();
	}

	public CreateContentStatusTranslationRequestDto toCreateContentStatusRequestDto(PrivateContentStatusTranslationProto.PrivateTranslationCreateRequest request) {
		return new CreateContentStatusTranslationRequestDto(
			request.getContentStatusId(),
			request.getLanguageCode(),
			request.getName()
		);
	}

	public UpdateContentStatusTranslationRequestDto toUpdateContentStatusRequestDto(PrivateContentStatusTranslationProto.PrivateTranslationUpdateRequest request) {
		return new UpdateContentStatusTranslationRequestDto(
			request.getName()
		);
	}

	public ContentStatusRequestDto toContentStatusRequestDto(PrivateContentStatusProto.PrivateCreateRequest request) {
		return new ContentStatusRequestDto(request.getAlias());
	}

	public ContentStatusRequestDto toContentStatusRequestDto(PrivateContentStatusProto.PrivateUpdateRequest request) {
		return new ContentStatusRequestDto(request.getAlias());
	}
}
