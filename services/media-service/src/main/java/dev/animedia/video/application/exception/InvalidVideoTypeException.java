package dev.animedia.video.application.exception;

import dev.animedia.shared.domain.appexception.AppException;
import dev.animedia.shared.domain.appexception.AppExceptionStatus;

public class InvalidVideoTypeException extends AppException {
	public InvalidVideoTypeException(String filename) {
		super(AppExceptionStatus.INVALID_ARGUMENT, "video.invalid_video_type", filename);
	}
}
