package dev.animedia.contentservice.contentstatus.service.impl;

import dev.animedia.contentservice.contentstatus.dto.request.ContentStatusSearchRequestDto;
import dev.animedia.contentservice.contentstatus.model.ContentStatus;
import dev.animedia.contentservice.contentstatus.model.ContentStatusTranslation;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.List;

public class FilterSpecification {

	private FilterSpecification() {}

	public static Specification<ContentStatus> getSearchSpecification(ContentStatusSearchRequestDto requestDto) {
		return Specification
			.where(byLanguageCodes(requestDto.languageCodes()))
			.and(byAliases(requestDto.aliases()))
			.and(byNames(requestDto.names()));

	}

	private static Specification<ContentStatus> byLanguageCodes(List<String> languageCodes) {
		return (root, query, criteriaBuilder) -> {

			if (languageCodes == null || languageCodes.isEmpty()) return null;

			Join<ContentStatus, ContentStatusTranslation> translationsJoin =
				root.join("content_status_translations", JoinType.LEFT);

			return translationsJoin.get("language_сode").in(languageCodes);
		};
	}

	private static Specification<ContentStatus> byNames(List<String> names) {
		return (root, query, criteriaBuilder) -> {

			if (names == null || names.isEmpty()) return null;

			Join<ContentStatus, ContentStatusTranslation> translationsJoin =
				root.join("content_status_translations", JoinType.LEFT);

			List<Predicate> predicates = names.stream()
				.filter(StringUtils::hasText)
				.map(name -> name.trim().toLowerCase())
				.map(name -> criteriaBuilder.like(
					criteriaBuilder.lower(translationsJoin.get("name")),
					"%" + name + "%"
				))
				.toList();

			if (predicates.isEmpty()) return null;

			return criteriaBuilder.or(predicates);
		};
	}

	private static Specification<ContentStatus> byAliases(List<String> aliases) {
		return (root, query, criteriaBuilder) -> {

			if (aliases == null || aliases.isEmpty()) return null;

			List<Predicate> predicates = aliases.stream()
				.filter(StringUtils::hasText)
				.map(alias -> alias.trim().toLowerCase())
				.map(alias -> criteriaBuilder.like(
					criteriaBuilder.lower(root.get("alias")),
					"%" + alias + "%"
				))
				.toList();

			if (predicates.isEmpty()) return null;

			return criteriaBuilder.or(predicates);
		};
	}
}
