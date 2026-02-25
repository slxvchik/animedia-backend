package dev.animedia.contentservice.status.api;

import dev.animedia.contentservice.app.FieldValidator;
import dev.animedia.contentservice.status.mapper.GrpcContentStatusMapper;
import dev.animedia.contentservice.status.service.ContentStatusTranslationCommandService;
import dev.animedia.grpc.common.CommonProto;
import dev.animedia.grpc.status.ContentStatusCommonProto;
import dev.animedia.grpc.status.ContentStatusTranslationPrivateProto;
import dev.animedia.grpc.status.ContentStatusTranslationPrivateServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class GrpcContentStatusTranslationPrivateService extends ContentStatusTranslationPrivateServiceGrpc.ContentStatusTranslationPrivateServiceImplBase {

	private final ContentStatusTranslationCommandService contentStatusTranslationCommandService;
	private final GrpcContentStatusMapper grpcContentStatusMapper;
	private final FieldValidator fieldValidator;

	@Autowired
	public GrpcContentStatusTranslationPrivateService(
		ContentStatusTranslationCommandService contentStatusTranslationCommandService,
		GrpcContentStatusMapper grpcContentStatusMapper,
		FieldValidator fieldValidator
	) {
		this.contentStatusTranslationCommandService = contentStatusTranslationCommandService;
		this.grpcContentStatusMapper = grpcContentStatusMapper;
		this.fieldValidator = fieldValidator;
	}

	@Override
	public void create(
		ContentStatusTranslationPrivateProto.CreateRequest request,
		StreamObserver<ContentStatusCommonProto.ContentStatusTranslationResponse> responseObserver
	) {
		var requestDto = grpcContentStatusMapper.toCreateContentStatusRequestDto(request);
		fieldValidator.validate(requestDto);
		var createdContentStatusTranslation = contentStatusTranslationCommandService.create(requestDto);

		var response = grpcContentStatusMapper.toProtoContentStatusTranslation(createdContentStatusTranslation);

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public void update(
		ContentStatusTranslationPrivateProto.UpdateRequest request,
		StreamObserver<ContentStatusCommonProto.ContentStatusTranslationResponse> responseObserver
	) {
		var requestDto = grpcContentStatusMapper.toUpdateContentStatusRequestDto(request);
		fieldValidator.validate(requestDto);
		var updatedContentStatusTranslation = contentStatusTranslationCommandService.update(request.getId(), requestDto);

		var response = grpcContentStatusMapper.toProtoContentStatusTranslation(updatedContentStatusTranslation);

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public void delete(
		ContentStatusTranslationPrivateProto.DeleteRequest request,
		StreamObserver<CommonProto.EmptyResponse> responseObserver
	) {
		contentStatusTranslationCommandService.delete(request.getId());
	}
}
