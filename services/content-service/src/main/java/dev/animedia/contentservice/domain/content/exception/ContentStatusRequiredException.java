package dev.animedia.contentservice.domain.content.exception;

import dev.animedia.contentservice.domain.shared.exception.AppException;
import dev.animedia.contentservice.domain.shared.exception.AppExceptionStatus;

public class ContentStatusRequiredException extends AppException {
    public ContentStatusRequiredException() {
        super(AppExceptionStatus.INVALID_ARGUMENT, "CONTENT_STATUS_REQUIRED");
    }
}
