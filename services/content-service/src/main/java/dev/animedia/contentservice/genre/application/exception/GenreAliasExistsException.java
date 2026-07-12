package dev.animedia.contentservice.genre.application.exception;

import dev.animedia.contentservice.shared.domain.appexception.AppException;
import dev.animedia.contentservice.shared.domain.appexception.AppExceptionStatus;

public class GenreAliasExistsException extends AppException {
	public GenreAliasExistsException(String alias) {
		super(AppExceptionStatus.NOT_FOUND, "genre.alias.exists.extra", alias);
	}
}
