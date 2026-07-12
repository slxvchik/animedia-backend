package dev.animedia.contentservice.content.domain.exception;

import dev.animedia.contentservice.shared.domain.appexception.AppException;
import dev.animedia.contentservice.shared.domain.appexception.AppExceptionStatus;

public class ContentTranslationTitleRequiredException extends AppException {
    public ContentTranslationTitleRequiredException() {
        super(AppExceptionStatus.INVALID_ARGUMENT, "content.translation.title.required");
    }
}
