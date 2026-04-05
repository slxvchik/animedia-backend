package dev.animedia.contentservice.domain.genre.exception;

import dev.animedia.contentservice.domain.shared.exception.AppException;
import dev.animedia.contentservice.domain.shared.exception.AppExceptionStatus;

public class GenreAliasRequiredException extends AppException {
    public GenreAliasRequiredException() {
        super(AppExceptionStatus.INVALID_ARGUMENT, "GENRE_ALIAS_REQUIRED");
    }
}
