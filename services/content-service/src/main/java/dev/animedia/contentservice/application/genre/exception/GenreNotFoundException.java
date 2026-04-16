package dev.animedia.contentservice.application.genre.exception;

import dev.animedia.contentservice.domain.shared.exception.AppException;
import dev.animedia.contentservice.domain.shared.exception.AppExceptionStatus;

public class GenreNotFoundException extends AppException {
	public GenreNotFoundException() {
		super(AppExceptionStatus.NOT_FOUND, "GENRE_NOT_FOUND");
	}
}
