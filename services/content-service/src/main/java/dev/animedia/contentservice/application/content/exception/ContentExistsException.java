package dev.animedia.contentservice.application.content.exception;

import dev.animedia.contentservice.domain.content.model.ContentType;
import dev.animedia.contentservice.domain.shared.appexception.AppException;
import dev.animedia.contentservice.domain.shared.appexception.AppExceptionStatus;

public class ContentExistsException extends AppException {
    public ContentExistsException(String alias, ContentType type, Integer season) {
        super(AppExceptionStatus.ALREADY_EXISTS, "content.exists.extra", alias, type.name(), String.valueOf(season));
    }
}
