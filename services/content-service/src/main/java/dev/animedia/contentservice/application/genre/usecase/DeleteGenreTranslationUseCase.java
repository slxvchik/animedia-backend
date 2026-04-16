package dev.animedia.contentservice.application.genre.usecase;

public interface DeleteGenreTranslationUseCase {
	void deleteTranslation(Long genreId, Long genreTranslationId);
}
