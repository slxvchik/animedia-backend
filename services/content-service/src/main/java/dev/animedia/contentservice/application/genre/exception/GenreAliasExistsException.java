package dev.animedia.contentservice.application.genre.exception;

import dev.animedia.contentservice.domain.shared.appexception.AppException;
import dev.animedia.contentservice.domain.shared.appexception.AppExceptionStatus;

public class GenreAliasExistsException extends AppException {
	public GenreAliasExistsException(String alias) {
		super(AppExceptionStatus.NOT_FOUND, "genre.alias.exists.extra", alias);
	}
}
