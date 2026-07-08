package dev.animedia.contentservice.domain.content.exception;

import dev.animedia.contentservice.domain.shared.appexception.AppException;
import dev.animedia.contentservice.domain.shared.appexception.AppExceptionStatus;

public class ContentTranslationTitleRequiredException extends AppException {
    public ContentTranslationTitleRequiredException() {
        super(AppExceptionStatus.INVALID_ARGUMENT, "content.translation.title.required");
    }
}
