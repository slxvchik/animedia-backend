package dev.animedia.contentservice.content.mapper;

import dev.animedia.contentservice.content.dto.request.ContentRequestDto;
import dev.animedia.contentservice.content.dto.response.ContentResponseDto;
import dev.animedia.contentservice.content.dto.response.ContentTranslationResponseDto;
import dev.animedia.contentservice.content.dto.response.ContentWithTranslationResponseDto;
import dev.animedia.contentservice.content.model.Content;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationResponseDto;
import dev.animedia.contentservice.status.dto.response.ContentStatusWithTranslationResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContentMapper {

	public Content toContent(ContentRequestDto contentRequestDto) {
		Content content = new Content();

		if (contentRequestDto.uuid() != null) {
			content.setUuid(contentRequestDto.uuid());
		}
		content.setAlias(contentRequestDto.alias());
		content.setType(contentRequestDto.type());
		content.setSeason(contentRequestDto.season());
		content.setStatus(contentRequestDto.status());
		content.setCoverUrl(contentRequestDto.coverUrl());
		content.setTrailerUrl(contentRequestDto.trailerUrl());
		content.setReleaseDate(contentRequestDto.releaseDate());
		content.setActive(contentRequestDto.active());
		content.setSort(contentRequestDto.sort());
		content.setLanguageCodes(contentRequestDto.languageCodes());
		content.setGenres(contentRequestDto.genres());

		return content;
	}

	public ContentResponseDto toContentResponseDto(
		Content content,
		ContentStatusWithTranslationResponseDto contentStatusWithTranslation,
		List<GenreWithTranslationResponseDto> genreWithTranslations
	) {
		return new ContentResponseDto(
			content.getUuid().toString(),
			content.getAlias(),
			content.getType(),
			content.getSeason(),
			contentStatusWithTranslation,
			content.getCoverUrl(),
			content.getTrailerUrl(),
			content.getReleaseDate(),
			content.getCreatedAt(),
			content.getUpdatedAt(),
			content.getActive(),
			content.getSort(),
			new ArrayList<>(content.getLanguageCodes()),
			genreWithTranslations
		);
	}

	public ContentWithTranslationResponseDto toContentWithTranslationResponseDto(
		ContentResponseDto contentResponseDto,
		ContentTranslationResponseDto translationResponseDto
	) {
		return new ContentWithTranslationResponseDto(
			contentResponseDto,
			translationResponseDto
		);
	}
}