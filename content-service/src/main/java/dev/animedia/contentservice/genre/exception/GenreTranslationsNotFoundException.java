package dev.animedia.contentservice.genre.exception;

import dev.animedia.contentservice.app.exception.AppException;
import org.springframework.http.HttpStatus;

public class GenreTranslationsNotFoundException extends AppException{
    public GenreTranslationsNotFoundException() {
        super(HttpStatus.NOT_FOUND, "GENRE_TRANSLATIONS_NOT_FOUND");
    }
}
