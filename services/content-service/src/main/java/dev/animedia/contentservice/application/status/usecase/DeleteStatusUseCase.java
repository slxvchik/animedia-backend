package dev.animedia.contentservice.application.status.usecase;

import java.util.UUID;

public interface DeleteStatusUseCase {
    void delete(UUID id);
}
