package dev.animedia.contentservice.domain.shared.translation.model;

import dev.animedia.contentservice.domain.shared.translation.exception.LanguageCodeRequiredException;
import dev.animedia.contentservice.domain.shared.translation.exception.LanguageCodePatternException;

import java.util.Objects;
import java.util.regex.Pattern;

public abstract class BaseTranslation {

	private String languageCode;

	private static final Pattern CODE_PATTERN = Pattern.compile("^[a-z]{2}$");

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		validateLanguageCode(languageCode);
		this.languageCode = languageCode;
	}

	private void validateLanguageCode(String languageCode) {
		if (languageCode == null || languageCode.isBlank()) throw new LanguageCodeRequiredException();
		if (!CODE_PATTERN.matcher(languageCode).matches()) throw new LanguageCodePatternException();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (!(o instanceof BaseTranslation that)) return false;

		return Objects.equals(languageCode, that.languageCode);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(languageCode);
	}
}
