package dev.animedia.contentservice.presentation.grpc.status.api;

import dev.animedia.contentservice.application.status.dto.StatusDto;
import dev.animedia.contentservice.application.status.dto.StatusSearchDto;
import dev.animedia.contentservice.application.status.usecase.SearchStatusUseCase;
import dev.animedia.contentservice.domain.shared.model.Page;
import dev.animedia.contentservice.domain.shared.model.Pageable;
import dev.animedia.contentservice.presentation.grpc.config.LanguageInterceptor;
import dev.animedia.contentservice.presentation.grpc.shared.mapper.ProtoPaginationMapper;
import dev.animedia.contentservice.presentation.grpc.status.mapper.PublicStatusGrpcMapper;
import dev.animedia.grpc.common.CommonProto.PaginationResponse;
import dev.animedia.grpc.status.PublicContentStatusProto.PublicSearchStatusRequest;
import dev.animedia.grpc.status.PublicContentStatusProto.PublicSearchStatusResponse;
import dev.animedia.grpc.status.PublicContentStatusProto.PublicStatusResponse;
import dev.animedia.grpc.status.PublicContentStatusServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

import java.util.List;
import java.util.Set;

@GrpcService
public class PublicStatusGrpcService extends PublicContentStatusServiceGrpc.PublicContentStatusServiceImplBase {
	private final ProtoPaginationMapper protoPaginationMapper;
	private final SearchStatusUseCase searchStatusUseCase;
	private final PublicStatusGrpcMapper publicStatusGrpcMapper;

	@Autowired
	public PublicStatusGrpcService(
		ProtoPaginationMapper protoPaginationMapper,
		SearchStatusUseCase searchStatusUseCase,
		PublicStatusGrpcMapper publicStatusGrpcMapper
	) {
		this.protoPaginationMapper = protoPaginationMapper;
		this.searchStatusUseCase = searchStatusUseCase;
		this.publicStatusGrpcMapper = publicStatusGrpcMapper;
	}

	@Override
	public void search(
		PublicSearchStatusRequest request,
		StreamObserver<PublicSearchStatusResponse> responseObserver
	) {

		String languageCode = LanguageInterceptor.getLanguageCode();

		StatusSearchDto statusSearchDto = publicStatusGrpcMapper.toPublicStatusSearchDto(request, languageCode);
		Pageable domainPageable = protoPaginationMapper.toDomainPageable(
			request.getPagination(),
			Set.of("alias", "sortOrder", "translations.name")
		);

		Page<StatusDto> statusDtoPage = searchStatusUseCase.search(statusSearchDto, domainPageable);

		PaginationResponse paginationResponse = protoPaginationMapper.toProtoPaginationResponse(statusDtoPage);
		List<PublicStatusResponse> statusResponseList = statusDtoPage.content() != null
			? statusDtoPage.content()
				.stream()
				.map(publicStatusGrpcMapper::toPublicStatusResponse)
				.toList()
			: List.of();

		responseObserver.onNext(
			publicStatusGrpcMapper.toPublicSearchStatusResponse(
				statusResponseList,
				paginationResponse
			)
		);
		responseObserver.onCompleted();
	}
}
