package dev.animedia.video.application.exception;

import dev.animedia.shared.domain.appexception.AppException;
import dev.animedia.shared.domain.appexception.AppExceptionStatus;

public class InvalidVideoFileTypeException extends AppException {
	public InvalidVideoFileTypeException() {
		super(AppExceptionStatus.INTERNAL_ERROR, "video.invalid_file_type");
	}
}
