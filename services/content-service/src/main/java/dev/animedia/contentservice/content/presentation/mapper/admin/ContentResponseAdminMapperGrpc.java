package dev.animedia.contentservice.content.presentation.mapper.admin;

import dev.animedia.contentservice.content.application.dto.content.ContentRequestDto;
import dev.animedia.contentservice.content.application.dto.content.ContentTranslationDto;
import dev.animedia.contentservice.content.presentation.mapper.ContentTypeMapperGrpc;
import dev.animedia.contentservice.genre.presentation.mapper.admin.GenreAdminMapperGrpc;
import dev.animedia.contentservice.shared.presentation.grpc.mapper.DateMapper;
import dev.animedia.contentservice.status.presentation.mapper.admin.StatusAdminMapperGrpc;
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
	private final StatusAdminMapperGrpc statusAdminMapperGrpc;
	private final GenreAdminMapperGrpc genreAdminMapperGrpc;

	@Autowired
	public ContentResponseAdminMapperGrpc(
		ContentTypeMapperGrpc contentTypeMapperGrpc,
		DateMapper dateMapper,
		StatusAdminMapperGrpc statusAdminMapperGrpc,
		GenreAdminMapperGrpc genreAdminMapperGrpc
	) {
		this.contentTypeMapperGrpc = contentTypeMapperGrpc;
		this.dateMapper = dateMapper;
		this.statusAdminMapperGrpc = statusAdminMapperGrpc;
		this.genreAdminMapperGrpc = genreAdminMapperGrpc;
	}

	public ContentAdminProto.ContentResponse toContentGrpcResponse(
		ContentRequestDto contentRequestDto
	) {
		if (contentRequestDto == null) return null;

		String contentId = String.valueOf(contentRequestDto.id());

		var content = ContentAdminProto.ContentResponse
			.newBuilder()
			.setId(contentId)
			.setAlias(contentRequestDto.alias())
			.setType(
				contentTypeMapperGrpc.toProtoContentType(contentRequestDto.type())
			)
			.setSeason(contentRequestDto.season())
			.setStatus(
				statusAdminMapperGrpc.toStatusGrpcResponse(contentRequestDto.statusId())
			)
			.setActive(contentRequestDto.active());

		Optional.ofNullable(contentRequestDto.coverImageId())
			.ifPresent(content::setCoverImageId);
		Optional.ofNullable(contentRequestDto.trailerVideoId())
			.ifPresent(content::setTrailerVideoId);
		Optional.ofNullable(contentRequestDto.releaseDate())
			.ifPresent(date -> content.setReleaseDate(dateMapper.toGrpcDate(date)));
		Optional.ofNullable(contentRequestDto.createdAt())
			.ifPresent(time -> content.setCreatedAt(dateMapper.toGrpcTimestamp(time)));
		Optional.ofNullable(contentRequestDto.updatedAt())
			.ifPresent(time -> content.setUpdatedAt(dateMapper.toGrpcTimestamp(time)));

		List<String> languages = contentRequestDto.languageCodeSet() != null
			? List.copyOf(contentRequestDto.languageCodeSet())
			: List.of();

		List<GenreAdminProto.GenreResponse> genres = contentRequestDto.genreIdSet() != null
			? contentRequestDto.genreIdSet()
				.stream()
				.map(genreAdminMapperGrpc::toGenreGrpcResponse)
				.toList()
			: List.of();

		List<ContentAdminProto.ContentTranslationResponse> translations =
			contentRequestDto.translationSet() != null
			? contentRequestDto.translationSet()
				.stream()
				.map(ctd -> toContentTranslationGrpcResponse(ctd, contentId))
				.toList()
			: List.of();

		return content
			.addAllLanguageCodes(languages)
			.addAllGenres(genres)
			.addAllTranslations(translations)
			.build();
	}

	private ContentAdminProto.ContentTranslationResponse toContentTranslationGrpcResponse(
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