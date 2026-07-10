package dev.animedia.contentservice.domain.content.repository;

import dev.animedia.contentservice.domain.content.model.Content;

import java.util.UUID;

public interface ContentCommandRepository {
    UUID create(Content content);
    void update(Content content);
    void delete(UUID id);
}
