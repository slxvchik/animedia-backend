package dev.animedia.contentservice.infrastructure.persistence.content.exception;

import dev.animedia.contentservice.domain.shared.appexception.AppException;
import dev.animedia.contentservice.domain.shared.appexception.AppExceptionStatus;

public class ContentCreateException extends AppException {
	public ContentCreateException() {
		super(AppExceptionStatus.INTERNAL_ERROR, "content.create");
	}
}
