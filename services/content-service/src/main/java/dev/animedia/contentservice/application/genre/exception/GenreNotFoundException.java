package dev.animedia.contentservice.application.genre.exception;

import dev.animedia.contentservice.domain.shared.exception.AppException;
import dev.animedia.contentservice.domain.shared.exception.AppExceptionStatus;

import java.util.List;
import java.util.stream.Collectors;

public class GenreNotFoundException extends AppException {
	public enum CODE {
		GENRE_NOT_FOUND,
		GENRES_NOT_FOUND,
		GENRES_NOT_FOUND_ADMIN
	}
	public GenreNotFoundException(CODE errorCode) {
		super(AppExceptionStatus.NOT_FOUND, errorCode.name());
	}
	public GenreNotFoundException(List<Long> idList) {
		super(
			AppExceptionStatus.NOT_FOUND,
			CODE.GENRES_NOT_FOUND_ADMIN.name(),
			idList == null ? "" : idList.stream().map(String::valueOf).collect(Collectors.joining(", "))
		);
	}
}
