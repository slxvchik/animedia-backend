package dev.animedia.contentservice.presentation.grpc.api.status;

import dev.animedia.contentservice.presentation.grpc.mapper.PaginationMapper;
import dev.animedia.contentservice.presentation.grpc.config.LanguageInterceptor;
import dev.animedia.contentservice.old.status.mapper.GrpcContentStatusMapper;
import dev.animedia.contentservice.old.status.service.ContentStatusPageService;
import dev.animedia.grpc.status.PublicContentStatusProto;
import dev.animedia.grpc.status.PublicContentStatusServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class PublicContentStatusGrpcService extends PublicContentStatusServiceGrpc.PublicContentStatusServiceImplBase {


	private final ContentStatusPageService contentStatusPageService;
	private final PaginationMapper paginationMapper;
	private final GrpcContentStatusMapper grpcContentStatusMapper;

	@Autowired
	public PublicContentStatusGrpcService(
		ContentStatusPageService contentStatusPageService,
		PaginationMapper paginationMapper,
		GrpcContentStatusMapper grpcContentStatusMapper
	) {
		this.contentStatusPageService = contentStatusPageService;
		this.paginationMapper = paginationMapper;
		this.grpcContentStatusMapper = grpcContentStatusMapper;
	}

	@Override
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
}
