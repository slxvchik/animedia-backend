package dev.animedia.contentservice.content.presentation.mapper.admin.request;

import dev.animedia.contentservice.content.application.dto.content.request.CreateContentDto;
import dev.animedia.contentservice.content.application.dto.content.request.CreateContentTranslationDto;
import dev.animedia.contentservice.content.application.dto.content.request.UpdateContentDto;
import dev.animedia.contentservice.content.application.dto.content.request.UpdateContentTranslationDto;
import dev.animedia.contentservice.content.presentation.mapper.ContentTypeMapperGrpc;
import dev.animedia.contentservice.shared.presentation.grpc.mapper.DateMapper;
import dev.animedia.grpc.content.admin.v1.ContentAdminProtoApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ContentCommandAdminMapperGrpc {
	private final ContentTypeMapperGrpc contentTypeMapperGrpc;
	private final DateMapper dateMapper;

	@Autowired
	public ContentCommandAdminMapperGrpc(ContentTypeMapperGrpc contentTypeMapperGrpc, DateMapper dateMapper) {
		this.contentTypeMapperGrpc = contentTypeMapperGrpc;
		this.dateMapper = dateMapper;
	}

	public CreateContentDto toContentRequestDto(
		ContentAdminProtoApi.CreateContentRequest request
	) {
		return new CreateContentDto(
			request.getAlias(),
			contentTypeMapperGrpc.toContentType(
				request.getType()
			),
			request.getSeason(),
			request.getStatusId(),
			request.hasCoverImageId() ? request.getCoverImageId() : null,
			request.hasTrailerVideoId() ? request.getTrailerVideoId() : null,
			request.hasReleaseDate() ? dateMapper.toLocalDate(request.getReleaseDate()) : null,
			request.getActive(),
			request.getSortOrder(),
			request.getLanguageCodesCount() > 0 ? new HashSet<>(request.getLanguageCodesList()) : null,
			request.getGenreIdsCount() > 0 ? Set.copyOf(request.getGenreIdsList()) : null,
			request.getTranslationsCount() > 0 ? request.getTranslationsList().stream().map(this::toContentTranslationDto).collect(Collectors.toSet()) : null
		);
	}

	public CreateContentTranslationDto toContentTranslationDto(
		ContentAdminProtoApi.CreateContentTranslationRequest request
	) {
		return new CreateContentTranslationDto(
			request.getLanguageCode(),
			request.getTitle(),
			request.hasDescription() ? request.getDescription() : null
		);
	}

	public UpdateContentDto toContentRequestDto(
		ContentAdminProtoApi.UpdateContentRequest request
	) {
		return new UpdateContentDto(
			UUID.fromString(request.getId()),
			request.getStatusId(),
			request.hasCoverImageId() ? request.getCoverImageId() : null,
			request.hasTrailerVideoId() ? request.getTrailerVideoId() : null,
			request.hasReleaseDate() ? dateMapper.toLocalDate(request.getReleaseDate()) : null,
			request.getActive(),
			request.getSortOrder(),
			request.getLanguageCodesCount() > 0 ? new HashSet<>(request.getLanguageCodesList()) : null,
			request.getGenreIdsCount() > 0 ? Set.copyOf(request.getGenreIdsList()) : null,
			request.getTranslationsCount() > 0 ? request.getTranslationsList().stream().map(this::toContentTranslationDto).collect(Collectors.toSet()) : null
		);
	}

	public UpdateContentTranslationDto toContentTranslationDto(
		ContentAdminProtoApi.UpdateContentTranslationRequest request
	) {
		return new UpdateContentTranslationDto(
			request.hasId() ? UUID.fromString(request.getId()) : null,
			request.getLanguageCode(),
			request.getTitle(),
			request.hasDescription() ? request.getDescription() : null
		);
	}
}
