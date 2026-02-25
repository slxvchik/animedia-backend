package dev.animedia.contentservice.status.api;

import dev.animedia.contentservice.app.PaginationMapper;
import dev.animedia.contentservice.app.config.LanguageInterceptor;
import dev.animedia.contentservice.status.mapper.GrpcContentStatusMapper;
import dev.animedia.contentservice.status.service.ContentStatusPageService;
import dev.animedia.grpc.status.ContentStatusPublicProto;
import dev.animedia.grpc.status.ContentStatusPublicServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class GrpcContentStatusPublicService extends ContentStatusPublicServiceGrpc.ContentStatusPublicServiceImplBase {


	private final ContentStatusPageService contentStatusPageService;
	private final PaginationMapper paginationMapper;
	private final GrpcContentStatusMapper grpcContentStatusMapper;

	@Autowired
	public GrpcContentStatusPublicService(
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
		ContentStatusPublicProto.SearchRequest request,
		StreamObserver<ContentStatusPublicProto.SearchResponse> responseObserver
	) {
		var paginationRequest = paginationMapper.toPageable(request.getPagination());
		String languageCode = LanguageInterceptor.getLanguageCode();

		var contentStatuses = contentStatusPageService.search(languageCode, request.getAliasesList(), request.getNamesList(), paginationRequest);
		var paginationResponse = paginationMapper.toProtoPaginationResponse(contentStatuses);

		var response = grpcContentStatusMapper.toPublicSearchResponse(contentStatuses, paginationResponse);

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
}
