package dev.animedia.contentservice.content.domain.repository;

import dev.animedia.contentservice.content.domain.model.Content;

import java.util.UUID;

public interface ContentCommandRepository {
    UUID create(Content content);
    void update(Content content);
    void delete(UUID id);
}
