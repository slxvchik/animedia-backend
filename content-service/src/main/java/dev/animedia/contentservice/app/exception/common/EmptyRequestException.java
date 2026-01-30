package dev.animedia.contentservice.app.exception.common;

import dev.animedia.contentservice.app.exception.AppException;
import org.springframework.http.HttpStatus;

public class EmptyRequestException extends AppException {
    public EmptyRequestException() {
        super(HttpStatus.BAD_REQUEST, "EMPTY_REQUEST");
    }
}
