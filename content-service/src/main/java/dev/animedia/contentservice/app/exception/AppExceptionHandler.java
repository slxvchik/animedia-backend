package dev.animedia.contentservice.app.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class AppExceptionHandler {

    private final AppExceptionMessageService appExceptionMessageService;

    @Autowired
    public AppExceptionHandler(AppExceptionMessageService appExceptionMessageService) {
        this.appExceptionMessageService = appExceptionMessageService;
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity handleAppError(AppException e) throws IOException {
        String errorMessage = appExceptionMessageService.getExceptionMessage(e.getCode(), "en", e.getParams());
        System.out.println(errorMessage);
        return new ResponseEntity(e.getHttpStatus());
    }

}
