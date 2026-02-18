package dev.animedia.contentservice.genre.exception;

import dev.animedia.contentservice.app.exception.AppException;
import dev.animedia.contentservice.genre.GenreConstants;
import org.springframework.http.HttpStatus;

public class GenreAliasExistsException extends AppException {
    public GenreAliasExistsException() {
        super(HttpStatus.BAD_REQUEST, GenreConstants.GENRE_ALIAS_EXISTS_MESSAGE);
    }
}
