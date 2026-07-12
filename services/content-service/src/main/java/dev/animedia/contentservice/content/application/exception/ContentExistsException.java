package dev.animedia.contentservice.content.application.exception;

import dev.animedia.contentservice.content.domain.model.ContentType;
import dev.animedia.contentservice.shared.domain.appexception.AppException;
import dev.animedia.contentservice.shared.domain.appexception.AppExceptionStatus;

public class ContentExistsException extends AppException {
    public ContentExistsException(String alias, ContentType type, Integer season) {
        super(AppExceptionStatus.ALREADY_EXISTS, "content.exists.extra", alias, type.name(), String.valueOf(season));
    }
}
