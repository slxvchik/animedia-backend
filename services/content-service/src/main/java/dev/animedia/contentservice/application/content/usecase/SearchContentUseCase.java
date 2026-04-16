package dev.animedia.contentservice.application.content.usecase;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import dev.animedia.contentservice.application.content.dto.GenreSearchDto;
import dev.animedia.contentservice.domain.shared.model.Pageable;
import org.springframework.data.domain.Page;

public interface SearchContentUseCase {
	Page<ContentDto> search(GenreSearchDto genreSearchDto, Pageable pageable);
}
