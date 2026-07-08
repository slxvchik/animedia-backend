package dev.animedia.contentservice.application.content.exception;

import dev.animedia.contentservice.domain.shared.appexception.AppException;
import dev.animedia.contentservice.domain.shared.appexception.AppExceptionStatus;

import java.util.UUID;

public class ContentNotFoundException extends AppException {
    public ContentNotFoundException() {
        super(AppExceptionStatus.NOT_FOUND, "content.not_found");
    }
    public ContentNotFoundException(UUID contentId) {
        super(AppExceptionStatus.NOT_FOUND, "content.not_found.extra", contentId.toString());
    }
}
