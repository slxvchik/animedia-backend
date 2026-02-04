package dev.animedia.contentservice.genre.exception;

import dev.animedia.contentservice.app.exception.AppException;
import dev.animedia.contentservice.genre.GenreConstants;
import org.springframework.http.HttpStatus;

public class GenreTranslationsExistsException extends AppException {
    public GenreTranslationsExistsException() {
        super(HttpStatus.CONFLICT, GenreConstants.GENRE_TRANSLATIONS_EXISTS_MESSAGE);
    }
}
