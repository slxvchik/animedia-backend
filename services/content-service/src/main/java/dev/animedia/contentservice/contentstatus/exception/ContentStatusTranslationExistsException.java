package dev.animedia.contentservice.contentstatus.exception;

import dev.animedia.contentservice.app.exception.AppException;
import dev.animedia.contentservice.contentstatus.ContentStatusConstants;
import org.springframework.http.HttpStatus;

public class ContentStatusTranslationExistsException extends AppException {
	public ContentStatusTranslationExistsException() {
		super(HttpStatus.CONFLICT, ContentStatusConstants.CONTENT_STATUS_TRANSLATION_EXISTS_MESSAGE);
	}
}
