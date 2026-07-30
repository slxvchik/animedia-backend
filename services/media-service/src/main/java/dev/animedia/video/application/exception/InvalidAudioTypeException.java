package dev.animedia.video.application.exception;

import dev.animedia.shared.domain.appexception.AppException;
import dev.animedia.shared.domain.appexception.AppExceptionStatus;

public class InvalidAudioTypeException extends AppException {
	public InvalidAudioTypeException(String filename) {
		super(AppExceptionStatus.INVALID_ARGUMENT, "video.invalid_audio_type", filename);
	}
}
