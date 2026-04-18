package dev.animedia.contentservice.domain.content.repository;

import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.model.ContentSearchCriteria;
import dev.animedia.contentservice.domain.shared.model.Page;
import dev.animedia.contentservice.domain.shared.model.Pageable;

public interface ContentSearchRepository {
    Page<Content> search(ContentSearchCriteria contentSearchCriteria, Pageable pageable);
}