package dev.animedia.contentservice.genre.exception;

import dev.animedia.contentservice.app.exception.AppException;
import org.springframework.http.HttpStatus;

public class GenreTranslationNameEmptyException extends AppException {
    public GenreTranslationNameEmptyException() {
        super(HttpStatus.BAD_REQUEST, "GENRE_TRANSLATION_NAME_EMPTY");
    }
}
