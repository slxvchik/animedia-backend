package dev.animedia.contentservice.infrastructure.persistence.content.exception;

import dev.animedia.contentservice.domain.shared.exception.AppException;
import dev.animedia.contentservice.domain.shared.exception.AppExceptionStatus;

public class ContentCreateException extends AppException {
	public ContentCreateException() {
		super(AppExceptionStatus.INTERNAL_ERROR, "CONTENT_CREATE_EXCEPTION");
	}
}
