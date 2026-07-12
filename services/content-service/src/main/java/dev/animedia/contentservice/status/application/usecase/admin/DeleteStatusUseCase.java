package dev.animedia.contentservice.status.application.usecase.admin;

import java.util.UUID;

public interface DeleteStatusUseCase {
    void delete(UUID id);
}
