package dev.animedia.contentservice.shared.domain.translation.exception;

import dev.animedia.contentservice.shared.domain.appexception.AppException;
import dev.animedia.contentservice.shared.domain.appexception.AppExceptionStatus;

public class LanguageCodePatternException extends AppException {
	public LanguageCodePatternException() {
		super(AppExceptionStatus.INVALID_ARGUMENT, "base_translation.language_code.pattern");
	}
}
