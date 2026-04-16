package dev.animedia.contentservice.application.genre.exception;

import dev.animedia.contentservice.domain.shared.exception.AppException;
import dev.animedia.contentservice.domain.shared.exception.AppExceptionStatus;

public class GenreAliasExistsException extends AppException {
	public GenreAliasExistsException() {
		super(AppExceptionStatus.NOT_FOUND, "GENRE_ALIAS_EXISTS");
	}
}
