package dev.animedia.languageservice.language.exception;

import dev.animedia.languageservice.app.exception.AppException;
import dev.animedia.languageservice.language.LanguageConstants;
import org.springframework.http.HttpStatus;

public class LanguageNameExistsException extends AppException {
	public LanguageNameExistsException() {
		super(HttpStatus.CONFLICT, LanguageConstants.LANGUAGE_NAME_EXISTS_MESSAGE);
	}
}
