package dev.animedia.languageservice.language.exception;

import dev.animedia.languageservice.app.exception.AppException;
import dev.animedia.languageservice.language.LanguageConstants;
import org.springframework.http.HttpStatus;

public class LanguageCodeExistsException extends AppException {
	public LanguageCodeExistsException() {
		super(HttpStatus.CONFLICT, LanguageConstants.LANGUAGE_CODE_EXISTS_MESSAGE);
	}
}
