package dev.animedia.languageservice.mapper;

import org.springframework.stereotype.Component;

import dev.animedia.languageservice.exception.AppExceptionStatus;
import io.grpc.Status;

@Component
public class AppExceptionStatusMapper {
	public Status.Code toGrpcCode(AppExceptionStatus status) {
		return switch (status) {
			case NOT_FOUND -> Status.Code.NOT_FOUND;
			case ALREADY_EXISTS -> Status.Code.ALREADY_EXISTS;
			case INVALID_ARGUMENT, CLIENT_ERROR -> Status.Code.INVALID_ARGUMENT;
			case UNAUTHORIZED -> Status.Code.UNAUTHENTICATED;
			case INTERNAL_ERROR -> Status.Code.INTERNAL;
		};
	}
}
