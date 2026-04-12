package dev.animedia.contentservice.domain.content.model;

import dev.animedia.contentservice.domain.shared.model.Pageable;
import jakarta.annotation.Nullable;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ContentSearchCriteria(
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
) {}