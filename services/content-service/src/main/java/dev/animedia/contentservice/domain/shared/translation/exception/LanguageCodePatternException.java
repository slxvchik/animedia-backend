package dev.animedia.contentservice.domain.shared.translation.exception;

import dev.animedia.contentservice.domain.shared.exception.AppException;
import dev.animedia.contentservice.domain.shared.exception.AppExceptionStatus;

public class LanguageCodePatternException extends AppException {
	public LanguageCodePatternException() {
		super(AppExceptionStatus.INVALID_ARGUMENT, "base_translation.language_code.pattern");
	}
}
