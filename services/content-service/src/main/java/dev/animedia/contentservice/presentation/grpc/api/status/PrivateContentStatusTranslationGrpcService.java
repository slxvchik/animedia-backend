package dev.animedia.contentservice.presentation.grpc.api.status;

import dev.animedia.contentservice.old.app.FieldValidator;
import dev.animedia.contentservice.old.status.mapper.GrpcContentStatusMapper;
import dev.animedia.contentservice.old.status.service.ContentStatusTranslationCommandService;
import dev.animedia.grpc.common.CommonProto;
import dev.animedia.grpc.status.ContentStatusCommonProto;
import dev.animedia.grpc.status.PrivateContentStatusTranslationProto;
import dev.animedia.grpc.status.PrivateContentStatusTranslationServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class PrivateContentStatusTranslationGrpcService extends PrivateContentStatusTranslationServiceGrpc.PrivateContentStatusTranslationServiceImplBase {

	private final ContentStatusTranslationCommandService contentStatusTranslationCommandService;
	private final GrpcContentStatusMapper grpcContentStatusMapper;
	private final FieldValidator fieldValidator;

	@Autowired
	public PrivateContentStatusTranslationGrpcService(
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
		PrivateContentStatusTranslationProto.PrivateTranslationCreateRequest request,
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
		PrivateContentStatusTranslationProto.PrivateTranslationUpdateRequest request,
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
		PrivateContentStatusTranslationProto.PrivateTranslationDeleteRequest request,
		StreamObserver<CommonProto.EmptyResponse> responseObserver
	) {
		contentStatusTranslationCommandService.delete(request.getId());
	}
}
