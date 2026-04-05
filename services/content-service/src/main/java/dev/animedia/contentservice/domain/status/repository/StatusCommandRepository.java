package dev.animedia.contentservice.domain.status.repository;

import dev.animedia.contentservice.domain.status.model.Status;

public interface StatusCommandRepository {
    Status create(Status status);
    Status update(Status status);
    void delete(Long id);
}
