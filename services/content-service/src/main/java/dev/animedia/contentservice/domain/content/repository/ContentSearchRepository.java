package dev.animedia.contentservice.domain.content.repository;

import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.model.ContentType;
import dev.animedia.contentservice.domain.shared.model.Page;
import dev.animedia.contentservice.domain.shared.model.Pageable;
import jakarta.annotation.Nullable;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ContentSearchRepository {
    Page<Content> search(
        @Nullable List<UUID> uuidList,
        @Nullable List<String> aliasList,
        @Nullable List<String> titleList,
        @Nullable List<ContentType> typeList,
        @Nullable List<Integer> seasonList,
        @Nullable List<Long> statusIdList,
        @Nullable LocalDate releaseDateFrom,
        @Nullable LocalDate releaseDateTo,
        @Nullable Boolean active,
        @Nullable List<String> languageCodeList,
        @Nullable List<Long> genreIdList,
        Pageable pageable
    );
}
