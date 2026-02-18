package dev.animedia.contentservice.contentstatus.exception;

import dev.animedia.contentservice.app.exception.AppException;
import dev.animedia.contentservice.contentstatus.ContentStatusConstants;
import org.springframework.http.HttpStatus;

public class ContentStatusAliasExistsException extends AppException {
	public ContentStatusAliasExistsException() {
		super(HttpStatus.CONFLICT, ContentStatusConstants.CONTENT_STATUS_ALIAS_EXISTS_MESSAGE);
	}
}
