package dev.animedia.contentservice.genre.exception;

import dev.animedia.contentservice.app.exception.AppException;
import org.springframework.http.HttpStatus;

public class GenreTranslationsAlreadyExistsException extends AppException {
    public GenreTranslationsAlreadyExistsException() {
        super(HttpStatus.CONFLICT, "GENRE_TRANSLATIONS_ALREADY_EXISTS");
    }
}
