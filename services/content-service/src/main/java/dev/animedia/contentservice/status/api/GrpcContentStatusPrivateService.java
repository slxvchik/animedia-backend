package dev.animedia.contentservice.status.api;

import dev.animedia.contentservice.app.FieldValidator;
import dev.animedia.contentservice.app.PaginationMapper;
import dev.animedia.contentservice.status.mapper.GrpcContentStatusMapper;
import dev.animedia.contentservice.status.service.ContentStatusCommandService;
import dev.animedia.contentservice.status.service.ContentStatusPageService;
import dev.animedia.grpc.common.CommonProto;
import dev.animedia.grpc.status.ContentStatusCommonProto;
import dev.animedia.grpc.status.ContentStatusPrivateProto;
import dev.animedia.grpc.status.ContentStatusPrivateServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class GrpcContentStatusPrivateService extends ContentStatusPrivateServiceGrpc.ContentStatusPrivateServiceImplBase {

	private final ContentStatusCommandService contentStatusCommandService;
	private final ContentStatusPageService contentStatusPageService;
	private final PaginationMapper paginationMapper;
	private final GrpcContentStatusMapper grpcContentStatusMapper;
	private final FieldValidator fieldValidator;

	@Autowired
	public GrpcContentStatusPrivateService(
		ContentStatusCommandService contentStatusCommandService,
		ContentStatusPageService contentStatusPageService,
		PaginationMapper paginationMapper,
		GrpcContentStatusMapper grpcContentStatusMapper,
		FieldValidator fieldValidator
	) {
		this.contentStatusCommandService = contentStatusCommandService;
		this.contentStatusPageService = contentStatusPageService;
		this.paginationMapper = paginationMapper;
		this.grpcContentStatusMapper = grpcContentStatusMapper;
		this.fieldValidator = fieldValidator;
	}

	@Override
	public void search(
		ContentStatusPrivateProto.SearchRequest request,
		StreamObserver<ContentStatusPrivateProto.SearchResponse> responseObserver
	) {
		var paginationRequest = paginationMapper.toPageable(request.getPagination());
		var contentStatuses = contentStatusPageService.search(request.getIdsList(), request.getLanguageCodesList(), request.getAliasesList(), request.getNamesList(), paginationRequest);

		var paginationResponse = paginationMapper.toProtoPaginationResponse(contentStatuses);
		var response = grpcContentStatusMapper.toPrivateSearchResponse(contentStatuses, paginationResponse);

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public void create(
		ContentStatusPrivateProto.CreateRequest request,
		StreamObserver<ContentStatusCommonProto.ContentStatusResponse> responseObserver
	) {
		var requestDto = grpcContentStatusMapper.toContentStatusRequestDto(request);
		fieldValidator.validate(requestDto);
		var savedContentStatus = contentStatusCommandService.create(requestDto);

		var response = grpcContentStatusMapper.toProtoContentStatus(savedContentStatus);
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public void update(
		ContentStatusPrivateProto.UpdateRequest request,
		StreamObserver<ContentStatusCommonProto.ContentStatusResponse> responseObserver
	) {
		var requestDto = grpcContentStatusMapper.toContentStatusRequestDto(request);
		fieldValidator.validate(requestDto);
		var updatedContentStatus = contentStatusCommandService.update(request.getId(), requestDto);

		var response = grpcContentStatusMapper.toProtoContentStatus(updatedContentStatus);
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public void delete(
		ContentStatusPrivateProto.DeleteRequest request,
		StreamObserver<CommonProto.EmptyResponse> responseObserver
	) {
		contentStatusCommandService.delete(request.getId());
		responseObserver.onCompleted();
	}
}
