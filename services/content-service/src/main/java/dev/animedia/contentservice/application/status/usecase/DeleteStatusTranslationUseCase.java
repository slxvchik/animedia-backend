package dev.animedia.contentservice.application.status.usecase;

public interface DeleteStatusTranslationUseCase {
    void deleteTranslation(Long statusId, Long statusTranslationId);
}
