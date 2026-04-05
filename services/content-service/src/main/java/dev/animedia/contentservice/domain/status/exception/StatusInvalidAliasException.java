package dev.animedia.contentservice.domain.status.exception;

import dev.animedia.contentservice.domain.shared.exception.AppException;
import dev.animedia.contentservice.domain.shared.exception.AppExceptionStatus;

public class StatusInvalidAliasException extends AppException {
    public StatusInvalidAliasException() {
        super(AppExceptionStatus.INVALID_ARGUMENT, "STATUS_INVALID_ALIAS");
    }
}
