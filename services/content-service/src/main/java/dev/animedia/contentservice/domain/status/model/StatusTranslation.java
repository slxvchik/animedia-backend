package dev.animedia.contentservice.domain.status.model;

import dev.animedia.contentservice.domain.shared.model.BaseEntity;
import dev.animedia.contentservice.domain.status.exception.StatusTranslationLanguageCodeRequiredException;
import dev.animedia.contentservice.domain.status.exception.StatusTranslationNameRequiredException;

public class StatusTranslation extends BaseEntity<Long> {
	private final String languageCode;
	private String name;

	public StatusTranslation(Long id, String languageCode, String name) {
		validateLanguageCode(languageCode);
		validateName(name);
		this.id = id;
		this.languageCode = languageCode;
		this.name = name;
	}

	public void update(String name) {
		validateName(name);
		this.name = name;
	}

	private void validateLanguageCode(String languageCode) {
		if (languageCode == null || languageCode.isBlank()) throw new StatusTranslationLanguageCodeRequiredException();
	}

	private void validateName(String name) {
		if (name == null || name.isBlank()) throw new StatusTranslationNameRequiredException();
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public String getName() {
		return name;
	}
}
