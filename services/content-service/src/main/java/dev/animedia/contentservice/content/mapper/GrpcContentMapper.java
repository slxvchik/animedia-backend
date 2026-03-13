package dev.animedia.contentservice.content.mapper;

import dev.animedia.contentservice.content.dto.request.PublicSearchRequestDto;
import dev.animedia.grpc.core.PublicContentProto;
import org.springframework.stereotype.Component;

@Component
public class GrpcContentMapper {
	public PublicSearchRequestDto toPublicSearchRequestDto(PublicContentProto.PublicSearchRequest request) {

		return new PublicSearchRequestDto(
			String alias,
			String title,
			ContentType type,
			List<Integer> seasons,
			List<Long> contentStatusIds,
			LocalDate releaseFrom,
			LocalDate releaseTo,
			List<String> languageCodes,
			List<Long> genreIds,
			Pageable pageable
		);
	}
}
