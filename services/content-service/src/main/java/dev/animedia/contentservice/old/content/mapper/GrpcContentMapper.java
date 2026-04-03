package dev.animedia.contentservice.old.content.mapper;

import dev.animedia.contentservice.old.app.mapper.DateMapper;
import dev.animedia.contentservice.old.content.dto.request.ContentRequestDto;
import dev.animedia.contentservice.old.content.dto.request.PrivateSearchRequestDto;
import dev.animedia.contentservice.old.content.dto.request.PublicSearchRequestDto;
import dev.animedia.contentservice.old.content.dto.response.ContentResponseDto;
import dev.animedia.contentservice.old.content.dto.response.ContentWithTranslationListResponseDto;
import dev.animedia.contentservice.old.content.dto.response.ContentWithTranslationResponseDto;
import dev.animedia.contentservice.old.genre.mapper.GrpcGenreMapper;
import dev.animedia.contentservice.old.status.mapper.GrpcContentStatusMapper;
import dev.animedia.grpc.common.CommonProto;
import dev.animedia.grpc.core.ContentCommonProto;
import dev.animedia.grpc.core.PrivateContentProto;
import dev.animedia.grpc.core.PublicContentProto;
import dev.animedia.grpc.genre.GenreCommonProto;
import dev.animedia.grpc.status.ContentStatusCommonProto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Component
public class GrpcContentMapper {

	private final TypeMapper typeMapper;
	private final DateMapper dateMapper;
	private final GrpcContentStatusMapper grpcContentStatusMapper;
	private final GrpcGenreMapper grpcGenreMapper;

	@Autowired
    public GrpcContentMapper(TypeMapper typeMapper, DateMapper dateMapper, GrpcContentStatusMapper grpcContentStatusMapper, GrpcGenreMapper grpcGenreMapper) {
        this.typeMapper = typeMapper;
        this.dateMapper = dateMapper;
		this.grpcContentStatusMapper = grpcContentStatusMapper;
		this.grpcGenreMapper = grpcGenreMapper;
	}

    public PublicSearchRequestDto toPublicSearchRequestDto(PublicContentProto.PublicSearchRequest request) {
		return new PublicSearchRequestDto(
			request.hasAlias() ? request.getAlias() : null,
			request.hasTitle() ? request.getTitle() : null,
			typeMapper.toTypeList(request.getTypeList()),
			request.getSeasonsList(),
			request.getContentStatusIdsList(),
			request.hasReleaseDateFrom() ? dateMapper.toLocalDate(request.getReleaseDateFrom()) : null,
			request.hasReleaseDateTo() ? dateMapper.toLocalDate(request.getReleaseDateTo()) : null,
			request.getLanguageCodesList(),
			request.getGenreIdsList()
		);
	}

	public PublicContentProto.PublicSearchResponse toPublicSearchResponse(
		List<ContentWithTranslationResponseDto> contentsWithTranslation,
		CommonProto.PaginationResponse pagination
	) {
		var protoContents = contentsWithTranslation.stream().map(this::toPublicContentResponseWithTranslation).toList();
		return PublicContentProto.PublicSearchResponse.newBuilder()
			.addAllContents(protoContents)
			.setPagination(pagination)
			.build();
	}

	public PublicContentProto.PublicContentResponseWithTranslation toPublicContentResponseWithTranslation(ContentWithTranslationResponseDto contentWithTranslation) {

		ContentCommonProto.ContentType type = typeMapper.toGrpcType(contentWithTranslation.type());
		ContentStatusCommonProto.ContentStatusWithTranslationResponse status = grpcContentStatusMapper.toProtoContentStatusWithTranslation(contentWithTranslation.status());
		CommonProto.Date releaseDate = dateMapper.toGrpcDate(contentWithTranslation.releaseDate());
		List<GenreCommonProto.GenreWithTranslationResponse> genres = grpcGenreMapper.toProtoGenreListWithTranslation(contentWithTranslation.genres());

		PublicContentProto.PublicContentResponse content = PublicContentProto.PublicContentResponse.newBuilder()
			.setUuid(contentWithTranslation.uuid())
			.setAlias(contentWithTranslation.alias())
			.setType(type)
			.setSeason(contentWithTranslation.season())
			.setStatus(status)
			.setCoverUrl(contentWithTranslation.coverUrl())
			.setTrailerUrl(contentWithTranslation.trailerUrl())
			.setReleaseDate(releaseDate)
			.addAllLanguageCodes(contentWithTranslation.languageCodes())
			.addAllGenres(genres)
			.build();

		ContentCommonProto.ContentTranslationResponse translations = ContentCommonProto.ContentTranslationResponse.newBuilder()
			.setUuid(contentWithTranslation.translationUuid())
			.setContentUuid(contentWithTranslation.uuid())
			.setLanguageCode(contentWithTranslation.languageCode())
			.setTitle(contentWithTranslation.title())
			.setDescription(contentWithTranslation.description())
			.build();

		return PublicContentProto.PublicContentResponseWithTranslation.newBuilder()
			.setContent(content)
			.setTranslation(translations)
			.build();
	}

	public PrivateSearchRequestDto toPrivateSearchRequestDto(PrivateContentProto.PrivateSearchRequest request) {
		return new PrivateSearchRequestDto(
			request.hasUuid() ? UUID.fromString(request.getUuid()) : null,
			request.hasAlias() ? request.getAlias() : null,
			request.hasTitle() ? request.getTitle() : null,
			typeMapper.toTypeList(request.getTypeList()),
			request.getSeasonsList(),
			request.getContentStatusIdsList(),
			request.hasReleaseDateFrom() ? dateMapper.toLocalDate(request.getReleaseDateFrom()) : null,
			request.hasReleaseDateTo() ? dateMapper.toLocalDate(request.getReleaseDateTo()) : null,
			request.hasCreatedAtFrom() ? dateMapper.toLocalDateTime(request.getCreatedAtFrom()) : null,
			request.hasCreatedAtTo() ? dateMapper.toLocalDateTime(request.getCreatedAtTo()) : null,
			request.hasUpdatedAtFrom() ? dateMapper.toLocalDateTime(request.getUpdatedAtFrom()) : null,
			request.hasCreatedAtTo() ? dateMapper.toLocalDateTime(request.getCreatedAtTo()) : null,
			request.hasActive() ? request.getActive() : null,
			request.getLanguageCodesList(),
			request.getGenreIdsList()
		);
	}

	public PrivateContentProto.PrivateSearchResponse toPrivateSearchResponse(
		List<ContentWithTranslationListResponseDto> contentsWithTranslations,
		CommonProto.PaginationResponse pagination
	) {
		var contents = contentsWithTranslations.stream()
			.map(content -> {

				var type = typeMapper.toGrpcType(content.type());
				var status = grpcContentStatusMapper.toProtoContentStatusWithTranslation(content.status());
				var created = dateMapper.toTimestamp(content.createdAt());
				var updated = dateMapper.toTimestamp(content.updatedAt());
				var genres = grpcGenreMapper.toProtoGenreListWithTranslation(content.genres());

				var contentResponse = PrivateContentProto.PrivateContentResponse.newBuilder()
					.setUuid(content.uuid())
					.setAlias(content.alias())
					.setType(type)
					.setSeason(content.season())
					.setStatus(status)
					.setCoverUrl(content.coverUrl())
					.setTrailerUrl(content.trailerUrl())
					.setCreatedAt(created)
					.setUpdatedAt(updated)
					.setActive(content.active())
					.addAllLanguageCodes(content.languageCodes())
					.addAllGenres(genres)
					.build();

				var translations = content.translations().stream().map(translation -> ContentCommonProto.ContentTranslationResponse.newBuilder()
                    .setUuid(translation.uuid())
                    .setContentUuid(translation.contentUuid())
                    .setLanguageCode(translation.languageCode())
                    .setTitle(translation.title())
                    .setDescription(translation.description())
                    .build()).toList();

				return PrivateContentProto.PrivateContentResponseWithTranslationList.newBuilder()
					.setContent(contentResponse)
					.addAllTranslations(translations)
					.build();
			})
			.toList();

		return PrivateContentProto.PrivateSearchResponse.newBuilder()
				.addAllContents(contents)
				.setPagination(pagination)
				.build();
	}

	public ContentRequestDto toContentRequestDto(PrivateContentProto.PrivateCreateRequest request) {
		return new ContentRequestDto(
			request.getAlias(),
			typeMapper.toType(request.getType()),
			request.getSeason(),
			request.getContentStatusId(),
			request.getCoverUrl(),
			request.getTrailerUrl(),
			dateMapper.toLocalDate(request.getReleaseDate()),
			request.getActive(),
			request.getSort(),
			new HashSet<>(request.getLanguageCodesList()),
			new HashSet<>(request.getGenreIdsList())
		);
	}

	public ContentRequestDto toContentRequestDto(PrivateContentProto.PrivateUpdateRequest request) {
		return new ContentRequestDto(
			request.getAlias(),
			typeMapper.toType(request.getType()),
			request.getSeason(),
			request.getContentStatusId(),
			request.getCoverUrl(),
			request.getTrailerUrl(),
			dateMapper.toLocalDate(request.getReleaseDate()),
			request.getActive(),
			request.getSort(),
			new HashSet<>(request.getLanguageCodesList()),
			new HashSet<>(request.getGenreIdsList())
		);
	}

	public PrivateContentProto.PrivateContentResponse toPrivateContentResponse(ContentResponseDto content) {

		var status = grpcContentStatusMapper.toProtoContentStatusWithTranslation(content.status());
		var genres = grpcGenreMapper.toProtoGenreListWithTranslation(content.genres());

		return PrivateContentProto.PrivateContentResponse.newBuilder()
			.setUuid(content.uuid())
			.setAlias(content.alias())
			.setType(typeMapper.toGrpcType(content.type()))
			.setSeason(content.season())
			.setStatus(status)
			.setCoverUrl(content.coverUrl())
			.setTrailerUrl(content.trailerUrl())
			.setReleaseDate(dateMapper.toGrpcDate(content.releaseDate()))
			.setCreatedAt(dateMapper.toTimestamp(content.createdAt()))
			.setUpdatedAt(dateMapper.toTimestamp(content.updatedAt()))
			.setActive(content.active())
			.addAllLanguageCodes(content.languageCodes())
			.addAllGenres(genres)
			.build();
	}
}
