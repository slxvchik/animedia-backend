package dev.animedia.contentservice.content.presentation.mapper.user;

import dev.animedia.contentservice.content.application.dto.content.ContentRequestDto;
import dev.animedia.contentservice.content.application.dto.content.ContentTranslationDto;
import dev.animedia.contentservice.content.presentation.mapper.ContentTypeMapperGrpc;
import dev.animedia.contentservice.genre.presentation.mapper.user.GenreUserMapperGrpc;
import dev.animedia.contentservice.shared.presentation.grpc.mapper.DateMapper;
import dev.animedia.contentservice.status.presentation.mapper.user.StatusUserMapperGrpc;
import dev.animedia.grpc.content.user.v1.ContentUserProto;
import dev.animedia.grpc.genre.user.v1.GenreUserProto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class ContentUserMapperGrpc {

	private final ContentTypeMapperGrpc contentTypeMapperGrpc;
	private final DateMapper dateMapper;
	private final StatusUserMapperGrpc statusUserMapperGrpc;
	private final GenreUserMapperGrpc genreUserMapperGrpc;

	@Autowired
	public ContentUserMapperGrpc(
		ContentTypeMapperGrpc contentTypeMapperGrpc,
		DateMapper dateMapper,
		StatusUserMapperGrpc statusUserMapperGrpc,
		GenreUserMapperGrpc genreUserMapperGrpc
	) {
		this.contentTypeMapperGrpc = contentTypeMapperGrpc;
		this.dateMapper = dateMapper;
		this.statusUserMapperGrpc = statusUserMapperGrpc;
		this.genreUserMapperGrpc = genreUserMapperGrpc;
	}

	public ContentUserProto.ContentResponse toContentGrpcResponse(
		ContentRequestDto contentRequestDto
	) {
		if (contentRequestDto == null) return null;

		var contentResponse = ContentUserProto.ContentResponse.newBuilder()
			.setAlias(contentRequestDto.alias())
			.setType(
				contentTypeMapperGrpc.toProtoContentType(contentRequestDto.type())
			)
			.setSeason(contentRequestDto.season())
			.setStatus(
				statusUserMapperGrpc.toStatusGrpcResponse(
					contentRequestDto.statusId()
				)
			);

		if (contentRequestDto.coverImageId() != null)
			contentResponse.setCoverImageUuid(
				contentRequestDto.coverImageId()
			);

		if (contentRequestDto.trailerVideoId() != null)
			contentResponse.setTrailerVideoUuid(
				contentRequestDto.trailerVideoId()
			);

		if (contentRequestDto.releaseDate() != null)
			contentResponse.setReleaseDate(
				dateMapper.toGrpcDate(contentRequestDto.releaseDate())
			);

		List<String> languages = contentRequestDto.languageCodeSet() != null
			? List.copyOf(contentRequestDto.languageCodeSet())
			: List.of();

		contentResponse.addAllLanguageCodes(languages);

		List<GenreUserProto.GenreResponse> genres =
			contentRequestDto.genreIdSet() != null
			? contentRequestDto.genreIdSet()
				.stream()
				.map(genreUserMapperGrpc::toGenreGrpcResponse)
				.filter(Objects::nonNull)
				.toList()
			: List.of();

		contentResponse.addAllGenres(genres);

		List<ContentUserProto.ContentTranslationResponse> translations =
			contentRequestDto.translationSet() != null
			? contentRequestDto.translationSet()
				.stream()
				.map(this::toContentTranslationGrpcResponse)
				.filter(Objects::nonNull)
				.toList()
			: List.of();

		contentResponse.addAllTranslations(translations);

		return contentResponse
			.build();
	}

	private ContentUserProto.ContentTranslationResponse toContentTranslationGrpcResponse(
		ContentTranslationDto contentTranslationDto
	) {
		if (contentTranslationDto == null) return null;

		return ContentUserProto.ContentTranslationResponse.newBuilder()
			.setTitle(contentTranslationDto.title())
			.setDescription(contentTranslationDto.description())
			.build();
	}
}
