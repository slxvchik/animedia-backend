package dev.animedia.contentservice.infrastructure.content.persistence.repository;

import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.model.ContentSearchCriteria;
import dev.animedia.contentservice.domain.content.repository.ContentSearchRepository;
import dev.animedia.contentservice.domain.shared.model.Page;
import dev.animedia.contentservice.domain.shared.model.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class ContentSearchRepositoryImpl implements ContentSearchRepository {
	@Override
	public Page<Content> search(ContentSearchCriteria contentSearchCriteria, Pageable pageable) {
		return null;
	}
}
