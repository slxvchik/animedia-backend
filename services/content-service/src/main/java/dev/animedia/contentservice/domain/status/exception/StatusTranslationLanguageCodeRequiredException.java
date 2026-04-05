package dev.animedia.contentservice.domain.status.exception;

import dev.animedia.contentservice.domain.shared.exception.AppException;
import dev.animedia.contentservice.domain.shared.exception.AppExceptionStatus;

public class StatusTranslationLanguageCodeRequiredException extends AppException {
    public StatusTranslationLanguageCodeRequiredException() {
        super(AppExceptionStatus.INVALID_ARGUMENT, "STATUS_TRANSLATION_LANGUAGE_CODE_REQUIRED");
    }
}
