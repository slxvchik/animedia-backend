package dev.animedia.contentservice.app.exception;

import dev.animedia.contentservice.app.exception.common.AppErrorTranslationException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.animedia.contentservice.app.context.LocaleLanguageContext;
import dev.animedia.contentservice.app.dto.AppResponseDto;

import java.io.IOException;
import java.util.List;

@ControllerAdvice
public class AppExceptionHandler {

    private final AppExceptionMessageService appExceptionMessageService;

    @Autowired
    public AppExceptionHandler(AppExceptionMessageService appExceptionMessageService) {
        this.appExceptionMessageService = appExceptionMessageService;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppResponseDto<Object>> handleValidationError(MethodArgumentNotValidException exception) throws IOException {

        String languageCode = LocaleLanguageContext.getLocaleLanguageCode();

        List<FieldError> fieldErrors = exception.getFieldErrors();

        List<String> errorCodes = fieldErrors.stream().map(FieldError::getDefaultMessage).toList();

        try {
            List<String> errorMessages = appExceptionMessageService.getExceptionMessage(errorCodes, languageCode);
            AppResponseDto<Object> response = AppResponseDto.error(errorMessages);

            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);

        } catch (AppErrorTranslationException appErrorTranslationException) {

            AppResponseDto<Object> response = AppResponseDto.error(appErrorTranslationException.getMessage(languageCode));

            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
        }
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<AppResponseDto<Object>> handleAppError(AppException exception) throws IOException {

        String languageCode = LocaleLanguageContext.getLocaleLanguageCode();
        
        List<String> errorMessage = appExceptionMessageService.getExceptionMessage(exception.getCode(), languageCode);
        
        AppResponseDto<Object> response = AppResponseDto.error(errorMessage);

        return ResponseEntity
            .status(exception.getHttpStatus())
            .body(response);
    }

}
