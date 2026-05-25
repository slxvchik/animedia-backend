package dev.animedia.contentservice.domain.content.exception;

import dev.animedia.contentservice.domain.shared.exception.AppException;
import dev.animedia.contentservice.domain.shared.exception.AppExceptionStatus;

public class ContentTypeRequiredException extends AppException {
    public ContentTypeRequiredException() {
        super(AppExceptionStatus.INVALID_ARGUMENT, "content.type.required");
    }
}
