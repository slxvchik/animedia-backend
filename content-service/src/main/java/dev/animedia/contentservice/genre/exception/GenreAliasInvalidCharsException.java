package dev.animedia.contentservice.genre.exception;

import dev.animedia.contentservice.app.exception.AppException;
import org.springframework.http.HttpStatus;

public class GenreAliasInvalidCharsException extends AppException {
    public GenreAliasInvalidCharsException() {
        super(HttpStatus.BAD_REQUEST, "GENRE_ALIAS_INVALID_CHARS");
    }
}
