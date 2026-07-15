package dev.animedia.contentservice.content.presentation.mapper.admin.response;

import dev.animedia.contentservice.content.application.dto.genre.GenreDto;
import dev.animedia.contentservice.content.application.dto.genre.GenreTranslationDto;
import dev.animedia.grpc.genre.admin.v1.GenreAdminProto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
public class GenreResponseAdminMapperGrpc {
	public GenreAdminProto.GenreResponse toGenreResponseGrpc(
		GenreDto genreDto
	) {
		if (genreDto == null) return null;

		List<GenreAdminProto.GenreTranslationResponse> translations = genreDto.translations() != null
			? genreDto.translations()
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

	public GenreAdminProto.GenreTranslationResponse toGenreTranslationResponseGrpc(
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
