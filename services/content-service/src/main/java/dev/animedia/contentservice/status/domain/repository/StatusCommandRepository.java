package dev.animedia.contentservice.status.domain.repository;

import dev.animedia.contentservice.status.domain.model.Status;

import java.util.UUID;

public interface StatusCommandRepository {
    UUID create(Status status);
    void update(Status status);
    void delete(UUID id);
}
