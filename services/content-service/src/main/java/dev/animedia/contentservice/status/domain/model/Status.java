package dev.animedia.contentservice.status.domain.model;

import dev.animedia.contentservice.shared.domain.slugalias.SlugAlias;

import java.util.*;

public class Status {
	private final UUID id;
	private final SlugAlias alias;
	private int sortOrder;
	private boolean active;
	private final Set<StatusTranslation> translations = new HashSet<>();

	public Status(UUID id, String alias, int sortOrder, boolean active, Set<StatusTranslation> translations) {
		this.id = id;
		this.alias = new SlugAlias(alias);
		setSortOrder(sortOrder);
		this.active = active;
		setTranslations(translations);
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

	public Set<StatusTranslation> getTranslations() {
		return Collections.unmodifiableSet(translations);
	}

	public void update(int sortOrder, boolean active, Set<StatusTranslation> translationSet) {
		setSortOrder(sortOrder);
		this.active = active;
		setTranslations(translationSet);
	}

	private void setTranslations(Set<StatusTranslation> translations) {
		this.translations.clear();
		this.translations.addAll(translations);
	}

	private void setSortOrder(int sortOrder) {
		this.sortOrder = Math.max(sortOrder, 0);
	}
}
