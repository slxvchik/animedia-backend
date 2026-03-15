package dev.animedia.contentservice.content.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import dev.animedia.contentservice.content.dto.request.ContentRequestDto;
import dev.animedia.contentservice.content.dto.response.ContentResponseDto;
import dev.animedia.contentservice.content.dto.response.ContentTranslationResponseDto;
import dev.animedia.contentservice.content.dto.response.ContentWithTranslationResponseDto;
import dev.animedia.contentservice.content.dto.response.ContentWithTranslationListResponseDto;
import dev.animedia.contentservice.content.model.Content;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationResponseDto;
import dev.animedia.contentservice.genre.model.Genre;
import dev.animedia.contentservice.status.dto.response.ContentStatusWithTranslationResponseDto;
import dev.animedia.contentservice.status.model.ContentStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class ContentMapper {

	@PersistenceContext
	private EntityManager entityManager;

	public Content toContent(ContentRequestDto dto) {
		Content content = new Content();

		content.setAlias(dto.alias());
		content.setType(dto.type());
		content.setSeason(dto.season());

		ContentStatus status = entityManager.getReference(ContentStatus.class, dto.contentStatusId());
		content.setStatus(status);

		content.setCoverUrl(dto.coverUrl());
		content.setTrailerUrl(dto.trailerUrl());
		content.setReleaseDate(dto.releaseDate());
		content.setActive(dto.active());
		content.setSort(dto.sort());
		content.setLanguageCodes(dto.languageCodes());

		Set<Genre> genres = new HashSet<>();
		for (var genreId : dto.genreIds()) {
			genres.add(entityManager.getReference(Genre.class, genreId));
		}
		content.setGenres(genres);

		return content;
	}

	public ContentResponseDto toContentResponseDto(
		Content content,
		ContentStatusWithTranslationResponseDto contentStatusWithTranslation,
		List<GenreWithTranslationResponseDto> genresWithTranslation
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
			genresWithTranslation
		);
	}

	/**
	 * @param contents - content entity without translation
	 * @param contentsTranslation - translation response dto for content
	 * @param contentStatusesWithTranslation - statuses response dto with translation
	 * @param genresWithTranslation - genres response dto with translation
	 * @return a list of content with a multiple translations and translated nested entities.
	 */
	public List<ContentWithTranslationListResponseDto> toContentListWithTranslationListResponseDto(
		List<Content> contents,
		List<ContentTranslationResponseDto> contentsTranslation,
		List<ContentStatusWithTranslationResponseDto> contentStatusesWithTranslation,
		List<GenreWithTranslationResponseDto> genresWithTranslation
	) {
		// content translations map by content id
		Map<String, List<ContentTranslationResponseDto>> translationsMap = contentsTranslation.stream()
			.collect(Collectors.groupingBy(ContentTranslationResponseDto::contentUuid));

		// statuses map by id
		Map<Long, ContentStatusWithTranslationResponseDto> statusesMap = createMapByKey(contentStatusesWithTranslation, ContentStatusWithTranslationResponseDto::id);

		// genres map by id
		Map<Long, GenreWithTranslationResponseDto> genresMap = createMapByKey(genresWithTranslation, GenreWithTranslationResponseDto::id);

		return contents.stream()
			.map(content -> {
				String contentUuid = content.getUuid().toString();

				var statusResponse = statusesMap.get(content.getStatus().getId());

				var genresResponse = content.getGenres().stream()
					.map(g -> genresMap.get(g.getId()))
					.filter(Objects::nonNull)
					.toList();

				return new ContentWithTranslationListResponseDto(
					contentUuid,
					content.getAlias(),
					content.getType(),
					content.getSeason(),
					statusResponse,
					content.getCoverUrl(),
					content.getTrailerUrl(),
					content.getReleaseDate(),
					content.getCreatedAt(),
					content.getUpdatedAt(),
					content.getActive(),
					content.getSort(),
					new ArrayList<>(content.getLanguageCodes()),
					genresResponse,
					translationsMap.getOrDefault(contentUuid, List.of())
				);
			})
			.toList();
	}

	/**
	 * @param contents - content entity without translation
	 * @param contentsTranslation - translation response dto for content
	 * @param contentStatusesWithTranslation - statuses response dto with translation
	 * @param genresWithTranslation - genres response dto with translation
	 * @return a list of content with a single translation and translated nested entities.
	 */
	public List<ContentWithTranslationResponseDto> toContentListWithTranslationResponseDto(
		List<Content> contents,
		List<ContentTranslationResponseDto> contentsTranslation,
		List<ContentStatusWithTranslationResponseDto> contentStatusesWithTranslation,
		List<GenreWithTranslationResponseDto> genresWithTranslation
	) {
		// content translations map by content id
		Map<String, ContentTranslationResponseDto> translationMap = contentsTranslation.stream()
			.collect(
				Collectors.toMap(
					ContentTranslationResponseDto::contentUuid,
					Function.identity()
				)
			);

		// statuses map by id
		Map<Long, ContentStatusWithTranslationResponseDto> statusesMap = createMapByKey(contentStatusesWithTranslation, ContentStatusWithTranslationResponseDto::id);

		// genres map by id
		Map<Long, GenreWithTranslationResponseDto> genresMap = createMapByKey(genresWithTranslation, GenreWithTranslationResponseDto::id);

		return contents.stream()
			.map(content -> {
				String contentUuid = content.getUuid().toString();

				var translation = translationMap.get(contentUuid);
				if (translation == null) return null;

				var statusResponse = statusesMap.get(content.getStatus().getId());

				var genresResponse = content.getGenres().stream()
					.map(g -> genresMap.get(g.getId()))
					.filter(Objects::nonNull)
					.toList();

				return new ContentWithTranslationResponseDto(
					contentUuid,
					content.getAlias(),
					content.getType(),
					content.getSeason(),
					statusResponse,
					content.getCoverUrl(),
					content.getTrailerUrl(),
					content.getReleaseDate(),
					content.getCreatedAt(),
					content.getUpdatedAt(),
					content.getActive(),
					content.getSort(),
					new ArrayList<>(content.getLanguageCodes()),
					genresResponse,

					translation.uuid(),
					translation.languageCode(),
					translation.title(),
					translation.description()
				);
			})
			.filter(Objects::nonNull)
			.toList();
	}

	public ContentWithTranslationResponseDto toContentWithTranslationResponseDto(
		ContentResponseDto contentResponseDto,
		ContentTranslationResponseDto translationResponseDto
	) {
		return new ContentWithTranslationResponseDto(
			contentResponseDto.uuid(),
			contentResponseDto.alias(),
			contentResponseDto.type(),
			contentResponseDto.season(),
			contentResponseDto.status(),
			contentResponseDto.coverUrl(),
			contentResponseDto.trailerUrl(),
			contentResponseDto.releaseDate(),
			contentResponseDto.createdAt(),
			contentResponseDto.updatedAt(),
			contentResponseDto.active(),
			contentResponseDto.sort(),
			contentResponseDto.languageCodes(),
			contentResponseDto.genres(),

			translationResponseDto.uuid(),
			translationResponseDto.languageCode(),
			translationResponseDto.title(),
			translationResponseDto.description()
		);
	}

	public void updateEntity(ContentRequestDto dto, Content content) {
		content.setAlias(dto.alias());
		content.setType(dto.type());
		content.setSeason(dto.season());

		content.setStatus(entityManager.getReference(ContentStatus.class, dto.contentStatusId()));

		content.setCoverUrl(dto.coverUrl());
		content.setTrailerUrl(dto.trailerUrl());
		content.setReleaseDate(dto.releaseDate());
		content.setActive(dto.active());

		content.getLanguageCodes().clear();
		dto.languageCodes().forEach(code ->
			content.getLanguageCodes().add(code)
		);

		content.getGenres().clear();
		dto.genreIds().forEach(id ->
			content.getGenres().add(entityManager.getReference(Genre.class, id))
		);
	}

	private <K, V> Map<K, V> createMapByKey(List<V> entities, Function<V, K> keyExtractor) {
		return entities.stream()
			.collect(
				Collectors.toMap(
					keyExtractor,
					Function.identity(),
					(first, second) -> first
				)
			);
	}
}