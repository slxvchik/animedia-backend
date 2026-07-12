package dev.animedia.contentservice.shared.domain.slugalias;

import dev.animedia.contentservice.shared.domain.appexception.AppException;
import dev.animedia.contentservice.shared.domain.appexception.AppExceptionStatus;

public class SlugAliasPatternException extends AppException {
	public SlugAliasPatternException(String value) {
		super(AppExceptionStatus.INVALID_ARGUMENT, "slug_alias.invalid.pattern", value);
	}
}
