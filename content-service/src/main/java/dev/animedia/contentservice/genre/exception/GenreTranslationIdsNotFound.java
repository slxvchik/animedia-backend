package dev.animedia.contentservice.genre.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

import dev.animedia.contentservice.app.exception.AppException;

public class GenreTranslationIdsNotFound extends AppException {
    public GenreTranslationIdsNotFound(List<String> genreIds) {
        super(HttpStatus.NOT_FOUND, "GENRE_TRANSLATIONS_NOT_FOUND");
    }
}
