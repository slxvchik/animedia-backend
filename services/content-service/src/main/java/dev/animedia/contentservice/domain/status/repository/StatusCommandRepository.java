package dev.animedia.contentservice.domain.status.repository;

import dev.animedia.contentservice.domain.status.model.Status;

import java.util.UUID;

public interface StatusCommandRepository {
    Status create(Status status);
    Status update(Status status);
    void delete(UUID id);
}
