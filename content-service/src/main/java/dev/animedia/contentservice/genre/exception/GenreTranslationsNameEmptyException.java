package dev.animedia.contentservice.genre.exception;

import dev.animedia.contentservice.app.exception.AppException;
import org.springframework.http.HttpStatus;

public class GenreTranslationsNameEmptyException extends AppException {
    public GenreTranslationsNameEmptyException() {
        super(HttpStatus.BAD_REQUEST, "GENRE_TRANSLATIONS_NAME_EMPTY");
    }
}
