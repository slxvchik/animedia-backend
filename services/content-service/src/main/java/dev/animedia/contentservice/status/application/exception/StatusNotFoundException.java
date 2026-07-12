package dev.animedia.contentservice.status.application.exception;

import dev.animedia.contentservice.shared.domain.appexception.AppException;
import dev.animedia.contentservice.shared.domain.appexception.AppExceptionStatus;

import java.util.UUID;

public class StatusNotFoundException extends AppException {
    public StatusNotFoundException() {
        super(AppExceptionStatus.NOT_FOUND, "status.not_found");
    }
    public StatusNotFoundException(UUID statusId) {
        super(AppExceptionStatus.NOT_FOUND, "status.not_found.extra", String.valueOf(statusId));
    }
}
