package dev.animedia.video.domain.exception;

import dev.animedia.shared.domain.appexception.AppException;
import dev.animedia.shared.domain.appexception.AppExceptionStatus;

public class VideoRootPathDirectoryDoesNotExistException extends AppException {
	public VideoRootPathDirectoryDoesNotExistException() {
		super(AppExceptionStatus.INTERNAL_ERROR, "video.root_path.directory_does_not_exist");
	}
}
