package dev.animedia.contentservice.app.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import dev.animedia.contentservice.app.context.LanguageLocaleContext;
import dev.animedia.contentservice.app.dto.AppResponseDto;

import java.io.IOException;

@ControllerAdvice
public class AppExceptionHandler {

    private final AppExceptionMessageService appExceptionMessageService;

    @Autowired
    public AppExceptionHandler(AppExceptionMessageService appExceptionMessageService) {
        this.appExceptionMessageService = appExceptionMessageService;
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<AppResponseDto<Object>> handleAppError(AppException e) throws IOException {
        String languageCode = LanguageLocaleContext.getLanguageLocaleCode();
        
        String errorMessage = appExceptionMessageService.getExceptionMessage(e.getCode(), languageCode, e.getParams());
        
        AppResponseDto<Object> response = AppResponseDto.error(errorMessage);

        return ResponseEntity
            .status(e.getHttpStatus())
            .body(response);
    }

    @ExceptionHandler(ErrorLocaleException.class)
    public ResponseEntity<AppResponseDto<Object>> handleLocaleError(ErrorLocaleException e) {
        String languageCode = LanguageLocaleContext.getLanguageLocaleCode();

        AppResponseDto<Object> response = AppResponseDto.error(e.getMessage(languageCode));

        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(response);
    }

}
