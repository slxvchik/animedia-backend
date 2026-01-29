package dev.animedia.contentservice.genre.exception;

import org.springframework.http.HttpStatus;

import dev.animedia.contentservice.app.exception.AppException;

public class GenreTranslationInvalidFields extends AppException {
    public GenreTranslationInvalidFields() {
        super(HttpStatus.BAD_REQUEST, "GENRE_TRANSLATIONS_INVALID_FIELDS");
    }
}
