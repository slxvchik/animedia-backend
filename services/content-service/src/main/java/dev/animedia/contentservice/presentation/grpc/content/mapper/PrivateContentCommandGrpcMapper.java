package dev.animedia.contentservice.presentation.grpc.content.mapper;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import dev.animedia.contentservice.application.content.dto.ContentTranslationDto;
import dev.animedia.contentservice.application.genre.dto.GenreDto;
import dev.animedia.contentservice.application.status.dto.StatusDto;
import dev.animedia.contentservice.presentation.grpc.shared.mapper.DateMapper;
import dev.animedia.grpc.core.PrivateContentProto.CreateContentRequest;
import dev.animedia.grpc.core.PrivateContentProto.CreateContentTranslationRequest;
import dev.animedia.grpc.core.PrivateContentProto.UpdateContentRequest;
import dev.animedia.grpc.core.PrivateContentProto.UpdateContentTranslationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PrivateContentCommandGrpcMapper {

	private final ContentTypeGrpcMapper contentTypeGrpcMapper;
	private final DateMapper dateMapper;

	@Autowired
	public PrivateContentCommandGrpcMapper(ContentTypeGrpcMapper contentTypeGrpcMapper, DateMapper dateMapper) {
		this.contentTypeGrpcMapper = contentTypeGrpcMapper;
		this.dateMapper = dateMapper;
	}

	public ContentDto toContentDto(
		CreateContentRequest request
	) {
		return new ContentDto(
			null,
			request.getAlias(),
			contentTypeGrpcMapper.toContentType(
				request.getType()
			),
			request.getSeason(),
			toStatusDto(request.getStatusId()),
			request.hasCoverImageUuid() ? UUID.fromString(request.getCoverImageUuid()) : null,
			request.hasTrailerVideoUuid() ? UUID.fromString(request.getTrailerVideoUuid()) : null,
			request.hasReleaseDate() ? dateMapper.toLocalDate(request.getReleaseDate()) : null,
			null,
			null,
			request.getActive(),
			request.getSortOrder(),
			request.getLanguageCodesCount() > 0 ? new HashSet<>(request.getLanguageCodesList()) : null,
			request.getGenreIdsCount() > 0 ? request.getGenreIdsList().stream().map(this::toGenreDto).collect(Collectors.toSet()) : null,
			request.getTranslationsCount() > 0 ? request.getTranslationsList().stream().map(this::toContentTranslationDto).collect(Collectors.toSet()) : null
		);
	}

	public ContentTranslationDto toContentTranslationDto(
		CreateContentTranslationRequest request
	) {
		return new ContentTranslationDto(
			null,
			request.getLanguageCode(),
			request.getTitle(),
			request.hasDescription() ? request.getDescription() : null
		);
	}

	public ContentDto toContentDto(
		UpdateContentRequest request
	) {
		return new ContentDto(
			UUID.fromString(request.getUuid()),
			null,
			null,
			null,
			toStatusDto(request.getStatusId()),
			request.hasCoverImageUuid() ? UUID.fromString(request.getCoverImageUuid()) : null,
			request.hasTrailerVideoUuid() ? UUID.fromString(request.getTrailerVideoUuid()) : null,
			request.hasReleaseDate() ? dateMapper.toLocalDate(request.getReleaseDate()) : null,
			null,
			null,
			request.getActive(),
			request.getSortOrder(),
			request.getLanguageCodesCount() > 0 ? new HashSet<>(request.getLanguageCodesList()) : null,
			request.getGenreIdsCount() > 0 ? request.getGenreIdsList().stream().map(this::toGenreDto).collect(Collectors.toSet()) : null,
			request.getTranslationsCount() > 0 ? request.getTranslationsList().stream().map(this::toContentTranslationDto).collect(Collectors.toSet()) : null
		);
	}

	public ContentTranslationDto toContentTranslationDto(
		UpdateContentTranslationRequest request
	) {
		return new ContentTranslationDto(
			request.hasUuid() ? UUID.fromString(request.getUuid()) : null,
			request.getLanguageCode(),
			request.getTitle(),
			request.hasDescription() ? request.getDescription() : null
		);
	}

	private StatusDto toStatusDto(Long statusId) {
		return new StatusDto(
			statusId,
			null,
			null,
			null,
			null
		);
	}

	private GenreDto toGenreDto(Long genreId) {
		return new GenreDto(
			genreId,
			null,
			null,
			null,
			null
		);
	}
}
