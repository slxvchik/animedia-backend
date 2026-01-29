package dev.animedia.contentservice.genre.exception;

import org.springframework.http.HttpStatus;

import dev.animedia.contentservice.app.exception.AppException;

public class GenreTranslationIdNotFoundException extends AppException{
    public GenreTranslationIdNotFoundException() {
        super(HttpStatus.NOT_FOUND, "GENRE_TRANSLATION_NOT_FOUND");
    }
}
