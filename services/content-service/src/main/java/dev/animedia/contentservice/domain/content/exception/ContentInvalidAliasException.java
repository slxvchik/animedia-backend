package dev.animedia.contentservice.domain.content.exception;

import dev.animedia.contentservice.domain.shared.appexception.AppException;
import dev.animedia.contentservice.domain.shared.appexception.AppExceptionStatus;

public class ContentInvalidAliasException extends AppException {
    public ContentInvalidAliasException() {
        super(AppExceptionStatus.INVALID_ARGUMENT, "content.invalid.alias");
    }
}
