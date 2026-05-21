package dev.animedia.contentservice.infrastructure.persistence.content.repository;

import dev.animedia.contentservice.domain.content.model.ContentType;
import dev.animedia.contentservice.infrastructure.persistence.content.model.ContentEntity;
import dev.animedia.contentservice.infrastructure.persistence.content.model.ContentTranslationEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ContentSpecification {
	private ContentSpecification() {}

	public static Specification<ContentEntity> hasUuid(UUID uuid) {
		return (root, _, cb) ->
			uuid != null
				? cb.equal(root.get("uuid"), uuid)
				: null;
	}
	public static Specification<ContentEntity> hasAliases(List<String> aliases) {
		return (root, _, _) -> {
			var cleanAliases = cleanList(aliases);
			return !cleanAliases.isEmpty() ? root.get("alias").in(aliases) : null;
		};
	}
	public static Specification<ContentEntity> hasTypes(List<ContentType> types) {
		return (root, _, _) -> {
			var cleanTypes = cleanList(types);
			return !cleanTypes.isEmpty() ? root.get("type").in(cleanTypes) : null;
		};
	}
	public static Specification<ContentEntity> hasSeasons(List<Integer> seasons) {
		return (root, _, _) -> {
			var cleanSeasons = cleanList(seasons);
			return !cleanSeasons.isEmpty() ? root.get("season").in(cleanSeasons) : null;
		};
	}
	public static Specification<ContentEntity> hasStatuses(List<Long> statuses) {
		return (root, _, _) -> {
			var cleanStatuses = cleanList(statuses);
			return !cleanStatuses.isEmpty() ? root.get("status").in(cleanStatuses) : null;
		};
	}
	public static Specification<ContentEntity> hasReleaseFrom(LocalDate releaseFrom) {
		return (root, _, cb) ->
			releaseFrom != null
				? cb.greaterThanOrEqualTo(root.get("releaseDate"), releaseFrom)
				: null;
	}
	public static Specification<ContentEntity> hasReleaseTo(LocalDate releaseTo) {
		return (root, _, cb) ->
			releaseTo != null
				? cb.lessThanOrEqualTo(root.get("releaseDate"), releaseTo)
				: null;
	}
	public static Specification<ContentEntity> hasLanguageCodes(List<String> languageCodes) {
		return (root, _, _) -> {
			var cleanLanguageCodes = cleanList(languageCodes);
			return !cleanLanguageCodes.isEmpty() ? root.get("languageCodes").in(cleanLanguageCodes) : null;
		};
	}
	public static Specification<ContentEntity> hasGenres(List<Long> genreIds) {
		return (root, _, _) -> {
			var cleanGenreIds = cleanList(genreIds);
			return !cleanGenreIds.isEmpty() ? root.join("genres", JoinType.LEFT).get("id").in(cleanGenreIds) : null;
		};
	}
	public static Specification<ContentEntity> hasCreatedAtFrom(LocalDateTime createdAtFrom) {
		return (root, _, cb) ->
			createdAtFrom != null
				? cb.lessThanOrEqualTo(root.get("createdAt"), createdAtFrom)
				: null;
	}
	public static Specification<ContentEntity> hasCreatedTo(LocalDateTime createdAtTo) {
		return (root, _, cb) ->
			createdAtTo != null
				? cb.greaterThanOrEqualTo(root.get("createdAt"), createdAtTo)
				: null;
	}
	public static Specification<ContentEntity> hasUpdatedAtFrom(LocalDateTime updatedAtFrom) {
		return (root, _, cb) ->
			updatedAtFrom != null
				? cb.lessThanOrEqualTo(root.get("updatedAt"), updatedAtFrom)
				: null;
	}
	public static Specification<ContentEntity> hasUpdatedAtTo(LocalDateTime updatedAtTo) {
		return (root, _, cb) ->
			updatedAtTo != null
				? cb.greaterThanOrEqualTo(root.get("updatedAt"), updatedAtTo)
				: null;
	}
	public static Specification<ContentEntity> hasActive(Boolean active) {
		return (root, _, cb) ->
			active != null
				? cb.equal(root.get("active"), active)
				: null;
	}

	/**
	 * If language code is NULL, returns all translates
	 * @param titleList
	 * @param languageCode
	 * @return specification with translations
	 */
	public static Specification<ContentEntity> hasTranslationFilters(List<String> titleList, @Nullable String languageCode) {
		return (root, query, cb) -> {
			var cleanTitles = cleanList(titleList).stream().map(String::toLowerCase).toList();
			var hasLanguageFilter = languageCode != null && !languageCode.isBlank();

			Join<ContentEntity, ContentTranslationEntity> join = root.join("translations");
			List<Predicate> predicates = new ArrayList<>();

			if (!cleanTitles.isEmpty()) {
				List<Predicate> titlePredicates = cleanTitles.stream()
					.map(title -> cb.like(cb.lower(join.get("title")), "%" + title + "%"))
					.toList();
				predicates.add(
					cb.or(
						titlePredicates.toArray(new Predicate[0])
					)
				);
			}
			if (hasLanguageFilter) {
				predicates.add(cb.lower(join.get("languageCode")).equalTo(languageCode.toLowerCase()));
			}

			query.distinct(true);

			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}

	private static <T> List<T> cleanList(List<T> list) {
		if (list == null || list.isEmpty()) return List.of();
		return list.stream().filter(Objects::nonNull).distinct().toList();
	}
}

