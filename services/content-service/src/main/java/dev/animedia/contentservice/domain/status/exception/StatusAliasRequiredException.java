package dev.animedia.contentservice.domain.status.exception;

import dev.animedia.contentservice.domain.shared.exception.AppException;
import dev.animedia.contentservice.domain.shared.exception.AppExceptionStatus;

public class StatusAliasRequiredException extends AppException {
    public StatusAliasRequiredException() {
        super(AppExceptionStatus.INVALID_ARGUMENT, "status.alias.required");
    }
}
