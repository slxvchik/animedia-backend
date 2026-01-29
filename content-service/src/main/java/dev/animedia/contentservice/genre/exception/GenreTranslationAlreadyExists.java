package dev.animedia.contentservice.genre.exception;

import dev.animedia.contentservice.app.exception.AppException;
import org.springframework.http.HttpStatus;

public class GenreTranslationAlreadyExists extends AppException {
    public GenreTranslationAlreadyExists() {
        super(HttpStatus.CONFLICT, "GENRE_TRANSLATION_ALREADY_EXISTS");
    }
}
