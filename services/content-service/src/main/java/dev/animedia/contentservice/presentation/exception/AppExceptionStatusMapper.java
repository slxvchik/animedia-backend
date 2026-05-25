package dev.animedia.contentservice.presentation.exception;

import dev.animedia.contentservice.domain.shared.exception.AppExceptionStatus;
import io.grpc.Status;
import org.springframework.stereotype.Component;

@Component
public class AppExceptionStatusMapper {
	public Status.Code toGrpcCode(AppExceptionStatus status) {
		return switch (status) {
			case NOT_FOUND -> Status.Code.NOT_FOUND;
			case ALREADY_EXISTS -> Status.Code.ALREADY_EXISTS;
			case INVALID_ARGUMENT -> Status.Code.INVALID_ARGUMENT;
			case INTERNAL_ERROR -> Status.Code.INTERNAL;
		};
	}
}
