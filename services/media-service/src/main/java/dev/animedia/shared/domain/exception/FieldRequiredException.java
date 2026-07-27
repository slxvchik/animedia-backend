package dev.animedia.shared.domain.exception;

import dev.animedia.shared.domain.appexception.AppException;
import dev.animedia.shared.domain.appexception.AppExceptionStatus;

public class FieldRequiredException extends AppException {
	public FieldRequiredException(String fieldName) {
		super(AppExceptionStatus.INVALID_ARGUMENT, "field.required.exception", fieldName);
	}
}
