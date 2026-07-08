package dev.animedia.contentservice.presentation.grpc.shared.exception;

import dev.animedia.contentservice.domain.shared.appexception.AppException;
import dev.animedia.contentservice.domain.shared.appexception.AppExceptionStatus;

public class SortFieldNotAllowedException extends AppException {
	public SortFieldNotAllowedException(
		String field
	) {
		super(AppExceptionStatus.INVALID_ARGUMENT, "sort.field.not_allowed.extra", field);
	}
}