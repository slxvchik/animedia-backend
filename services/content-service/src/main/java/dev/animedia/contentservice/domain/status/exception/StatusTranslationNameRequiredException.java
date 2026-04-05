package dev.animedia.contentservice.domain.status.exception;

import dev.animedia.contentservice.domain.shared.exception.AppException;
import dev.animedia.contentservice.domain.shared.exception.AppExceptionStatus;

public class StatusTranslationNameRequiredException extends AppException {
    public StatusTranslationNameRequiredException() {
        super(AppExceptionStatus.INVALID_ARGUMENT, "STATUS_TRANSLATION_NAME_REQUIRED");
    }
}
