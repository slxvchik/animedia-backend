package dev.animedia.contentservice.presentation.grpc.genre.mapper;

import dev.animedia.contentservice.application.genre.dto.GenreDto;
import dev.animedia.contentservice.application.genre.dto.GenreSearchDto;
import dev.animedia.contentservice.application.genre.dto.GenreTranslationDto;
import dev.animedia.grpc.genre.PrivateGenreProto.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class PrivateGenreGrpcMapper {
	public GenreSearchDto toGenreSearchDto(
		PrivateSearchGenreRequest request
	) {
		if (request == null) return new GenreSearchDto(null, null, null, null, null);

		return new GenreSearchDto(
			request.hasActive() ? request.getActive() : null,
			request.hasAlias() ? request.getAlias() : null,
			request.hasName() ? request.getName() : null,
			request.hasDescription() ? request.getDescription() : null,
			request.hasLanguageCode() ? request.getLanguageCode() : null
		);
	}

	public GenreDto toGenreDto(
		CreateGenreRequest request
	) {
		if (request == null) return null;

		List<CreateGenreTranslationRequest> translations =
			request.getTranslationsList()
				.stream()
				.map(this::toGenreTranslationDto)
				.toList();

		return new GenreDto(
			null,
			request.getAlias(),
			request.getSort(),
			request.getActive(),
			translations
		);
	}

	private GenreTranslationDto toGenreTranslationDto(
		CreateGenreTranslationRequest request
	) {
		return null;
	}

	public PrivateGenreResponse toPrivateGenreResponse(
		GenreDto genreDto
	) {
		if (genreDto == null) return null;

		List<PrivateGenreTranslationResponse> translations = genreDto.translationSet() != null
			? genreDto.translationSet()
				.stream()
				.map(gtd -> toPrivateGenreTranslationResponse(gtd, genreDto.id()))
				.filter(Objects::nonNull)
				.toList()
			: List.of();

		return PrivateGenreResponse
			.newBuilder()
			.setId(genreDto.id())
			.setAlias(genreDto.alias())
			.setSortOrder(genreDto.sortOrder())
			.setActive(genreDto.active())
			.addAllTranslations(translations)
			.build();
	}

	private PrivateGenreTranslationResponse toPrivateGenreTranslationResponse(
		GenreTranslationDto genreTranslationDto,
		Long genreId
	) {
		if (genreTranslationDto == null) return null;

		return PrivateGenreTranslationResponse
			.newBuilder()
			.setId(genreTranslationDto.id())
			.setGenreId(genreId)
			.setLanguageCode(genreTranslationDto.languageCode())
			.setName(genreTranslationDto.name())
			.setDescription(genreTranslationDto.description())
			.build();
	}
}
