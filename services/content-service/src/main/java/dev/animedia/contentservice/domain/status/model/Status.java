package dev.animedia.contentservice.domain.status.model;

import dev.animedia.contentservice.domain.status.exception.StatusAliasRequiredException;
import dev.animedia.contentservice.domain.status.exception.StatusInvalidAliasException;

import java.util.*;
import java.util.regex.Pattern;

public class Status {
	private final UUID id;
	private final String alias;
	private int sortOrder;
	private boolean active;
	private final Set<StatusTranslation> translationSet = new HashSet<>();

	private static final Pattern ALIAS_PATTERN = Pattern.compile("^[a-z]{2,10}(?:-[a-z]{1,10}){0,8}$");

	public Status(UUID id, String alias, int sortOrder, boolean active, Set<StatusTranslation> translationSet) {
		validateAlias(alias);
		this.id = id;
		this.alias = alias;
		setSortOrder(sortOrder);
		this.active = active;
		setTranslationSet(translationSet);
	}

	public UUID getId() {
		return id;
	}

	public String getAlias() {
		return alias;
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public boolean getActive() {
		return active;
	}

	public Set<StatusTranslation> getTranslationSet() {
		return Collections.unmodifiableSet(translationSet);
	}

	public void update(int sortOrder, boolean active, Set<StatusTranslation> translationSet) {
		setSortOrder(sortOrder);
		this.active = active;
		setTranslationSet(translationSet);
	}

	private void validateAlias(String alias) {
		if (alias == null || alias.isBlank()) throw new StatusAliasRequiredException();
		if (!ALIAS_PATTERN.matcher(alias).matches()) throw new StatusInvalidAliasException();
	}

	private void setTranslationSet(Set<StatusTranslation> translationSet) {
		this.translationSet.clear();
		this.translationSet.addAll(translationSet);
	}

	private void setSortOrder(int sortOrder) {
		this.sortOrder = Math.max(sortOrder, 0);
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Status status)) return false;
        return alias.equals(status.alias);
	}

	@Override
	public int hashCode() {
		return Objects.hash(alias);
	}
}
