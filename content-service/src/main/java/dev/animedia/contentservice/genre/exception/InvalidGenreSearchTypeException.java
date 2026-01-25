package dev.animedia.contentservice.genre.exception;

import dev.animedia.contentservice.app.exception.AppException;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class InvalidGenreSearchTypeException extends AppException {
    public InvalidGenreSearchTypeException() {
        super(HttpStatus.BAD_REQUEST, "GENRE_INVALID_SEARCH_TYPE", Map.of());
    }
}
