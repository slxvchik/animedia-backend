package dev.animedia.contentservice.infrastructure.content.persistence.repository;

import dev.animedia.contentservice.application.content.exception.ContentNotFoundException;
import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.repository.ContentCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ContentCommandRepositoryImpl implements ContentCommandRepository {
	private final JpaContentRepository jpaContentRepository;

	@Autowired
	public ContentCommandRepositoryImpl(JpaContentRepository jpaContentRepository) {
		this.jpaContentRepository = jpaContentRepository;
	}

	@Override
	public Content create(Content content) {
		return null;
	}

	@Override
	public Content update(Content content) {
		return null;
	}

	@Override
	public void delete(UUID id) {
		jpaContentRepository.findById(id)
			.orElseThrow(ContentNotFoundException::new);
		jpaContentRepository.deleteById(id);
	}
}
