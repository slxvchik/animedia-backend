package dev.animedia.contentservice.presentation.grpc.content.mapper;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import dev.animedia.contentservice.application.content.dto.ContentTranslationDto;
import dev.animedia.contentservice.presentation.grpc.genre.mapper.PrivateGenreGrpcMapper;
import dev.animedia.contentservice.presentation.grpc.shared.mapper.DateMapper;
import dev.animedia.contentservice.presentation.grpc.status.mapper.PrivateStatusGrpcMapper;
import dev.animedia.grpc.core.PrivateContentProto.PrivateContentResponse;
import dev.animedia.grpc.core.PrivateContentProto.PrivateContentTranslationResponse;
import dev.animedia.grpc.genre.PrivateGenreProto.PrivateGenreResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PrivateContentCommonGrpcMapper {

	private final ContentTypeGrpcMapper contentTypeGrpcMapper;
	private final DateMapper dateMapper;
	private final PrivateStatusGrpcMapper privateStatusGrpcMapper;
	private final PrivateGenreGrpcMapper privateGenreGrpcMapper;

	@Autowired
	public PrivateContentCommonGrpcMapper(
		ContentTypeGrpcMapper contentTypeGrpcMapper,
		DateMapper dateMapper,
		PrivateStatusGrpcMapper privateStatusGrpcMapper,
		PrivateGenreGrpcMapper privateGenreGrpcMapper
	) {
		this.contentTypeGrpcMapper = contentTypeGrpcMapper;
		this.dateMapper = dateMapper;
		this.privateStatusGrpcMapper = privateStatusGrpcMapper;
		this.privateGenreGrpcMapper = privateGenreGrpcMapper;
	}

	public PrivateContentResponse toPrivateContentResponse(
		ContentDto contentDto
	) {
		if (contentDto == null) return null;

		String contentId = String.valueOf(contentDto.id());

		var content = PrivateContentResponse
			.newBuilder()
			.setUuid(contentId)
			.setAlias(contentDto.alias())
			.setType(
				contentTypeGrpcMapper.toProtoContentType(contentDto.type())
			)
			.setSeason(contentDto.season())
			.setStatus(
				privateStatusGrpcMapper.toPrivateStatusResponse(contentDto.status())
			)
			.setActive(contentDto.active());

		Optional.ofNullable(contentDto.coverImageId())
			.ifPresent(id -> content.setCoverImageUuid(id.toString()));
		Optional.ofNullable(contentDto.trailerVideoId())
			.ifPresent(id -> content.setTrailerVideoUuid(id.toString()));
		Optional.ofNullable(contentDto.releaseDate())
			.ifPresent(date -> content.setReleaseDate(dateMapper.toGrpcDate(date)));
		Optional.ofNullable(contentDto.createdAt())
			.ifPresent(time -> content.setCreatedAt(dateMapper.toGrpcTimestamp(time)));
		Optional.ofNullable(contentDto.updatedAt())
			.ifPresent(time -> content.setUpdatedAt(dateMapper.toGrpcTimestamp(time)));

		List<String> languages = contentDto.languageCodeSet() != null
			? List.copyOf(contentDto.languageCodeSet())
			: List.of();

		List<PrivateGenreResponse> genres = contentDto.genreSet() != null
			? contentDto.genreSet()
				.stream()
				.map(privateGenreGrpcMapper::toPrivateGenreResponse)
				.toList()
			: List.of();

		List<PrivateContentTranslationResponse> translations =
			contentDto.translationSet() != null
			? contentDto.translationSet()
				.stream()
				.map(ctd -> toPrivateContentTranslationResponse(ctd, contentId))
				.toList()
			: List.of();

		return content
			.addAllLanguageCodes(languages)
			.addAllGenres(genres)
			.addAllTranslations(translations)
			.build();
	}

	private PrivateContentTranslationResponse toPrivateContentTranslationResponse(
		ContentTranslationDto contentTranslationDto,
		String contentId
	) {
		if (contentTranslationDto == null) return null;

		var translation = PrivateContentTranslationResponse
			.newBuilder()
			.setUuid(String.valueOf(contentTranslationDto.id()))
			.setContentUuid(contentId)
			.setLanguageCode(contentTranslationDto.languageCode())
			.setTitle(contentTranslationDto.title());

		if (contentTranslationDto.description() != null)
			translation.setDescription(contentTranslationDto.description());

		return translation
			.build();
	}
}