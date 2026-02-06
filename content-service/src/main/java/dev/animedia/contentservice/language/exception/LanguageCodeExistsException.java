package dev.animedia.contentservice.language.exception;

import dev.animedia.contentservice.app.exception.AppException;
import dev.animedia.contentservice.language.LanguageConstants;
import org.springframework.http.HttpStatus;

public class LanguageCodeExistsException extends AppException {
	public LanguageCodeExistsException() {
		super(HttpStatus.CONFLICT, LanguageConstants.LANGUAGE_CODE_EXISTS_MESSAGE);
	}
}
