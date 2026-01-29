package dev.animedia.contentservice.genre.exception;

import dev.animedia.contentservice.app.exception.AppException;
import org.springframework.http.HttpStatus;

public class GenreIdNotFoundException extends AppException {
    public GenreIdNotFoundException() {
        super(HttpStatus.NOT_FOUND, "GENRE_ID_NOT_FOUND");
    }
}
