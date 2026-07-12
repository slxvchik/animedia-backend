package dev.animedia.contentservice.genre.application.exception;

import dev.animedia.contentservice.shared.domain.appexception.AppException;
import dev.animedia.contentservice.shared.domain.appexception.AppExceptionStatus;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class GenreNotFoundException extends AppException {
	public GenreNotFoundException() {
		super(AppExceptionStatus.NOT_FOUND, "genre.not_found");
	}
	public GenreNotFoundException(List<UUID> idList) {
		super(
			AppExceptionStatus.NOT_FOUND,
			"genre.not_found.extra",
			idList == null ? "" : idList.stream().map(String::valueOf).collect(Collectors.joining(", "))
		);
	}
	public GenreNotFoundException(UUID id) {
		super(
			AppExceptionStatus.NOT_FOUND,
			"genre.not_found.extra",
			id.toString()
		);
	}
}
