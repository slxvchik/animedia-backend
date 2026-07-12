package dev.animedia.contentservice.status.domain.model;

import dev.animedia.contentservice.shared.domain.slugalias.SlugAlias;

import java.util.*;

public class Status {
	private final UUID id;
	private final SlugAlias alias;
	private int sortOrder;
	private boolean active;
	private final Set<StatusTranslation> translationSet = new HashSet<>();

	public Status(UUID id, String alias, int sortOrder, boolean active, Set<StatusTranslation> translationSet) {
		this.id = id;
		this.alias = new SlugAlias(alias);
		setSortOrder(sortOrder);
		this.active = active;
		setTranslationSet(translationSet);
	}

	public UUID getId() {
		return id;
	}

	public String getAlias() {
		return alias.getValue();
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
