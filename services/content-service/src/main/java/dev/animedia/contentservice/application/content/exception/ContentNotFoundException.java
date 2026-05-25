package dev.animedia.contentservice.application.content.exception;

import dev.animedia.contentservice.domain.shared.exception.AppException;
import dev.animedia.contentservice.domain.shared.exception.AppExceptionStatus;

public class ContentNotFoundException extends AppException {
    public ContentNotFoundException() {
        super(AppExceptionStatus.NOT_FOUND, "content.not_found");
    }
}
