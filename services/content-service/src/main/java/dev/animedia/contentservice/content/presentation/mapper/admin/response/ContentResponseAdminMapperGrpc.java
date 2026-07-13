package dev.animedia.contentservice.content.presentation.mapper.admin.response;

import dev.animedia.contentservice.content.application.dto.content.ContentResponseDto;
import dev.animedia.contentservice.content.application.dto.content.ContentTranslationDto;
import dev.animedia.contentservice.content.presentation.mapper.ContentTypeMapperGrpc;
import dev.animedia.contentservice.shared.presentation.grpc.mapper.DateMapper;
import dev.animedia.grpc.content.admin.v1.ContentAdminProto;
import dev.animedia.grpc.genre.admin.v1.GenreAdminProto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ContentResponseAdminMapperGrpc {

	private final ContentTypeMapperGrpc contentTypeMapperGrpc;
	private final DateMapper dateMapper;
	private final StatusResponseAdminMapperGrpc statusResponseAdminMapperGrpc;
	private final GenreResponseAdminMapperGrpc genreResponseAdminMapperGrpc;

	@Autowired
	public ContentResponseAdminMapperGrpc(
		ContentTypeMapperGrpc contentTypeMapperGrpc,
		DateMapper dateMapper,
		StatusResponseAdminMapperGrpc statusResponseAdminMapperGrpc,
		GenreResponseAdminMapperGrpc genreResponseAdminMapperGrpc
	) {
		this.contentTypeMapperGrpc = contentTypeMapperGrpc;
		this.dateMapper = dateMapper;
		this.statusResponseAdminMapperGrpc = statusResponseAdminMapperGrpc;
		this.genreResponseAdminMapperGrpc = genreResponseAdminMapperGrpc;
	}

	public ContentAdminProto.ContentResponse toContentResponseGrpc(
		ContentResponseDto contentResponseDto
	) {
		if (contentResponseDto == null) return null;

		String contentId = String.valueOf(contentResponseDto.id());

		var content = ContentAdminProto.ContentResponse
			.newBuilder()
			.setId(contentId)
			.setAlias(contentResponseDto.alias())
			.setType(
				contentTypeMapperGrpc.toProtoContentType(contentResponseDto.type())
			)
			.setSeason(contentResponseDto.season())
			.setStatus(
				statusResponseAdminMapperGrpc.toStatusResponseGrpc(contentResponseDto.status())
			)
			.setActive(contentResponseDto.active());

		Optional.ofNullable(contentResponseDto.coverImageId())
			.ifPresent(content::setCoverImageId);
		Optional.ofNullable(contentResponseDto.trailerVideoId())
			.ifPresent(content::setTrailerVideoId);
		Optional.ofNullable(contentResponseDto.releaseDate())
			.ifPresent(date -> content.setReleaseDate(dateMapper.toGrpcDate(date)));
		Optional.ofNullable(contentResponseDto.createdAt())
			.ifPresent(time -> content.setCreatedAt(dateMapper.toGrpcTimestamp(time)));
		Optional.ofNullable(contentResponseDto.updatedAt())
			.ifPresent(time -> content.setUpdatedAt(dateMapper.toGrpcTimestamp(time)));

		List<String> languages = contentResponseDto.languageCodeSet() != null
			? List.copyOf(contentResponseDto.languageCodeSet())
			: List.of();

		List<GenreAdminProto.GenreResponse> genres = contentResponseDto.genreDtoSet() != null
			? contentResponseDto.genreDtoSet()
				.stream()
				.map(genreResponseAdminMapperGrpc::toGenreResponseGrpc)
				.toList()
			: List.of();

		List<ContentAdminProto.ContentTranslationResponse> translations =
			contentResponseDto.translationSet() != null
			? contentResponseDto.translationSet()
				.stream()
				.map(ctd -> toContentTranslationResponseGrpc(ctd, contentId))
				.toList()
			: List.of();

		return content
			.addAllLanguageCodes(languages)
			.addAllGenres(genres)
			.addAllTranslations(translations)
			.build();
	}

	private ContentAdminProto.ContentTranslationResponse toContentTranslationResponseGrpc(
		ContentTranslationDto contentTranslationDto,
		String contentId
	) {
		if (contentTranslationDto == null) return null;

		var translation = ContentAdminProto.ContentTranslationResponse
			.newBuilder()
			.setId(String.valueOf(contentTranslationDto.id()))
			.setContentId(contentId)
			.setLanguageCode(contentTranslationDto.languageCode())
			.setTitle(contentTranslationDto.title());

		if (contentTranslationDto.description() != null)
			translation.setDescription(contentTranslationDto.description());

		return translation
			.build();
	}
}