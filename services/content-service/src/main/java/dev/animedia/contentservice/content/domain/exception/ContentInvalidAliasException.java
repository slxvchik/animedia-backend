package dev.animedia.contentservice.content.domain.exception;

import dev.animedia.contentservice.shared.domain.appexception.AppException;
import dev.animedia.contentservice.shared.domain.appexception.AppExceptionStatus;

public class ContentInvalidAliasException extends AppException {
    public ContentInvalidAliasException() {
        super(AppExceptionStatus.INVALID_ARGUMENT, "content.invalid.alias");
    }
}
