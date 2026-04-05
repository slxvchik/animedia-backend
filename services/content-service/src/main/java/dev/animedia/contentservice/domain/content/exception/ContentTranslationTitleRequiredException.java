package dev.animedia.contentservice.domain.content.exception;

import dev.animedia.contentservice.domain.shared.exception.AppException;
import dev.animedia.contentservice.domain.shared.exception.AppExceptionStatus;

public class ContentTranslationTitleRequiredException extends AppException {
    public ContentTranslationTitleRequiredException() {
        super(AppExceptionStatus.INVALID_ARGUMENT, "CONTENT_TRANSLATION_TITLE_REQUIRED");
    }
}
