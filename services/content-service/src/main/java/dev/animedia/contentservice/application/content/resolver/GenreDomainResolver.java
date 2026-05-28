package dev.animedia.contentservice.application.content.resolver;

import dev.animedia.contentservice.application.genre.exception.GenreNotFoundException;
import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.genre.repository.GenreQueryRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GenreDomainResolver {
	private final GenreQueryRepository genreQueryRepository;

	public GenreDomainResolver(GenreQueryRepository genreQueryRepository) {
		this.genreQueryRepository = genreQueryRepository;
	}

	public Set<Genre> resolve(Set<Long> requestedGenreIds) {
		if (requestedGenreIds == null || requestedGenreIds.isEmpty()) {
			return Set.of();
		}

		List<Genre> genreList = genreQueryRepository.findByIdList(
			List.copyOf(requestedGenreIds),
			null,
			null
		);

		if (genreList.size() != requestedGenreIds.size()) {
			Set<Long> foundGenreIds = genreList.stream()
				.map(Genre::getId)
				.collect(Collectors.toSet());

			List<Long> notFoundIds = requestedGenreIds.stream()
				.filter(id -> !foundGenreIds.contains(id))
				.toList();

			throw new GenreNotFoundException(notFoundIds);
		}

		return Set.copyOf(genreList);
	}

}
