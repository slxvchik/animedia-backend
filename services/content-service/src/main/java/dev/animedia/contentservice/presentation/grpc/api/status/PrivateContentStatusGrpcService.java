package dev.animedia.contentservice.presentation.grpc.api.status;

import dev.animedia.contentservice.old.app.FieldValidator;
import dev.animedia.contentservice.presentation.grpc.mapper.PaginationMapper;
import dev.animedia.contentservice.old.status.mapper.GrpcContentStatusMapper;
import dev.animedia.contentservice.old.status.service.ContentStatusCommandService;
import dev.animedia.contentservice.old.status.service.ContentStatusPageService;
import dev.animedia.grpc.common.CommonProto;
import dev.animedia.grpc.status.ContentStatusCommonProto;
import dev.animedia.grpc.status.PrivateContentStatusProto;
import dev.animedia.grpc.status.PrivateContentStatusServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class PrivateContentStatusGrpcService extends PrivateContentStatusServiceGrpc.PrivateContentStatusServiceImplBase {

	private final ContentStatusCommandService contentStatusCommandService;
	private final ContentStatusPageService contentStatusPageService;
	private final PaginationMapper paginationMapper;
	private final GrpcContentStatusMapper grpcContentStatusMapper;
	private final FieldValidator fieldValidator;

	@Autowired
	public PrivateContentStatusGrpcService(
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
		PrivateContentStatusProto.PrivateSearchRequest request,
		StreamObserver<PrivateContentStatusProto.PrivateSearchResponse> responseObserver
	) {
		var paginationRequest = paginationMapper.toPageable(request.getPagination());
		var contentStatuses = contentStatusPageService.search(request.getIdsList(), request.getLanguageCodesList(), request.hasAlias() ? request.getAlias() : null, request.hasName() ? request.getName() : null, paginationRequest);

		var paginationResponse = paginationMapper.toProtoPaginationResponse(contentStatuses);
		var response = grpcContentStatusMapper.toPrivateSearchResponse(contentStatuses, paginationResponse);

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public void create(
		PrivateContentStatusProto.PrivateCreateRequest request,
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
		PrivateContentStatusProto.PrivateUpdateRequest request,
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
		PrivateContentStatusProto.PrivateDeleteRequest request,
		StreamObserver<CommonProto.EmptyResponse> responseObserver
	) {
		contentStatusCommandService.delete(request.getId());
		responseObserver.onCompleted();
	}
}
