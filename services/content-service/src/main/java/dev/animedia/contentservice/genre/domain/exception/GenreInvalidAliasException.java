package dev.animedia.contentservice.genre.domain.exception;


import dev.animedia.contentservice.shared.domain.exception.AppException;
import dev.animedia.contentservice.shared.domain.exception.AppExceptionStatus;

public class GenreInvalidAliasException extends AppException {
    public GenreInvalidAliasException() {
        super(AppExceptionStatus.INVALID_ARGUMENT, "genre.invalid.alias");
    }
}
