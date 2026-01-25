package dev.animedia.contentservice.genre.exception;

import dev.animedia.contentservice.app.exception.AppException;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class GenreIdNotFoundException extends AppException {
    public GenreIdNotFoundException(String id) {
        super(HttpStatus.NOT_FOUND, "GENRE_ID_NOT_FOUND", Map.of("id", id));
    }
}
