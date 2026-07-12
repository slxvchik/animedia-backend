package dev.animedia.contentservice.content.presentation.mapper.user;

import dev.animedia.contentservice.content.application.dto.ContentDto;
import dev.animedia.contentservice.content.application.dto.ContentTranslationDto;
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
		ContentDto contentDto
	) {
		if (contentDto == null) return null;

		var contentResponse = ContentUserProto.ContentResponse.newBuilder()
			.setAlias(contentDto.alias())
			.setType(
				contentTypeMapperGrpc.toProtoContentType(contentDto.type())
			)
			.setSeason(contentDto.season())
			.setStatus(
				statusUserMapperGrpc.toStatusGrpcResponse(
					contentDto.status()
				)
			);

		if (contentDto.coverImageId() != null)
			contentResponse.setCoverImageUuid(
				contentDto.coverImageId()
			);

		if (contentDto.trailerVideoId() != null)
			contentResponse.setTrailerVideoUuid(
				contentDto.trailerVideoId()
			);

		if (contentDto.releaseDate() != null)
			contentResponse.setReleaseDate(
				dateMapper.toGrpcDate(contentDto.releaseDate())
			);

		List<String> languages = contentDto.languageCodeSet() != null
			? List.copyOf(contentDto.languageCodeSet())
			: List.of();

		contentResponse.addAllLanguageCodes(languages);

		List<GenreUserProto.GenreResponse> genres =
			contentDto.genreSet() != null
			? contentDto.genreSet()
				.stream()
				.map(genreUserMapperGrpc::toGenreGrpcResponse)
				.filter(Objects::nonNull)
				.toList()
			: List.of();

		contentResponse.addAllGenres(genres);

		List<ContentUserProto.ContentTranslationResponse> translations =
			contentDto.translationSet() != null
			? contentDto.translationSet()
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
