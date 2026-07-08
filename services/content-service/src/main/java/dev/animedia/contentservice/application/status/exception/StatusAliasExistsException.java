package dev.animedia.contentservice.application.status.exception;

import dev.animedia.contentservice.domain.shared.appexception.AppException;
import dev.animedia.contentservice.domain.shared.appexception.AppExceptionStatus;

public class StatusAliasExistsException extends AppException {
    public StatusAliasExistsException(String alias) {
        super(AppExceptionStatus.ALREADY_EXISTS, "status.alias.exists.extra", alias);
    }
}
