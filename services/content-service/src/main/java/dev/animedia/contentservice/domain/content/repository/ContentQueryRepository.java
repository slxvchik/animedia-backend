package dev.animedia.contentservice.domain.content.repository;

import dev.animedia.contentservice.domain.content.model.Content;
import jakarta.annotation.Nullable;

import java.util.Optional;
import java.util.UUID;

public interface ContentQueryRepository {
    Optional<Content> findById(UUID uuid, @Nullable String languageCode);
    Optional<Content> findByAlias(String alias, @Nullable String languageCode);
}
