package dev.animedia.languageservice.app;

import dev.animedia.languageservice.app.exception.AppException;
import io.grpc.Status;
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
			throw new AppException(Status.Code.INVALID_ARGUMENT, errorCodes);
		}
	}
}
