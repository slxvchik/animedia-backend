package dev.animedia.contentservice.presentation.grpc.content.mapper;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import dev.animedia.contentservice.application.content.dto.ContentSearchDto;
import dev.animedia.contentservice.application.content.dto.ContentTranslationDto;
import dev.animedia.contentservice.presentation.grpc.genre.mapper.PublicGenreGrpcMapper;
import dev.animedia.contentservice.presentation.grpc.shared.mapper.DateMapper;
import dev.animedia.contentservice.presentation.grpc.status.mapper.PublicStatusGrpcMapper;
import dev.animedia.grpc.common.CommonProto.PaginationResponse;
import dev.animedia.grpc.core.PublicContentProto.PublicContentResponse;
import dev.animedia.grpc.core.PublicContentProto.PublicContentTranslationResponse;
import dev.animedia.grpc.core.PublicContentProto.PublicSearchRequest;
import dev.animedia.grpc.core.PublicContentProto.PublicSearchResponse;
import dev.animedia.grpc.genre.PublicGenreProto.PublicGenreResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
public class PublicContentGrpcMapper {

	private final ContentTypeGrpcMapper contentTypeGrpcMapper;
	private final DateMapper dateMapper;
	private final PublicStatusGrpcMapper publicStatusGrpcMapper;
	private final PublicGenreGrpcMapper publicGenreGrpcMapper;

	@Autowired
	public PublicContentGrpcMapper(
		ContentTypeGrpcMapper contentTypeGrpcMapper,
		DateMapper dateMapper,
		PublicStatusGrpcMapper publicStatusGrpcMapper,
		PublicGenreGrpcMapper publicGenreGrpcMapper
	) {
		this.contentTypeGrpcMapper = contentTypeGrpcMapper;
		this.dateMapper = dateMapper;
		this.publicStatusGrpcMapper = publicStatusGrpcMapper;
		this.publicGenreGrpcMapper = publicGenreGrpcMapper;
	}

	public ContentSearchDto toContentSearchCriteria(
		PublicSearchRequest request,
		String translateLanguageCode
	) {
		return new ContentSearchDto(
			null,
			request.getAliasesCount() > 0 ? request.getAliasesList() : null,
			request.getTitlesCount() > 0 ? request.getTitlesList() : null,
			request.getTypesCount() > 0
				? request.getTypesList()
					.stream()
					.map(contentTypeGrpcMapper::toContentType)
					.filter(Objects::nonNull)
					.toList()
				: null,
			request.getSeasonsCount() > 0 ? request.getSeasonsList() : null,
			request.getStatusUuidsCount() > 0 ? request.getStatusUuidsList().stream().map(UUID::fromString).toList() : null,
			request.hasReleaseDateFrom() ? dateMapper.toLocalDate(request.getReleaseDateFrom()) : null,
			request.hasReleaseDateTo() ? dateMapper.toLocalDate(request.getReleaseDateFrom()) : null,
			null,
			null,
			null,
			null,
			true,
			request.getLanguageCodesCount() > 0 ? request.getLanguageCodesList() : null,
			request.getGenreUuidsCount() > 0 ? request.getGenreUuidsList().stream().map(UUID::fromString).toList() : null,
			translateLanguageCode
		);
	}

	public PublicSearchResponse toPublicSearchResponse(
		List<PublicContentResponse> publicContentResponseList,
		PaginationResponse paginationResponse
	) {
		return PublicSearchResponse
			.newBuilder()
			.addAllContents(publicContentResponseList)
			.setPagination(paginationResponse)
			.build();
	}

	public PublicContentResponse toPublicContentResponse(
		ContentDto contentDto
	) {
		if (contentDto == null) return null;

		var contentResponse = PublicContentResponse
			.newBuilder()
			.setAlias(contentDto.alias())
			.setType(
				contentTypeGrpcMapper.toProtoContentType(contentDto.type())
			)
			.setSeason(contentDto.season())
			.setStatus(
				publicStatusGrpcMapper.toPublicStatusResponse(
					contentDto.status()
				)
			);

		if (contentDto.coverImageId() != null)
			contentResponse.setCoverImageUuid(
				contentDto.coverImageId().toString()
			);

		if (contentDto.trailerVideoId() != null)
			contentResponse.setTrailerVideoUuid(
				contentDto.trailerVideoId().toString()
			);

		if (contentDto.releaseDate() != null)
			contentResponse.setReleaseDate(
				dateMapper.toGrpcDate(contentDto.releaseDate())
			);

		List<String> languages = contentDto.languageCodeSet() != null
			? List.copyOf(contentDto.languageCodeSet())
			: List.of();

		contentResponse.addAllLanguageCodes(languages);

		List<PublicGenreResponse> genres =
			contentDto.genreSet() != null
			? contentDto.genreSet()
				.stream()
				.map(publicGenreGrpcMapper::toPublicGenreResponse)
				.filter(Objects::nonNull)
				.toList()
			: List.of();

		contentResponse.addAllGenres(genres);

		List<PublicContentTranslationResponse> translations =
			contentDto.translationSet() != null
			? contentDto.translationSet()
				.stream()
				.map(this::toPublicContentTranslationResponse)
				.filter(Objects::nonNull)
				.toList()
			: List.of();

		contentResponse.addAllTranslations(translations);

		return contentResponse
			.build();
	}

	private PublicContentTranslationResponse toPublicContentTranslationResponse(
		ContentTranslationDto contentTranslationDto
	) {
		if (contentTranslationDto == null) return null;

		return PublicContentTranslationResponse
			.newBuilder()
			.setTitle(contentTranslationDto.title())
			.setDescription(contentTranslationDto.description())
			.build();
	}
}
