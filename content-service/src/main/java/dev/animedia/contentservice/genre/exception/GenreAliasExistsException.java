package dev.animedia.contentservice.genre.exception;

import dev.animedia.contentservice.app.exception.AppException;
import org.springframework.http.HttpStatus;

public class GenreAliasExistsException extends AppException {
    public GenreAliasExistsException() {
        super(HttpStatus.BAD_REQUEST, "GENRE_ALIAS_EXISTS");
    }
}
