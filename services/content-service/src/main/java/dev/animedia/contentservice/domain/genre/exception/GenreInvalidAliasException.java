package dev.animedia.contentservice.domain.genre.exception;


import dev.animedia.contentservice.domain.shared.exception.AppException;
import dev.animedia.contentservice.domain.shared.exception.AppExceptionStatus;

public class GenreInvalidAliasException extends AppException {
    public GenreInvalidAliasException() {
        super(AppExceptionStatus.INVALID_ARGUMENT, "GENRE_INVALID_ALIAS");
    }
}
