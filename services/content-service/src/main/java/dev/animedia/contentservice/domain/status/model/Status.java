package dev.animedia.contentservice.domain.status.model;

import dev.animedia.contentservice.domain.status.exception.StatusAliasRequiredException;
import dev.animedia.contentservice.domain.status.exception.StatusInvalidAliasException;

import java.util.Set;
import java.util.regex.Pattern;

public class Status {
	private final long id;
	private String alias;
	private int sortOrder;
	private Set<StatusTranslation> translationSet;

	private final static Pattern ALIAS_PATTERN = Pattern.compile("^[a-z]{2,10}(?:-[a-z]{1,10}){0,8}$");

	public Status(long id, String alias, int sortOrder, Set<StatusTranslation> translationSet) {
		validateAlias(alias);
		this.id = id;
		this.alias = alias;
		setSortOrder(sortOrder);
		this.translationSet = translationSet;
	}

	public void update(String alias, int sortOrder, Set<StatusTranslation> translationSet) {
		validateAlias(alias);
		this.alias = alias;
		setSortOrder(sortOrder);
		this.translationSet = translationSet;
	}

	private void validateAlias(String alias) {
		if (alias == null || alias.isBlank()) throw new StatusAliasRequiredException();
		if (!ALIAS_PATTERN.matcher(alias).hasMatch()) throw new StatusInvalidAliasException();
	}

	private void setSortOrder(int sortOrder) {
		this.sortOrder = Math.max(sortOrder, 0);
	}

	public long getId() {
		return id;
	}

	public String getAlias() {
		return alias;
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public Set<StatusTranslation> getTranslationSet() {
		return translationSet;
	}
}
