package dev.animedia.contentservice.shared.presentation.grpc.exception;

import dev.animedia.contentservice.shared.domain.appexception.AppException;
import dev.animedia.contentservice.shared.domain.appexception.AppExceptionStatus;

public class SortFieldNotAllowedException extends AppException {
	public SortFieldNotAllowedException(
		String field
	) {
		super(AppExceptionStatus.INVALID_ARGUMENT, "sort.field.not_allowed.extra", field);
	}
}