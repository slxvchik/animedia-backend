package dev.animedia.contentservice.genre.exception;

import dev.animedia.contentservice.app.exception.AppException;
import dev.animedia.contentservice.genre.GenreConstants;
import org.springframework.http.HttpStatus;

public class GenreTranslationsNotFoundException extends AppException{
    public GenreTranslationsNotFoundException() {
        super(HttpStatus.NOT_FOUND, GenreConstants.GENRE_TRANSLATIONS_NOT_FOUND_MESSAGE);
    }
}
