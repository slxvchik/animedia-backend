package dev.animedia.contentservice.domain.content.exception;

import dev.animedia.contentservice.domain.shared.appexception.AppException;
import dev.animedia.contentservice.domain.shared.appexception.AppExceptionStatus;

public class ContentStatusRequiredException extends AppException {
    public ContentStatusRequiredException() {
        super(AppExceptionStatus.INVALID_ARGUMENT, "content.status.required");
    }
}
