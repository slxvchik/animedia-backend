package dev.animedia.contentservice.genre.exception;

import dev.animedia.contentservice.app.exception.AppException;
import dev.animedia.contentservice.genre.GenreConstants;
import org.springframework.http.HttpStatus;

public class GenreSearchBatchSizeLimitException extends AppException {
    public GenreSearchBatchSizeLimitException() {
        super(HttpStatus.BAD_REQUEST, GenreConstants.GENRE_SEARCH_BATCH_SIZE_LIMIT_MESSAGE);
    }
}
