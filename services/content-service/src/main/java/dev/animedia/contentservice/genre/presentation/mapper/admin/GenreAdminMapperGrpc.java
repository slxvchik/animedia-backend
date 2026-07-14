package dev.animedia.contentservice.genre.presentation.mapper.admin;

import dev.animedia.contentservice.genre.application.dto.request.CreateGenreDto;
import dev.animedia.contentservice.genre.application.dto.request.CreateGenreTranslationDto;
import dev.animedia.contentservice.genre.application.dto.request.UpdateGenreDto;
import dev.animedia.contentservice.genre.application.dto.request.UpdateGenreTranslationDto;
import dev.animedia.contentservice.genre.application.dto.response.GenreDto;
import dev.animedia.contentservice.genre.application.dto.response.GenreTranslationDto;
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
	public CreateGenreDto toGenreDto(
		GenreAdminProtoApi.CreateGenreRequest request
	) {
		if (request == null) return null;

		Set<CreateGenreTranslationDto> translations =
			request.getTranslationsList()
				.stream()
				.map(this::toGenreTranslationDto)
				.filter(Objects::nonNull)
				.collect(Collectors.toSet());

		return new CreateGenreDto(
			request.getAlias(),
			request.getSortOrder(),
			request.getActive(),
			translations
		);
	}

	private CreateGenreTranslationDto toGenreTranslationDto(
		GenreAdminProtoApi.CreateGenreTranslationRequest request
	) {
		if (request == null) return null;

		return new CreateGenreTranslationDto(
			request.getLanguageCode(),
			request.getName(),
			request.getDescription()
		);
	}

	public UpdateGenreDto toGenreDto(
		GenreAdminProtoApi.UpdateGenreRequest request
	) {
		if (request == null) return null;

		Set<UpdateGenreTranslationDto> translations =
			request.getTranslationsList()
				.stream()
				.map(this::toGenreTranslationDto)
				.collect(Collectors.toSet());

		return new UpdateGenreDto(
			UUID.fromString(request.getId()),
			request.getSortOrder(),
			request.getActive(),
			translations
		);
	}

	private UpdateGenreTranslationDto toGenreTranslationDto(
		GenreAdminProtoApi.UpdateGenreTranslationRequest request
	) {
		if (request == null) return null;

		return new UpdateGenreTranslationDto(
			request.hasId() ? UUID.fromString(request.getId()) : null,
			request.getLanguageCode(),
			request.getName(),
			request.hasDescription() ? request.getDescription() : null
		);
	}

	public GenreAdminProto.GenreResponse toGenreResponseGrpc(
		GenreDto genreDto
	) {
		if (genreDto == null) return null;

		List<GenreAdminProto.GenreTranslationResponse> translations = genreDto.translationSet() != null
			? genreDto.translationSet()
				.stream()
				.map(gtd -> toGenreTranslationResponseGrpc(gtd, genreDto.id()))
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

	private GenreAdminProto.GenreTranslationResponse toGenreTranslationResponseGrpc(
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
