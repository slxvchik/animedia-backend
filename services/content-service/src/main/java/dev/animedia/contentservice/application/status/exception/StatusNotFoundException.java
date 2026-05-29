package dev.animedia.contentservice.application.status.exception;

import dev.animedia.contentservice.domain.shared.exception.AppException;
import dev.animedia.contentservice.domain.shared.exception.AppExceptionStatus;

import java.util.UUID;

public class StatusNotFoundException extends AppException {
    public StatusNotFoundException() {
        super(AppExceptionStatus.NOT_FOUND, "status.not_found");
    }
    public StatusNotFoundException(UUID statusId) {
        super(AppExceptionStatus.NOT_FOUND, "status.not_found.extra", String.valueOf(statusId));
    }
}
