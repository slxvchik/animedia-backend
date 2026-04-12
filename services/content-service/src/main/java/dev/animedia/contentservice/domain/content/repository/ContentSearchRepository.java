package dev.animedia.contentservice.domain.content.repository;

import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.model.ContentSearchCriteria;
import dev.animedia.contentservice.domain.shared.model.Page;

public interface ContentSearchRepository {
    Page<Content> search(ContentSearchCriteria contentSearchCriteria);
}
