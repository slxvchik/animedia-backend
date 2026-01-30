package dev.animedia.contentservice.genre.exception;

import dev.animedia.contentservice.app.exception.AppException;
import org.springframework.http.HttpStatus;

public class GenresNotFoundException extends AppException {
    public GenresNotFoundException() {
        super(HttpStatus.NOT_FOUND, "GENRES_NOT_FOUND");
    }
}
