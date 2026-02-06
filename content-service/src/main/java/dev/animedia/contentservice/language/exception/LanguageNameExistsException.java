package dev.animedia.contentservice.language.exception;

import dev.animedia.contentservice.app.exception.AppException;
import dev.animedia.contentservice.language.LanguageConstants;
import org.springframework.http.HttpStatus;

public class LanguageNameExistsException extends AppException {
	public LanguageNameExistsException() {
		super(HttpStatus.CONFLICT, LanguageConstants.LANGUAGE_NAME_EXISTS_MESSAGE);
	}
}
