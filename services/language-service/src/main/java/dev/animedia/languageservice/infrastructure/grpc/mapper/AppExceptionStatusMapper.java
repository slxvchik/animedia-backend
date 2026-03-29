package dev.animedia.languageservice.infrastructure.grpc.mapper;

import org.springframework.stereotype.Component;

import dev.animedia.languageservice.domain.exception.AppExceptionStatus;
import io.grpc.Status;

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
