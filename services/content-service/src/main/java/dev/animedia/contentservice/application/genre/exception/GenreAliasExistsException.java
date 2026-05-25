package dev.animedia.contentservice.application.genre.exception;

import dev.animedia.contentservice.domain.shared.exception.AppException;
import dev.animedia.contentservice.domain.shared.exception.AppExceptionStatus;

public class GenreAliasExistsException extends AppException {
	public GenreAliasExistsException(String alias) {
		super(AppExceptionStatus.NOT_FOUND, "genre.alias.exists", alias);
	}
}
