package dev.animedia.contentservice.genre.exception;

import dev.animedia.contentservice.app.exception.AppException;
import dev.animedia.contentservice.genre.GenreConstants;
import org.springframework.http.HttpStatus;

public class GenresNotFoundException extends AppException {
    public GenresNotFoundException() {
        super(HttpStatus.NOT_FOUND, GenreConstants.GENRES_NOT_FOUND_MESSAGE);
    }
}
