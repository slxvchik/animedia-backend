package dev.animedia.contentservice.status.domain.exception;

import dev.animedia.contentservice.shared.domain.exception.AppException;
import dev.animedia.contentservice.shared.domain.exception.AppExceptionStatus;

public class StatusTranslationNameRequiredException extends AppException {
    public StatusTranslationNameRequiredException() {
        super(AppExceptionStatus.INVALID_ARGUMENT, "status.translation.name.required");
    }
}
