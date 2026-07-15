package dev.animedia.contentservice.content.presentation.mapper.admin.response;

import dev.animedia.contentservice.content.application.dto.content.response.ContentDto;
import dev.animedia.contentservice.content.application.dto.content.response.ContentTranslationDto;
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
		ContentDto contentDto
	) {
		if (contentDto == null) return null;

		String contentId = String.valueOf(contentDto.id());

		var content = ContentAdminProto.ContentResponse
			.newBuilder()
			.setId(contentId)
			.setAlias(contentDto.alias())
			.setType(
				contentTypeMapperGrpc.toProtoContentType(contentDto.type())
			)
			.setSeason(contentDto.season())
			.setStatus(
				statusResponseAdminMapperGrpc.toStatusResponseGrpc(contentDto.status())
			)
			.setActive(contentDto.active());

		Optional.ofNullable(contentDto.coverImageId())
			.ifPresent(content::setCoverImageId);
		Optional.ofNullable(contentDto.trailerVideoId())
			.ifPresent(content::setTrailerVideoId);
		Optional.ofNullable(contentDto.releaseDate())
			.ifPresent(date -> content.setReleaseDate(dateMapper.toGrpcDate(date)));
		Optional.ofNullable(contentDto.createdAt())
			.ifPresent(time -> content.setCreatedAt(dateMapper.toGrpcTimestamp(time)));
		Optional.ofNullable(contentDto.updatedAt())
			.ifPresent(time -> content.setUpdatedAt(dateMapper.toGrpcTimestamp(time)));

		List<String> languages = contentDto.languageCodes() != null
			? List.copyOf(contentDto.languageCodes())
			: List.of();

		List<GenreAdminProto.GenreResponse> genres = contentDto.genres() != null
			? contentDto.genres()
				.stream()
				.map(genreResponseAdminMapperGrpc::toGenreResponseGrpc)
				.toList()
			: List.of();

		List<ContentAdminProto.ContentTranslationResponse> translations =
			contentDto.translations() != null
			? contentDto.translations()
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

	public ContentAdminProto.ContentTranslationResponse toContentTranslationResponseGrpc(
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