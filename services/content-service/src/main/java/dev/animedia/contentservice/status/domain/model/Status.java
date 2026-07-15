package dev.animedia.contentservice.status.domain.model;

import dev.animedia.contentservice.shared.domain.slugalias.SlugAlias;

import java.util.*;

public class Status {
	private final UUID id;
	private final SlugAlias alias;
	private Integer sortOrder;
	private Boolean active;
	private final Set<StatusTranslation> translations = new HashSet<>();

	public Status(UUID id, String alias, Integer sortOrder, Boolean active, Set<StatusTranslation> translations) {
		this.id = id;
		this.alias = new SlugAlias(alias);
		setSortOrder(sortOrder);
		setActive(active);
		setTranslations(translations);
	}

	public UUID getId() {
		return id;
	}

	public String getAlias() {
		return alias.getValue();
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public Boolean getActive() {
		return active;
	}

	public Set<StatusTranslation> getTranslations() {
		return Collections.unmodifiableSet(translations);
	}

	public void update(Integer sortOrder, Boolean active, Set<StatusTranslation> translationSet) {
		setSortOrder(sortOrder);
		setActive(active);
		setTranslations(translationSet);
	}

	private void setTranslations(Set<StatusTranslation> translations) {
		this.translations.clear();
		this.translations.addAll(translations);
	}

	private void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder != null ? Math.max(sortOrder, 0) : 0;
	}

	private void setActive(Boolean active) {
		this.active = active != null && active;
	}
}
