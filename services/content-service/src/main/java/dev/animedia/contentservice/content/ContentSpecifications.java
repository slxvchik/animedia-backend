package dev.animedia.contentservice.content;

import dev.animedia.contentservice.content.model.Content;
import dev.animedia.contentservice.content.model.ContentTranslation;
import dev.animedia.contentservice.content.model.ContentType;
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

public class ContentSpecifications {
	public static Specification<Content> hasUuid(UUID uuid) {
		return (root, query, cb) ->
			uuid != null
				? cb.equal(root.get("uuid"), uuid)
				: null;
	}
	public static Specification<Content> hasAlias(String alias) {
		return (root, query, cb) ->
			alias != null && !alias.isBlank()
				? cb.like(root.get("alias"), "%" + alias + "%")
				: null;
	}
	public static Specification<Content> hasType(ContentType type) {
		return (root, query, cb) ->
			type != null
				? cb.equal(root.get("type"), type)
				: null;
	}
	public static Specification<Content> hasSeasons(List<Integer> seasons) {
		return (root, query, cb) -> {
			var cleanSeasons = cleanList(seasons);
			return cleanSeasons != null ? root.get("seasons").in(cleanSeasons) : null;
		};
	}
	public static Specification<Content> hasStatuses(List<Long> statuses) {
		return (root, query, cb) -> {
			var cleanStatuses = cleanList(statuses);
			return cleanStatuses != null ? root.get("status").in(cleanStatuses) : null;
		};
	}
	public static Specification<Content> hasReleaseFrom(LocalDate releaseFrom) {
		return (root, query, cb) ->
			releaseFrom != null
				? cb.greaterThanOrEqualTo(root.get("releaseDate"), releaseFrom)
				: null;
	}
	public static Specification<Content> hasReleaseTo(LocalDate releaseTo) {
		return (root, query, cb) ->
			releaseTo != null
				? cb.lessThanOrEqualTo(root.get("releaseDate"), releaseTo)
				: null;
	}
	public static Specification<Content> hasLanguageCodes(List<String> languageCodes) {
		return (root, query, cb) -> {
			var cleanLanguageCodes = cleanList(languageCodes);
			return cleanLanguageCodes != null ? root.get("languageCodes").in(cleanLanguageCodes) : null;
		};
	}
	public static Specification<Content> hasGenres(List<Long> genreIds) {
		return (root, query, cb) -> {
			var cleanGenreIds = cleanList(genreIds);
			return cleanGenreIds.isEmpty() ? root.join("genres", JoinType.LEFT).get("id").in(cleanGenreIds) : null;
		};
	}
	public static Specification<Content> hasCreatedAtFrom(LocalDateTime createdAtFrom) {
		return (root, query, cb) ->
			createdAtFrom != null
				? cb.lessThanOrEqualTo(root.get("createdAt"), createdAtFrom)
				: null;
	}
	public static Specification<Content> hasCreatedTo(LocalDateTime createdAtTo) {
		return (root, query, cb) ->
			createdAtTo != null
				? cb.greaterThanOrEqualTo(root.get("createdAt"), createdAtTo)
				: null;
	}
	public static Specification<Content> hasUpdatedAtFrom(LocalDateTime updatedAtFrom) {
		return (root, query, cb) ->
			updatedAtFrom != null
				? cb.lessThanOrEqualTo(root.get("updatedAt"), updatedAtFrom)
				: null;
	}
	public static Specification<Content> hasUpdatedAtTo(LocalDateTime updatedAtTo) {
		return (root, query, cb) ->
			updatedAtTo != null
				? cb.greaterThanOrEqualTo(root.get("updatedAt"), updatedAtTo)
				: null;
	}
	public static Specification<Content> hasActive(Boolean active) {
		return (root, query, cb) ->
			active != null
				? cb.equal(root.get("active"), active)
				: null;
	}

	public static Specification<Content> hasTranslationFilters(String title, String languageCode) {
		return (root, query, cb) -> {
			boolean hasTitle = title != null && !title.isBlank();
			boolean hasLang = languageCode != null && !languageCode.isBlank();

			if (!hasTitle && !hasLang) return null;

			Join<Content, ContentTranslation> join = root.join("translations");
			List<Predicate> predicates = new ArrayList<>();

			if (hasTitle) {
				predicates.add(cb.like(join.get("title"), "%" + title + "%"));
			}
			if (hasLang) {
				predicates.add(cb.equal(join.get("languageCode"), languageCode));
			}

			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}

	private static <T> List<T> cleanList(List<T> list) {
		if (list == null || list.isEmpty()) return null;
		return list.stream().filter(Objects::nonNull).distinct().toList();
	}
}
