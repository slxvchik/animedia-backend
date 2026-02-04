package dev.animedia.contentservice.genre.exception;

import dev.animedia.contentservice.app.exception.AppException;
import dev.animedia.contentservice.genre.GenreConstants;
import org.springframework.http.HttpStatus;

public class GenreNotFoundException extends AppException {
    public GenreNotFoundException() {
        super(HttpStatus.NOT_FOUND, GenreConstants.GENRE_NOT_FOUND_MESSAGE);
    }
}
