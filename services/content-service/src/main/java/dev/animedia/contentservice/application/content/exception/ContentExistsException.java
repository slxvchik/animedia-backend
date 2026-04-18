package dev.animedia.contentservice.application.content.exception;

import dev.animedia.contentservice.domain.shared.exception.AppException;
import dev.animedia.contentservice.domain.shared.exception.AppExceptionStatus;

public class ContentExistsException extends AppException {
    public ContentExistsException() {
        super(AppExceptionStatus.ALREADY_EXISTS, "CONTENT_EXISTS");
    }
}
