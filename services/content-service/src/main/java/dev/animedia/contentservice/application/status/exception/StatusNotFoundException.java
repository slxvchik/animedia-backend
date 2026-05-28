package dev.animedia.contentservice.application.status.exception;

import dev.animedia.contentservice.domain.shared.exception.AppException;
import dev.animedia.contentservice.domain.shared.exception.AppExceptionStatus;

public class StatusNotFoundException extends AppException {
    public StatusNotFoundException() {
        super(AppExceptionStatus.NOT_FOUND, "status.not_found");
    }
    public StatusNotFoundException(Long statusId) {
        super(AppExceptionStatus.NOT_FOUND, "status.not_found.admin", statusId.toString());
    }
}
