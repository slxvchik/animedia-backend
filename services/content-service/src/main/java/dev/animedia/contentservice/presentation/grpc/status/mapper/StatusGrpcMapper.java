package dev.animedia.contentservice.presentation.grpc.status.mapper;

import dev.animedia.contentservice.application.status.dto.StatusSearchDto;
import dev.animedia.grpc.status.PublicContentStatusProto.PublicSearchStatusRequest;
import org.springframework.stereotype.Component;

@Component
public class StatusGrpcMapper {
	public StatusSearchDto toPublicStatusSearchDto(
		PublicSearchStatusRequest request,
		String languageCode
	) {
		return new StatusSearchDto(
			true,
			request.hasAlias() ? request.getAlias() : null,
			request.hasName() ? request.getName() : null,
			languageCode
		);
	}
}