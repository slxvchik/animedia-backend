package dev.animedia.contentservice.status.domain.exception;

import dev.animedia.contentservice.shared.domain.exception.AppException;
import dev.animedia.contentservice.shared.domain.exception.AppExceptionStatus;

public class StatusAliasRequiredException extends AppException {
    public StatusAliasRequiredException() {
        super(AppExceptionStatus.INVALID_ARGUMENT, "statusId.alias.required");
    }
}
