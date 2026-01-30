package dev.animedia.contentservice.genre.exception;

import dev.animedia.contentservice.app.exception.AppException;
import org.springframework.http.HttpStatus;

public class GenreInvalidSearchTypeException extends AppException {
    public GenreInvalidSearchTypeException() {
        super(HttpStatus.BAD_REQUEST, "GENRE_INVALID_SEARCH_TYPE");
    }
}
