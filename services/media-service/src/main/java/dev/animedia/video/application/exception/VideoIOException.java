package dev.animedia.video.application.exception;

import dev.animedia.shared.domain.appexception.AppException;
import dev.animedia.shared.domain.appexception.AppExceptionStatus;

public class VideoIOException extends AppException {
	public VideoIOException() {
		super(AppExceptionStatus.INTERNAL_ERROR, "video.input_output");
	}
}
