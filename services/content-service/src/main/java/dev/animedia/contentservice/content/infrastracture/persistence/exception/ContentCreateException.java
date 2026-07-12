package dev.animedia.contentservice.content.infrastracture.persistence.exception;

import dev.animedia.contentservice.shared.domain.appexception.AppException;
import dev.animedia.contentservice.shared.domain.appexception.AppExceptionStatus;

public class ContentCreateException extends AppException {
	public ContentCreateException() {
		super(AppExceptionStatus.INTERNAL_ERROR, "content.create");
	}
}
