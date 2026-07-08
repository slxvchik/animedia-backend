package dev.animedia.contentservice.domain.shared.exception;

import dev.animedia.contentservice.domain.shared.appexception.AppException;
import dev.animedia.contentservice.domain.shared.appexception.AppExceptionStatus;

public class FieldRequiredException extends AppException {
	public FieldRequiredException(String fieldName) {
		super(AppExceptionStatus.INVALID_ARGUMENT, "field.required.exception", fieldName);
	}
}
