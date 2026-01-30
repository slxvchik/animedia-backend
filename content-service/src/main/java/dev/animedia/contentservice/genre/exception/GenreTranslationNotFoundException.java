package dev.animedia.contentservice.genre.exception;

import org.springframework.http.HttpStatus;

import dev.animedia.contentservice.app.exception.AppException;

public class GenreTranslationNotFoundException extends AppException{
    public GenreTranslationNotFoundException() {
        super(HttpStatus.NOT_FOUND, "GENRE_TRANSLATION_NOT_FOUND");
    }
}
