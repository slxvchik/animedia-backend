package dev.animedia.contentservice.content.domain.exception;

import dev.animedia.contentservice.shared.domain.appexception.AppException;
import dev.animedia.contentservice.shared.domain.appexception.AppExceptionStatus;

public class ContentTypeRequiredException extends AppException {
    public ContentTypeRequiredException() {
        super(AppExceptionStatus.INVALID_ARGUMENT, "content.type.required");
    }
}
