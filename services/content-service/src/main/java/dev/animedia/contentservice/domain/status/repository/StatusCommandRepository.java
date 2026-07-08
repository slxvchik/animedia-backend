package dev.animedia.contentservice.domain.status.repository;

import dev.animedia.contentservice.domain.status.model.Status;

import java.util.UUID;

public interface StatusCommandRepository {
    UUID create(Status status);
    void update(Status status);
    void delete(UUID id);
}
