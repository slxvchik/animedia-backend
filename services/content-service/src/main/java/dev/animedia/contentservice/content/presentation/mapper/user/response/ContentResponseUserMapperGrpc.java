package dev.animedia.contentservice.content.presentation.mapper.user.response;

import dev.animedia.contentservice.content.application.dto.content.ContentResponseDto;
import dev.animedia.contentservice.content.application.dto.content.ContentTranslationDto;
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
		ContentResponseDto contentResponseDto
	) {
		if (contentResponseDto == null) return null;

		var contentResponse = ContentUserProto.ContentResponse.newBuilder()
			.setAlias(contentResponseDto.alias())
			.setType(
				contentTypeMapperGrpc.toProtoContentType(contentResponseDto.type())
			)
			.setSeason(contentResponseDto.season())
			.setStatus(
				statusResponseUserMapperGrpc.toStatusResponseGrpc(
					contentResponseDto.status()
				)
			);

		if (contentResponseDto.coverImageId() != null)
			contentResponse.setCoverImageUuid(
				contentResponseDto.coverImageId()
			);

		if (contentResponseDto.trailerVideoId() != null)
			contentResponse.setTrailerVideoUuid(
				contentResponseDto.trailerVideoId()
			);

		if (contentResponseDto.releaseDate() != null)
			contentResponse.setReleaseDate(
				dateMapper.toGrpcDate(contentResponseDto.releaseDate())
			);

		List<String> languages = contentResponseDto.languageCodeSet() != null
			? List.copyOf(contentResponseDto.languageCodeSet())
			: List.of();

		contentResponse.addAllLanguageCodes(languages);

		List<GenreUserProto.GenreResponse> genres =
			contentResponseDto.genreDtoSet() != null
			? contentResponseDto.genreDtoSet()
				.stream()
				.map(genreResponseUserMapperGrpc::toGenreResponseGrpc)
				.filter(Objects::nonNull)
				.toList()
			: List.of();

		contentResponse.addAllGenres(genres);

		List<ContentUserProto.ContentTranslationResponse> translations =
			contentResponseDto.translationSet() != null
			? contentResponseDto.translationSet()
				.stream()
				.map(this::toContentTranslationResponseGrpc)
				.filter(Objects::nonNull)
				.toList()
			: List.of();

		contentResponse.addAllTranslations(translations);

		return contentResponse
			.build();
	}

	private ContentUserProto.ContentTranslationResponse toContentTranslationResponseGrpc(
		ContentTranslationDto contentTranslationDto
	) {
		if (contentTranslationDto == null) return null;

		return ContentUserProto.ContentTranslationResponse.newBuilder()
			.setTitle(contentTranslationDto.title())
			.setDescription(contentTranslationDto.description())
			.build();
	}
}
