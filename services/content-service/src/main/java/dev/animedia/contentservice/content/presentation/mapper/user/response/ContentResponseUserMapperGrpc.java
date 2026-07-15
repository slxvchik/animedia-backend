package dev.animedia.contentservice.content.presentation.mapper.user.response;

import dev.animedia.contentservice.content.application.dto.content.response.ContentDto;
import dev.animedia.contentservice.content.application.dto.content.response.ContentTranslationDto;
import dev.animedia.contentservice.content.presentation.mapper.ContentTypeMapperGrpc;
import dev.animedia.contentservice.shared.presentation.grpc.mapper.DateMapper;
import dev.animedia.grpc.content.user.v1.ContentUserProto;
import dev.animedia.grpc.genre.user.v1.GenreUserProto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class ContentResponseUserMapperGrpc {
	private final ContentTypeMapperGrpc contentTypeMapperGrpc;
	private final DateMapper dateMapper;
	private final StatusResponseUserMapperGrpc statusResponseUserMapperGrpc;
	private final GenreResponseUserMapperGrpc genreResponseUserMapperGrpc;

	@Autowired
	public ContentResponseUserMapperGrpc(
		ContentTypeMapperGrpc contentTypeMapperGrpc,
		DateMapper dateMapper,
		StatusResponseUserMapperGrpc statusResponseUserMapperGrpc,
		GenreResponseUserMapperGrpc genreResponseUserMapperGrpc
	) {
		this.contentTypeMapperGrpc = contentTypeMapperGrpc;
		this.dateMapper = dateMapper;
		this.statusResponseUserMapperGrpc = statusResponseUserMapperGrpc;
		this.genreResponseUserMapperGrpc = genreResponseUserMapperGrpc;
	}

	public ContentUserProto.ContentResponse toContentResponseGrpc(
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
				statusResponseUserMapperGrpc.toStatusResponseGrpc(
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

		List<String> languages = contentDto.languageCodes() != null
			? List.copyOf(contentDto.languageCodes())
			: List.of();

		contentResponse.addAllLanguageCodes(languages);

		List<GenreUserProto.GenreResponse> genres =
			contentDto.genres() != null
			? contentDto.genres()
				.stream()
				.map(genreResponseUserMapperGrpc::toGenreResponseGrpc)
				.filter(Objects::nonNull)
				.toList()
			: List.of();

		contentResponse.addAllGenres(genres);

		List<ContentUserProto.ContentTranslationResponse> translations =
			contentDto.translations() != null
			? contentDto.translations()
				.stream()
				.map(this::toContentTranslationResponseGrpc)
				.filter(Objects::nonNull)
				.toList()
			: List.of();

		contentResponse.addAllTranslations(translations);

		return contentResponse
			.build();
	}

	public ContentUserProto.ContentTranslationResponse toContentTranslationResponseGrpc(
		ContentTranslationDto contentTranslationDto
	) {
		if (contentTranslationDto == null) return null;

		return ContentUserProto.ContentTranslationResponse.newBuilder()
			.setTitle(contentTranslationDto.title())
			.setDescription(contentTranslationDto.description())
			.build();
	}
}
