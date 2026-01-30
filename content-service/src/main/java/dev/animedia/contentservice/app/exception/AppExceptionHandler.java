package dev.animedia.contentservice.app.exception;

import dev.animedia.contentservice.app.exception.common.AppErrorTranslationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.animedia.contentservice.app.context.LocaleLanguageContext;
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
        String languageCode = LocaleLanguageContext.getLocaleLanguageCode();
        
        String errorMessage = appExceptionMessageService.getExceptionMessage(e.getCode(), languageCode);
        
        AppResponseDto<Object> response = AppResponseDto.error(errorMessage);

        return ResponseEntity
            .status(e.getHttpStatus())
            .body(response);
    }

    @ExceptionHandler(AppErrorTranslationException.class)
    public ResponseEntity<AppResponseDto<Object>> handleLocaleError(AppErrorTranslationException e) {
        String languageCode = LocaleLanguageContext.getLocaleLanguageCode();

        AppResponseDto<Object> response = AppResponseDto.error(e.getMessage(languageCode));

        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(response);
    }

}
