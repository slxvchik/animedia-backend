package dev.animedia.contentservice.language.exception;

import dev.animedia.contentservice.app.exception.AppException;
import org.springframework.http.HttpStatus;

public class LanguageCodesNotFoundException extends AppException {

    public LanguageCodesNotFoundException() {
        super(HttpStatus.NOT_FOUND, "LANGUAGE_CODES_NOT_FOUND");
    }
    
}
