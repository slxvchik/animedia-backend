package dev.animedia.contentservice.domain.shared.slugalias;

import dev.animedia.contentservice.domain.shared.appexception.AppException;
import dev.animedia.contentservice.domain.shared.appexception.AppExceptionStatus;

public class SlugAliasPatternException extends AppException {
	public SlugAliasPatternException(String value) {
		super(AppExceptionStatus.INVALID_ARGUMENT, "slug_alias.invalid.pattern", value);
	}
}
