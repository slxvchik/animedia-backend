package dev.animedia.contentservice.domain.status.model;

import dev.animedia.contentservice.domain.genre.model.GenreTranslation;
import dev.animedia.contentservice.domain.shared.model.BaseEntity;
import dev.animedia.contentservice.domain.status.exception.StatusAliasRequiredException;
import dev.animedia.contentservice.domain.status.exception.StatusInvalidAliasException;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class Status extends BaseEntity<Long> {
	private String alias;
	private int sortOrder;
	private final Set<StatusTranslation> translationSet = new HashSet<>();

	private static final Pattern ALIAS_PATTERN = Pattern.compile("^[a-z]{2,10}(?:-[a-z]{1,10}){0,8}$");

	public Status(Long id, String alias, int sortOrder, Set<StatusTranslation> translationSet) {
		validateAlias(alias);
		this.id = id;
		this.alias = alias;
		setSortOrder(sortOrder);
		setTranslationSet(translationSet);
	}

	public void update(String alias, int sortOrder, Set<StatusTranslation> translationSet) {
		validateAlias(alias);
		this.alias = alias;
		setSortOrder(sortOrder);
		setTranslationSet(translationSet);
	}

	private void validateAlias(String alias) {
		if (alias == null || alias.isBlank()) throw new StatusAliasRequiredException();
		if (!ALIAS_PATTERN.matcher(alias).hasMatch()) throw new StatusInvalidAliasException();
	}

	public void removeTranslation(Long id) {
		this.translationSet.removeIf(translation -> translation.getId().equals(id));
	}

	private void setTranslationSet(Set<StatusTranslation> translationSet) {
		if (translationSet != null) {
			this.translationSet.retainAll(translationSet);
			this.translationSet.addAll(translationSet);
		} else {
			this.translationSet.clear();
		}
	}

	private void setSortOrder(int sortOrder) {
		this.sortOrder = Math.max(sortOrder, 0);
	}

	public String getAlias() {
		return alias;
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public Set<StatusTranslation> getTranslationSet() {
		return Collections.unmodifiableSet(translationSet);
	}
}
