package dev.animedia.contentservice.status.domain.exception;

import dev.animedia.contentservice.shared.domain.exception.AppException;
import dev.animedia.contentservice.shared.domain.exception.AppExceptionStatus;

public class StatusInvalidAliasException extends AppException {
    public StatusInvalidAliasException() {
        super(AppExceptionStatus.INVALID_ARGUMENT, "status.invalid.alias");
    }
}
