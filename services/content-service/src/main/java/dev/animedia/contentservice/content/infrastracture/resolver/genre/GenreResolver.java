package dev.animedia.contentservice.content.infrastracture.resolver.genre;

import dev.animedia.contentservice.content.application.dto.genre.GenreDto;
import dev.animedia.contentservice.content.application.resolver.GenreResolverInterface;
import dev.animedia.contentservice.genre.application.usecase.GetGenreListUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GenreResolver implements GenreResolverInterface {
	private final GetGenreListUseCase getGenreListUseCase;
	private final GenreResolverMapper genreResolverMapper;

	@Autowired
	public GenreResolver(GetGenreListUseCase getGenreListUseCase, GenreResolverMapper genreResolverMapper) {
		this.getGenreListUseCase = getGenreListUseCase;
		this.genreResolverMapper = genreResolverMapper;
	}

	@Override
	public List<GenreDto> resolve(Set<String> requestedGenreIdSet) {
		if (requestedGenreIdSet == null || requestedGenreIdSet.isEmpty()) {
			return List.of();
		}

		Set<UUID> requestedGenreUuidSet = requestedGenreIdSet.stream().map(UUID::fromString).collect(Collectors.toSet());

		List<GenreDto> genreDtoList = getGenreListUseCase.get(
			List.copyOf(requestedGenreUuidSet),
			null
		).stream().map(genreResolverMapper::toContentGenreDto).toList();

		if (genreDtoList.size() != requestedGenreIdSet.size()) {
			Set<UUID> foundGenreIds = genreDtoList.stream()
				.map(GenreDto::id)
				.collect(Collectors.toSet());

			List<UUID> notFoundIds = requestedGenreUuidSet.stream()
				.filter(id -> !foundGenreIds.contains(id))
				.toList();

			throw new GenreNotFoundException(notFoundIds);
		}

		return genreDtoList;
	}
}
