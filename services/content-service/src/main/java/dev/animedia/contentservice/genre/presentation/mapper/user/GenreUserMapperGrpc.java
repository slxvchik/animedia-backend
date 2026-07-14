package dev.animedia.contentservice.genre.presentation.mapper.user;

import dev.animedia.contentservice.genre.application.dto.response.GenreDto;
import dev.animedia.contentservice.genre.application.dto.response.GenreTranslationDto;
import dev.animedia.grpc.genre.user.v1.GenreUserProto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class GenreUserMapperGrpc {
	public GenreUserProto.GenreResponse toGenreResponseGrpc(
		GenreDto genreDto
	) {
		if (genreDto == null) return null;

		List<GenreUserProto.GenreTranslationResponse> translations = genreDto.translationSet() != null
			? genreDto.translationSet()
				.stream()
				.map(this::toGenreTranslationResponseGrpc)
				.filter(Objects::nonNull)
				.toList()
			: List.of();

		return GenreUserProto.GenreResponse
			.newBuilder()
			.setAlias(genreDto.alias())
			.setSortOrder(genreDto.sortOrder())
			.addAllTranslations(translations)
			.build();
	}

	private GenreUserProto.GenreTranslationResponse toGenreTranslationResponseGrpc(
		GenreTranslationDto genreTranslationDto
	) {
		if (genreTranslationDto == null) return null;

		return GenreUserProto.GenreTranslationResponse
			.newBuilder()
			.setLanguageCode(genreTranslationDto.languageCode())
			.setName(genreTranslationDto.name())
			.setDescription(genreTranslationDto.description())
			.build();
	}
}
