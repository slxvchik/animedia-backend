package dev.animedia.contentservice.genre.presentation.mapper.admin;

import dev.animedia.contentservice.genre.application.dto.GenreDto;
import dev.animedia.contentservice.genre.application.dto.GenreTranslationDto;
import dev.animedia.grpc.genre.admin.v1.GenreAdminProto;
import dev.animedia.grpc.genre.admin.v1.GenreAdminProtoApi;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class GenreAdminMapperGrpc {

	public GenreDto toGenreDto(
		GenreAdminProtoApi.CreateGenreRequest request
	) {
		if (request == null) return null;

		Set<GenreTranslationDto> translations =
			request.getTranslationsList()
				.stream()
				.map(this::toGenreTranslationDto)
				.filter(Objects::nonNull)
				.collect(Collectors.toSet());

		return new GenreDto(
			null,
			request.getAlias(),
			request.getSortOrder(),
			request.getActive(),
			translations
		);
	}

	private GenreTranslationDto toGenreTranslationDto(
		GenreAdminProtoApi.CreateGenreTranslationRequest request
	) {
		if (request == null) return null;

		return new GenreTranslationDto(
			null,
			request.getLanguageCode(),
			request.getName(),
			request.getDescription()
		);
	}

	public GenreDto toGenreDto(
		GenreAdminProtoApi.UpdateGenreRequest request
	) {
		if (request == null) return null;

		Set<GenreTranslationDto> translations =
			request.getTranslationsList()
				.stream()
				.map(this::toGenreTranslationDto)
				.collect(Collectors.toSet());

		return new GenreDto(
			UUID.fromString(request.getId()),
			null,
			request.getSortOrder(),
			request.getActive(),
			translations
		);
	}

	private GenreTranslationDto toGenreTranslationDto(
		GenreAdminProtoApi.UpdateGenreTranslationRequest request
	) {
		if (request == null) return null;

		return new GenreTranslationDto(
			request.hasId() ? UUID.fromString(request.getId()) : null,
			request.getLanguageCode(),
			request.getName(),
			request.hasDescription() ? request.getDescription() : null
		);
	}

	public GenreAdminProto.GenreResponse toGenreGrpcResponse(
		GenreDto genreDto
	) {
		if (genreDto == null) return null;

		List<GenreAdminProto.GenreTranslationResponse> translations = genreDto.translationSet() != null
			? genreDto.translationSet()
				.stream()
				.map(gtd -> toGenreTranslationGrpcResponse(gtd, genreDto.id()))
				.filter(Objects::nonNull)
				.toList()
			: List.of();

		return GenreAdminProto.GenreResponse
			.newBuilder()
			.setId(String.valueOf(genreDto.id()))
			.setAlias(genreDto.alias())
			.setSortOrder(genreDto.sortOrder())
			.setActive(genreDto.active())
			.addAllTranslations(translations)
			.build();
	}

	private GenreAdminProto.GenreTranslationResponse toGenreTranslationGrpcResponse(
		GenreTranslationDto genreTranslationDto,
		UUID genreId
	) {
		if (genreTranslationDto == null) return null;

		return GenreAdminProto.GenreTranslationResponse
			.newBuilder()
			.setId(String.valueOf(genreTranslationDto.id()))
			.setGenreId(String.valueOf(genreId))
			.setLanguageCode(genreTranslationDto.languageCode())
			.setName(genreTranslationDto.name())
			.setDescription(genreTranslationDto.description())
			.build();
	}
}
