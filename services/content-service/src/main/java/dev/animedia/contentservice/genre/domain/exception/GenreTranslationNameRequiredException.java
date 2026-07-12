package dev.animedia.contentservice.genre.domain.exception;

import dev.animedia.contentservice.shared.domain.exception.AppException;
import dev.animedia.contentservice.shared.domain.exception.AppExceptionStatus;

public class GenreTranslationNameRequiredException extends AppException {
    public GenreTranslationNameRequiredException() {
        super(AppExceptionStatus.INVALID_ARGUMENT, "genre.translation.name.required");
    }
}
