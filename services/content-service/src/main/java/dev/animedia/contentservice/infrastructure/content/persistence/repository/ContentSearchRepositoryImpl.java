package dev.animedia.contentservice.infrastructure.content.persistence.repository;

import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.model.ContentSearchCriteria;
import dev.animedia.contentservice.domain.content.repository.ContentSearchRepository;
import dev.animedia.contentservice.domain.shared.model.Page;
import dev.animedia.contentservice.domain.shared.model.Pageable;
import dev.animedia.contentservice.old.content.dto.request.PrivateSearchRequestDto;
import dev.animedia.contentservice.old.content.dto.request.PublicSearchRequestDto;
import dev.animedia.contentservice.old.content.repository.ContentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContentSearchRepositoryImpl implements ContentSearchRepository {
	private final JpaContentRepository jpaContentRepository;

	@Autowired
	public ContentSearchRepositoryImpl(JpaContentRepository jpaContentRepository) {
		this.jpaContentRepository = jpaContentRepository;
	}

	@Override
	public Page<Content> search(ContentSearchCriteria contentSearchCriteria, Pageable pageable) {

		return null;
	}



	private org.springframework.data.domain.Page<Content> searchContents(
		PrivateSearchRequestDto searchRequestDto,
		org.springframework.data.domain.Pageable pageable
	) {
		var specs = List.of(
			ContentSpecification.hasUuid(searchRequestDto.uuid()),
			ContentSpecification.hasCreatedAtFrom(searchRequestDto.createdAtFrom()),
			ContentSpecification.hasCreatedTo(searchRequestDto.createdAtTo()),
			ContentSpecification.hasUpdatedAtFrom(searchRequestDto.updatedAtFrom()),
			ContentSpecification.hasUpdatedAtTo(searchRequestDto.updatedAtTo()),
			ContentSpecification.hasActive(searchRequestDto.active()),
			ContentSpecification.hasAlias(searchRequestDto.alias()),
			ContentSpecification.hasTranslationFilters(searchRequestDto.title(), null),
			ContentSpecification.hasTypes(searchRequestDto.types()),
			ContentSpecification.hasSeasons(searchRequestDto.seasons()),
			ContentSpecification.hasStatuses(searchRequestDto.contentStatusIds()),
			ContentSpecification.hasReleaseFrom(searchRequestDto.releaseFrom()),
			ContentSpecification.hasReleaseTo(searchRequestDto.releaseTo()),
			ContentSpecification.hasLanguageCodes(searchRequestDto.languageCodes()),
			ContentSpecification.hasGenres(searchRequestDto.genreIds())
		);

		return jpaContentRepository.findAll(Specification.allOf(specs), pageable);
	}

	private org.springframework.data.domain.Page<Content> searchContents(
		PublicSearchRequestDto searchRequestDto,
		String languageCode,
		org.springframework.data.domain.Pageable pageable
	) {
		var specs = List.of(
			ContentSpecification.hasAlias(searchRequestDto.alias()),
			ContentSpecification.hasTranslationFilters(searchRequestDto.title(), languageCode),
			ContentSpecification.hasTypes(searchRequestDto.types()),
			ContentSpecification.hasSeasons(searchRequestDto.seasons()),
			ContentSpecification.hasStatuses(searchRequestDto.contentStatusIds()),
			ContentSpecification.hasReleaseFrom(searchRequestDto.releaseFrom()),
			ContentSpecification.hasReleaseTo(searchRequestDto.releaseTo()),
			ContentSpecification.hasLanguageCodes(searchRequestDto.languageCodes()),
			ContentSpecification.hasGenres(searchRequestDto.genreIds())
		);

		return jpaContentRepository.findAll(Specification.allOf(specs), pageable);
	}
}
