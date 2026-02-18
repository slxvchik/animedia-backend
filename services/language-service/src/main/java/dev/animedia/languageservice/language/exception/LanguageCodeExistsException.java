package dev.animedia.languageservice.language.exception;

import dev.animedia.languageservice.app.exception.AppException;
import dev.animedia.languageservice.language.LanguageConstants;
import io.grpc.Status;

public class LanguageCodeExistsException extends AppException {
	public LanguageCodeExistsException() {
		super(Status.Code.ALREADY_EXISTS, LanguageConstants.LANGUAGE_CODE_EXISTS_MESSAGE);
	}
}
