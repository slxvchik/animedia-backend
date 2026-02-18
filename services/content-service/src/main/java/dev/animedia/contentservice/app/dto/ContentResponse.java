package dev.animedia.contentservice.app.dto;

public class ContentResponse<T> {

	private final T content;

	private ContentResponse(T content) {
		this.content = content;
	}

	public static <T> ContentResponse<T> content(T content) {
		return new ContentResponse<>(content);
	}

	public static <T> ContentResponse<T> content() {
		return new ContentResponse<>(null);
	}

	public T getContent() {
		return content;
	}
}
