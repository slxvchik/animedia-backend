package dev.animedia.contentservice.domain.content.repository;

import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.model.ContentType;
import jakarta.annotation.Nullable;

import java.util.Optional;
import java.util.UUID;

public interface ContentQueryRepository {
    Optional<Content> find(UUID id, @Nullable String languageCode);
    Optional<Content> find(String alias, ContentType type, @Nullable Integer season, @Nullable String languageCode);

    boolean exists(String alias, ContentType type, Integer season);
}