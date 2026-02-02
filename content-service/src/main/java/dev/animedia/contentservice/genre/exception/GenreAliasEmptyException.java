package dev.animedia.contentservice.genre.exception;

import dev.animedia.contentservice.app.exception.AppException;
import org.springframework.http.HttpStatus;

public class GenreAliasEmptyException extends AppException {
    public GenreAliasEmptyException() {
        super(HttpStatus.BAD_REQUEST, "GENRE_ALIAS_EMPTY");
    }
}
