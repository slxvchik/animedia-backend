package dev.animedia.contentservice.domain.genre.exception;

import dev.animedia.contentservice.domain.shared.exception.AppException;
import dev.animedia.contentservice.domain.shared.exception.AppExceptionStatus;

public class GenreTranslationLanguageCodeRequiredException extends AppException {
    public GenreTranslationLanguageCodeRequiredException() {
        super(AppExceptionStatus.INVALID_ARGUMENT, "GENRE_TRANSLATION_LANGUAGE_CODE_REQUIRED");
    }
}
