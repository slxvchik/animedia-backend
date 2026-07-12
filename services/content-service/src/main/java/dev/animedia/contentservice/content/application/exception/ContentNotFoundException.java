package dev.animedia.contentservice.content.application.exception;

import dev.animedia.contentservice.shared.domain.appexception.AppException;
import dev.animedia.contentservice.shared.domain.appexception.AppExceptionStatus;

import java.util.UUID;

public class ContentNotFoundException extends AppException {
    public ContentNotFoundException() {
        super(AppExceptionStatus.NOT_FOUND, "content.not_found");
    }
    public ContentNotFoundException(UUID contentId) {
        super(AppExceptionStatus.NOT_FOUND, "content.not_found.extra", contentId.toString());
    }
}
