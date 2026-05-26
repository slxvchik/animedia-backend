package dev.animedia.contentservice.presentation.grpc.status.api;

import dev.animedia.contentservice.application.status.dto.StatusDto;
import dev.animedia.contentservice.application.status.dto.StatusSearchDto;
import dev.animedia.contentservice.application.status.usecase.SearchStatusUseCase;
import dev.animedia.contentservice.domain.shared.model.Page;
import dev.animedia.contentservice.presentation.grpc.config.LanguageInterceptor;
import dev.animedia.contentservice.presentation.grpc.shared.mapper.PaginationMapper;
import dev.animedia.grpc.status.ContentStatusCommonProto;
import dev.animedia.grpc.status.PublicContentStatusProto;
import dev.animedia.grpc.status.PublicContentStatusServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class PublicContentStatusGrpcService extends PublicContentStatusServiceGrpc.PublicContentStatusServiceImplBase {
	private final PaginationMapper paginationMapper;
	private final SearchStatusUseCase searchStatusUseCase;

	@Autowired
	public PublicContentStatusGrpcService(
		PaginationMapper paginationMapper,
		SearchStatusUseCase searchStatusUseCase
	) {
		this.paginationMapper = paginationMapper;
		this.searchStatusUseCase = searchStatusUseCase;
	}

	public void search(
		PublicContentStatusProto.PublicSearchRequest request,
		StreamObserver<PublicContentStatusProto.PublicSearchResponse> responseObserver
	) {
		var paginationRequest = paginationMapper.toPageable(request.getPagination());
		String languageCode = LanguageInterceptor.getLanguageCode();

		var contentStatuses = contentStatusPageService.search(languageCode, request.hasAlias() ? request.getAlias() : null, request.hasName() ? request.getName() : null, paginationRequest);
		var paginationResponse = paginationMapper.toProtoPaginationResponse(contentStatuses);

		var response = grpcContentStatusMapper.toPublicSearchResponse(contentStatuses, paginationResponse);

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public void search(
		PublicContentStatusProto.PublicSearchStatusRequest request,
		StreamObserver<ContentStatusCommonProto.SearchStatusResponse> responseObserver
	) {
		StatusSearchDto statusSearchDto =
		Page<StatusDto> statusDtoPage = searchStatusUseCase.search();

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
}
