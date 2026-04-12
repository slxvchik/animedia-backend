package dev.animedia.contentservice.application.status.exception;

import dev.animedia.contentservice.domain.shared.exception.AppException;
import dev.animedia.contentservice.domain.shared.exception.AppExceptionStatus;

public class StatusAliasExistsException extends AppException {
    public StatusAliasExistsException() {
        super(AppExceptionStatus.ALREADY_EXISTS, "STATUS_ALIAS_EXISTS");
    }
}
