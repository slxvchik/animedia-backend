package dev.animedia.contentservice.domain.shared.model;

import java.util.Objects;

public abstract class BaseTranslationEntity {

	protected String languageCode;

	public String getLanguageCode() {
		return languageCode;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (!(o instanceof BaseTranslationEntity that)) return false;

		return Objects.equals(languageCode, that.languageCode);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(languageCode);
	}
}
