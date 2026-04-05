package dev.animedia.contentservice.domain.content.exception;

import dev.animedia.contentservice.domain.shared.exception.AppException;
import dev.animedia.contentservice.domain.shared.exception.AppExceptionStatus;

public class ContentTranslationLanguageCodeRequiredException extends AppException {
    public ContentTranslationLanguageCodeRequiredException() {
        super(AppExceptionStatus.INVALID_ARGUMENT, "CONTENT_TRANSLATION_LANGUAGE_CODE_REQUIRED");
    }
}
