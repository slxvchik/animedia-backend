package dev.animedia.contentservice.domain.shared.translation.exception;

import dev.animedia.contentservice.domain.shared.appexception.AppException;
import dev.animedia.contentservice.domain.shared.appexception.AppExceptionStatus;

public class LanguageCodeRequiredException extends AppException {
	public LanguageCodeRequiredException() {
		super(AppExceptionStatus.INVALID_ARGUMENT, "base_translation.language_code.required");
	}
}
