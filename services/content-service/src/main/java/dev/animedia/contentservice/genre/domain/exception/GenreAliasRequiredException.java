package dev.animedia.contentservice.genre.domain.exception;

import dev.animedia.contentservice.shared.domain.exception.AppException;
import dev.animedia.contentservice.shared.domain.exception.AppExceptionStatus;

public class GenreAliasRequiredException extends AppException {
    public GenreAliasRequiredException() {
        super(AppExceptionStatus.INVALID_ARGUMENT, "genre.alias.required");
    }
}
