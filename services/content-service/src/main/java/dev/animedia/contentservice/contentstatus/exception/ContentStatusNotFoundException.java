package dev.animedia.contentservice.contentstatus.exception;

import dev.animedia.contentservice.app.exception.AppException;
import dev.animedia.contentservice.contentstatus.ContentStatusConstants;
import org.springframework.http.HttpStatus;

public class ContentStatusNotFoundException extends AppException {
	public ContentStatusNotFoundException() {
		super(HttpStatus.CONFLICT, ContentStatusConstants.CONTENT_STATUS_NOT_FOUND_MESSAGE);
	}
}
