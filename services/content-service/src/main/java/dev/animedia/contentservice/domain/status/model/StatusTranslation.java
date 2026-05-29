package dev.animedia.contentservice.domain.status.model;

import dev.animedia.contentservice.domain.shared.translation.model.BaseTranslation;
import dev.animedia.contentservice.domain.status.exception.StatusTranslationNameRequiredException;

import java.util.UUID;

public class StatusTranslation extends BaseTranslation {
	private final UUID id;
	private String name;

	public StatusTranslation(UUID id, String languageCode, String name) {
		validateName(name);
		this.id = id;
		setLanguageCode(languageCode);
		this.name = name;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void update(String name) {
		validateName(name);
		this.name = name;
	}

	private void validateName(String name) {
		if (name == null || name.isBlank()) throw new StatusTranslationNameRequiredException();
	}
}
