package dev.animedia.contentservice.status.application.exception;

import dev.animedia.contentservice.shared.domain.appexception.AppException;
import dev.animedia.contentservice.shared.domain.appexception.AppExceptionStatus;

public class StatusAliasExistsException extends AppException {
    public StatusAliasExistsException(String alias) {
        super(AppExceptionStatus.ALREADY_EXISTS, "status.alias.exists.extra", alias);
    }
}
