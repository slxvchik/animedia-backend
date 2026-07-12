package dev.animedia.contentservice.status.domain.model;

import dev.animedia.contentservice.shared.domain.exception.FieldRequiredException;
import dev.animedia.contentservice.shared.domain.translation.model.BaseTranslation;

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
		if (name == null || name.isBlank()) throw new FieldRequiredException("status translation: name");
	}
}
