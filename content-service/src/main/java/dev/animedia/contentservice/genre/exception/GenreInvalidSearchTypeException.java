package dev.animedia.contentservice.genre.exception;

import dev.animedia.contentservice.app.exception.AppException;
import dev.animedia.contentservice.genre.GenreConstants;
import org.springframework.http.HttpStatus;

public class GenreInvalidSearchTypeException extends AppException {
    public GenreInvalidSearchTypeException() {
        super(HttpStatus.BAD_REQUEST, GenreConstants.GENRE_INVALID_SEARCH_TYPE_MESSAGE);
    }
}
