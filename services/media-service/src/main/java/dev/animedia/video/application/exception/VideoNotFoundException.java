package dev.animedia.video.application.exception;

import dev.animedia.shared.domain.appexception.AppException;
import dev.animedia.shared.domain.appexception.AppExceptionStatus;

public class VideoNotFoundException extends AppException {
	public VideoNotFoundException() {
		super(AppExceptionStatus.NOT_FOUND, "video.not_found");
	}
}
