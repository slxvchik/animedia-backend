package dev.animedia.contentservice.shared.domain.exception;

import dev.animedia.contentservice.shared.domain.appexception.AppException;
import dev.animedia.contentservice.shared.domain.appexception.AppExceptionStatus;

public class FieldRequiredException extends AppException {
	public FieldRequiredException(String fieldName) {
		super(AppExceptionStatus.INVALID_ARGUMENT, "field.required.exception", fieldName);
	}
}
