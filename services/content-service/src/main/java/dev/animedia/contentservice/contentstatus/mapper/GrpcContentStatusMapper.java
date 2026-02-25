package dev.animedia.contentservice.contentstatus.mapper;

import dev.animedia.contentservice.contentstatus.dto.request.ContentStatusRequestDto;
import dev.animedia.contentservice.contentstatus.dto.request.CreateContentStatusTranslationRequestDto;
import dev.animedia.contentservice.contentstatus.dto.request.UpdateContentStatusTranslationRequestDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusResponseDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusTranslationResponseDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusWithTranslationResponseDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusWithTranslationsResponseDto;
import dev.animedia.grpc.common.CommonProto;
import dev.animedia.grpc.content.ContentStatusCommonProto;
import dev.animedia.grpc.content.ContentStatusPrivateProto;
import dev.animedia.grpc.content.ContentStatusPublicProto;
import dev.animedia.grpc.content.ContentStatusTranslationPrivateProto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GrpcContentStatusMapper {

	public ContentStatusPrivateProto.SearchResponse toPrivateSearchResponse(Page<ContentStatusWithTranslationsResponseDto> contentStatuses, CommonProto.PaginationResponse paginationResponse) {

		List<ContentStatusPrivateProto.ContentStatusWithTranslations> protoContentStatuses = contentStatuses.stream()
			.map(contentStatus -> {

				var protoContentStatus = ContentStatusCommonProto.ContentStatusResponse.newBuilder()
					.setId(contentStatus.id())
					.setAlias(contentStatus.alias())
					.build();

				var translations = contentStatus.translations().stream()
					.map(translation ->
						ContentStatusCommonProto.ContentStatusTranslationResponse.newBuilder()
						.setId(translation.id())
						.setContentStatusId(translation.contentStatusId())
						.setLanguageCode(translation.languageCode())
						.setName(translation.name())
						.build()
					).toList();

				return ContentStatusPrivateProto.ContentStatusWithTranslations.newBuilder()
					.setContentStatus(protoContentStatus)
					.addAllTranslations(translations)
					.build();
			}).toList();

		return ContentStatusPrivateProto.SearchResponse.newBuilder()
			.addAllContentStatuses(protoContentStatuses)
			.setPagination(paginationResponse)
			.build();
	}

	public ContentStatusRequestDto toContentStatusRequestDto(ContentStatusPrivateProto.CreateRequest request) {
		return new ContentStatusRequestDto(request.getAlias());
	}

	public ContentStatusRequestDto toContentStatusRequestDto(ContentStatusPrivateProto.UpdateRequest request) {
		return new ContentStatusRequestDto(request.getAlias());
	}

	public ContentStatusCommonProto.ContentStatusResponse toProtoContentStatus(ContentStatusResponseDto contentStatusResponseDto) {
		return ContentStatusCommonProto.ContentStatusResponse.newBuilder()
			.setId(contentStatusResponseDto.id())
			.setAlias(contentStatusResponseDto.alias())
			.build();
	}

	public ContentStatusPublicProto.SearchResponse toPublicSearchResponse(
		Page<ContentStatusWithTranslationResponseDto> contentStatuses,
		CommonProto.PaginationResponse paginationResponse
	) {
		var protoContentStatuses = contentStatuses.stream()
			.map(contentStatus -> {

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

				return ContentStatusPublicProto.ContentStatusWithTranslation.newBuilder()
					.setContentStatus(protoContentStatus)
					.setTranslation(protoTranslation)
					.build();
			})
			.toList();

		return ContentStatusPublicProto.SearchResponse.newBuilder()
			.addAllContentStatuses(protoContentStatuses)
			.setPagination(paginationResponse)
			.build();
	}

	public CreateContentStatusTranslationRequestDto toCreateContentStatusRequestDto(ContentStatusTranslationPrivateProto.CreateRequest request) {
		return new CreateContentStatusTranslationRequestDto(
			request.getContentStatusId(),
			request.getLanguageCode(),
			request.getName()
		);
	}

	public UpdateContentStatusTranslationRequestDto toUpdateContentStatusRequestDto(ContentStatusTranslationPrivateProto.UpdateRequest request) {
		return new UpdateContentStatusTranslationRequestDto(
			request.getName()
		);
	}

	public ContentStatusCommonProto.ContentStatusTranslationResponse toProtoContentStatusTranslation(ContentStatusTranslationResponseDto contentStatusTranslation) {
		return ContentStatusCommonProto.ContentStatusTranslationResponse.newBuilder()
			.setId(contentStatusTranslation.id())
			.setContentStatusId(contentStatusTranslation.contentStatusId())
			.setLanguageCode(contentStatusTranslation.languageCode())
			.setName(contentStatusTranslation.name())
			.build();
	}
}
