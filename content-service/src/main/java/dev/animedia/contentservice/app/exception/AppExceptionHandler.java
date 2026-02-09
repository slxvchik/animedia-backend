package dev.animedia.contentservice.app.exception;

import dev.animedia.contentservice.app.exception.common.AppErrorTranslationException;
import jakarta.validation.ValidationException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.animedia.contentservice.app.context.LocaleLanguageContext;
import dev.animedia.contentservice.app.dto.AppResponseDto;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ControllerAdvice
public class AppExceptionHandler {

    private final AppExceptionMessageService appExceptionMessageService;

    private static final Logger LOGGER = Logger.getLogger(AppExceptionHandler.class.getName());

    private static final List<Class<? extends Exception>> CLIENT_ERROR_EXCEPTIONS = Arrays.asList(
        // Spring MVC exceptions
        HttpRequestMethodNotSupportedException.class,
        HttpMediaTypeNotSupportedException.class,
        HttpMediaTypeNotAcceptableException.class,
        MissingPathVariableException.class,
        MissingServletRequestParameterException.class,
        ServletRequestBindingException.class,
        TypeMismatchException.class,
        HttpMessageNotReadableException.class,
        MethodArgumentNotValidException.class,
        MissingServletRequestPartException.class,
        BindException.class,
        NoHandlerFoundException.class,

        // Custom client exceptions
        IllegalArgumentException.class,
        ValidationException.class
    );

    private boolean isClientError(Exception exception) {
        return CLIENT_ERROR_EXCEPTIONS.stream()
            .anyMatch(exClass -> exClass.isInstance(exception));
    }

    @Autowired
    public AppExceptionHandler(AppExceptionMessageService appExceptionMessageService) {
        this.appExceptionMessageService = appExceptionMessageService;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppResponseDto<List<String>>> handleValidationError(MethodArgumentNotValidException exception) throws IOException {

        String languageCode = LocaleLanguageContext.getLocaleLanguageCode();

        List<FieldError> fieldErrors = exception.getFieldErrors();

        List<String> errorCodes = fieldErrors.stream()
            .map(FieldError::getDefaultMessage)
            .toList();

        try {
            List<String> errorMessages = appExceptionMessageService.getExceptionMessage(errorCodes, languageCode);
            AppResponseDto<List<String>> response = AppResponseDto.error(errorMessages);

            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);

        } catch (AppErrorTranslationException appErrorTranslationException) {

            AppResponseDto<List<String>> response = AppResponseDto.error(
                appErrorTranslationException.getMessage(languageCode)
            );

            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
        }
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<AppResponseDto<List<String>>> handleAppError(AppException exception) throws IOException {

        String languageCode = LocaleLanguageContext.getLocaleLanguageCode();
        
        List<String> errorMessage = appExceptionMessageService.getExceptionMessage(exception.getCode(), languageCode);
        
        AppResponseDto<List<String>> response = AppResponseDto.error(errorMessage);

        return ResponseEntity
            .status(exception.getHttpStatus())
            .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AppResponseDto<List<String>>> handleException(Exception exception) {

        LOGGER.log(Level.SEVERE, "handleException: {0}", exception.getMessage());

        String languageCode = LocaleLanguageContext.getLocaleLanguageCode();

        List<String> errorMessage;
        try {
            if (isClientError(exception)) {
                errorMessage = appExceptionMessageService.getExceptionMessage("CLIENT_ERROR", languageCode);
                return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(AppResponseDto.error(errorMessage));
            } else {
                errorMessage = appExceptionMessageService.getExceptionMessage("SERVER_ERROR", languageCode);
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "handleException translate exception: {0}", ex.getMessage());
            errorMessage = List.of("An unknown error has occurred");
        }
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(AppResponseDto.error(errorMessage));
    }

}
