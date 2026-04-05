package dev.animedia.contentservice.domain.content.repository;

import dev.animedia.contentservice.domain.content.model.Content;

import java.util.UUID;

public interface ContentCommandRepository {
    Content create(Content content);
    Content update(Content content);
    void delete(UUID uuid);
}
