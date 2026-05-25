package dev.animedia.contentservice.domain.genre.exception;

import dev.animedia.contentservice.domain.shared.exception.AppException;
import dev.animedia.contentservice.domain.shared.exception.AppExceptionStatus;

public class GenreTranslationNameRequiredException extends AppException {
    public GenreTranslationNameRequiredException() {
        super(AppExceptionStatus.INVALID_ARGUMENT, "genre.translation.name.required");
    }
}
