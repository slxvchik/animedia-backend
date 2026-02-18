package dev.animedia.contentservice.genre.exception;

import dev.animedia.contentservice.app.exception.AppException;
import dev.animedia.contentservice.genre.GenreConstants;
import org.springframework.http.HttpStatus;

public class GenreTranslationExistsException extends AppException {
    public GenreTranslationExistsException() {
        super(HttpStatus.CONFLICT, GenreConstants.GENRE_TRANSLATION_EXISTS_MESSAGE);
    }
}
