package dev.animedia.contentservice.app;

import dev.animedia.contentservice.app.exception.AppException;
import dev.animedia.contentservice.app.exception.AppExceptionStatus;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FieldValidator {
    private final Validator validator;

    @Autowired
    public FieldValidator(Validator validator) {
        this.validator = validator;
    }

    public <T> void validate(T requestDto, Class<?>... groups) {
        var violations = validator.validate(requestDto, groups);
        if (!violations.isEmpty()) {
            List<String> errorCodes = violations.stream().map(ConstraintViolation::getMessage).toList();
            throw new AppException(AppExceptionStatus.INVALID_ARGUMENT, errorCodes);
        }
    }

    public <T> void validate(List<T> listRequestDto, Class<?>... groups) {
        var violations = validator.validate(listRequestDto, groups);
        if (!violations.isEmpty()) {
            List<String> errorCodes = violations.stream().map(ConstraintViolation::getMessage).toList();
            throw new AppException(AppExceptionStatus.INVALID_ARGUMENT, errorCodes);
        }
    }
}