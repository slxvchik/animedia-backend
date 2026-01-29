package dev.animedia.contentservice.genre.exception;

import dev.animedia.contentservice.app.exception.AppException;
import org.springframework.http.HttpStatus;

public class GenreNotFoundException extends AppException {
    public GenreNotFoundException() {
        super(HttpStatus.NOT_FOUND, "GENRE_NOT_FOUND");
    }
}
