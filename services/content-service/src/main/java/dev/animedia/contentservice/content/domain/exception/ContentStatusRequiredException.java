package dev.animedia.contentservice.content.domain.exception;

import dev.animedia.contentservice.shared.domain.appexception.AppException;
import dev.animedia.contentservice.shared.domain.appexception.AppExceptionStatus;

public class ContentStatusRequiredException extends AppException {
    public ContentStatusRequiredException() {
        super(AppExceptionStatus.INVALID_ARGUMENT, "content.statusId.required");
    }
}
