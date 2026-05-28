package dev.animedia.contentservice.presentation.grpc.content.mapper;

import dev.animedia.contentservice.application.content.dto.ContentSearchDto;
import dev.animedia.contentservice.presentation.grpc.shared.mapper.DateMapper;
import dev.animedia.grpc.common.CommonProto.PaginationResponse;
import dev.animedia.grpc.core.PrivateContentProto.PrivateContentResponse;
import dev.animedia.grpc.core.PrivateContentProto.PrivateSearchContentRequest;
import dev.animedia.grpc.core.PrivateContentProto.PrivateSearchContentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class PrivateContentQueryGrpcMapper {

	private final ContentTypeGrpcMapper contentTypeGrpcMapper;
	private final DateMapper dateMapper;

	@Autowired
	public PrivateContentQueryGrpcMapper(
		ContentTypeGrpcMapper contentTypeGrpcMapper,
		DateMapper dateMapper
	) {
		this.contentTypeGrpcMapper = contentTypeGrpcMapper;
		this.dateMapper = dateMapper;
	}

	public ContentSearchDto toContentSearchDto(
		PrivateSearchContentRequest request
	) {
		return new ContentSearchDto(
			request.hasUuid() ? UUID.fromString(request.getUuid()) : null,
			request.getAliasesCount() > 0 ? request.getAliasesList() : null,
			request.getTitlesCount() > 0 ? request.getTitlesList() : null,
			request.getTypesCount() > 0
				? request.getTypesList()
				.stream()
				.map(contentTypeGrpcMapper::toContentType)
				.toList()
				: null,
			request.getSeasonsCount() > 0 ? request.getSeasonsList() : null,
			request.getStatusIdsCount() > 0 ? request.getStatusIdsList() : null,
			request.hasReleaseDateFrom()
				? dateMapper.toLocalDate(request.getReleaseDateFrom())
				: null,
			request.hasReleaseDateTo()
				? dateMapper.toLocalDate(request.getReleaseDateTo())
				: null,
			request.hasCreatedAtFrom()
				? dateMapper.toLocalDateTime(request.getCreatedAtFrom())
				: null,
			request.hasCreatedAtTo()
				? dateMapper.toLocalDateTime(request.getCreatedAtTo())
				: null,
			request.hasUpdatedAtFrom()
				? dateMapper.toLocalDateTime(request.getUpdatedAtFrom())
				: null,
			request.hasUpdatedAtTo()
				? dateMapper.toLocalDateTime(request.getUpdatedAtTo())
				: null,
			request.hasActive() ? request.getActive() : null,
			request.getLanguageCodesCount() > 0
				? request.getLanguageCodesList()
				: null,
			request.getGenreIdsCount() > 0
				? request.getGenreIdsList()
				: null,
			null
		);
	}

	public PrivateSearchContentResponse toPrivateSearchContentResponse(
		List<PrivateContentResponse> contentResponseList,
		PaginationResponse paginationResponse
	) {
		return PrivateSearchContentResponse
			.newBuilder()
			.addAllContents(contentResponseList)
			.setPagination(paginationResponse)
			.build();
	}
}