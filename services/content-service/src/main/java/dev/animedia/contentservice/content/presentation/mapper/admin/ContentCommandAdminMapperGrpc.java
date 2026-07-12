package dev.animedia.contentservice.content.presentation.mapper.admin;

import dev.animedia.contentservice.content.application.dto.ContentDto;
import dev.animedia.contentservice.content.application.dto.ContentTranslationDto;
import dev.animedia.contentservice.genre.application.dto.GenreDto;
import dev.animedia.contentservice.status.application.dto.StatusDto;
import dev.animedia.contentservice.content.presentation.mapper.ContentTypeMapperGrpc;
import dev.animedia.contentservice.shared.presentation.grpc.mapper.DateMapper;
import dev.animedia.grpc.content.admin.v1.ContentAdminProtoApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
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

	public ContentDto toContentDto(
		ContentAdminProtoApi.CreateContentRequest request
	) {
		return new ContentDto(
			null,
			request.getAlias(),
			contentTypeMapperGrpc.toContentType(
				request.getType()
			),
			request.getSeason(),
			toStatusDto(UUID.fromString(request.getStatusId())),
			request.hasCoverImageId() ? request.getCoverImageId() : null,
			request.hasTrailerVideoId() ? request.getTrailerVideoId() : null,
			request.hasReleaseDate() ? dateMapper.toLocalDate(request.getReleaseDate()) : null,
			null,
			null,
			request.getActive(),
			request.getSortOrder(),
			request.getLanguageCodesCount() > 0 ? new HashSet<>(request.getLanguageCodesList()) : null,
			request.getGenreIdsCount() > 0 ? request.getGenreIdsList().stream().map(id -> toGenreDto(UUID.fromString(id))).collect(Collectors.toSet()) : null,
			request.getTranslationsCount() > 0 ? request.getTranslationsList().stream().map(this::toContentTranslationDto).collect(Collectors.toSet()) : null
		);
	}

	public ContentTranslationDto toContentTranslationDto(
		ContentAdminProtoApi.CreateContentTranslationRequest request
	) {
		return new ContentTranslationDto(
			null,
			request.getLanguageCode(),
			request.getTitle(),
			request.hasDescription() ? request.getDescription() : null
		);
	}

	public ContentDto toContentDto(
		ContentAdminProtoApi.UpdateContentRequest request
	) {
		return new ContentDto(
			UUID.fromString(request.getId()),
			null,
			null,
			null,
			toStatusDto(UUID.fromString(request.getStatusId())),
			request.hasCoverImageId() ? request.getCoverImageId() : null,
			request.hasTrailerVideoId() ? request.getTrailerVideoId() : null,
			request.hasReleaseDate() ? dateMapper.toLocalDate(request.getReleaseDate()) : null,
			null,
			null,
			request.getActive(),
			request.getSortOrder(),
			request.getLanguageCodesCount() > 0 ? new HashSet<>(request.getLanguageCodesList()) : null,
			request.getGenreIdsCount() > 0 ? request.getGenreIdsList().stream().map(uuid -> toGenreDto(UUID.fromString(uuid))).collect(Collectors.toSet()) : null,
			request.getTranslationsCount() > 0 ? request.getTranslationsList().stream().map(this::toContentTranslationDto).collect(Collectors.toSet()) : null
		);
	}

	public ContentTranslationDto toContentTranslationDto(
		ContentAdminProtoApi.UpdateContentTranslationRequest request
	) {
		return new ContentTranslationDto(
			request.hasId() ? UUID.fromString(request.getId()) : null,
			request.getLanguageCode(),
			request.getTitle(),
			request.hasDescription() ? request.getDescription() : null
		);
	}

	private StatusDto toStatusDto(UUID statusId) {
		return new StatusDto(
			statusId,
			null,
			null,
			null,
			null
		);
	}

	private GenreDto toGenreDto(UUID genreId) {
		return new GenreDto(
			genreId,
			null,
			null,
			null,
			null
		);
	}
}
