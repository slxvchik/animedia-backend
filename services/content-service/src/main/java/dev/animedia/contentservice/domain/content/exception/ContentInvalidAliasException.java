package dev.animedia.contentservice.domain.content.exception;

import dev.animedia.contentservice.domain.shared.exception.AppException;
import dev.animedia.contentservice.domain.shared.exception.AppExceptionStatus;

public class ContentInvalidAliasException extends AppException {
    public ContentInvalidAliasException() {
        super(AppExceptionStatus.INVALID_ARGUMENT, "CONTENT_INVALID_ALIAS");
    }
}
