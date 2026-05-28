package dev.animedia.contentservice.application.genre.exception;

import dev.animedia.contentservice.domain.shared.exception.AppException;
import dev.animedia.contentservice.domain.shared.exception.AppExceptionStatus;

import java.util.List;
import java.util.stream.Collectors;

public class GenreNotFoundException extends AppException {
	public GenreNotFoundException() {
		super(AppExceptionStatus.NOT_FOUND, "genre.not_found");
	}
	public GenreNotFoundException(List<Long> idList) {
		super(
			AppExceptionStatus.NOT_FOUND,
			"genre.not_found.extra",
			idList == null ? "" : idList.stream().map(String::valueOf).collect(Collectors.joining(", "))
		);
	}
}
