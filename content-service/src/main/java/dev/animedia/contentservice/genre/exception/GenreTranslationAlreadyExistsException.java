package dev.animedia.contentservice.genre.exception;

import dev.animedia.contentservice.app.exception.AppException;
import org.springframework.http.HttpStatus;

public class GenreTranslationAlreadyExistsException extends AppException {
    public GenreTranslationAlreadyExistsException() {
        super(HttpStatus.CONFLICT, "GENRE_TRANSLATION_ALREADY_EXISTS");
    }
}
