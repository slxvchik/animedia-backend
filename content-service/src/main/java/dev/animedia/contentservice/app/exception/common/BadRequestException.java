package dev.animedia.contentservice.app.exception.common;

import dev.animedia.contentservice.app.exception.AppException;
import org.springframework.http.HttpStatus;

public class BadRequestException extends AppException {
    public BadRequestException() {
        super(HttpStatus.BAD_REQUEST, "BAD_REQUEST");
    }
}
