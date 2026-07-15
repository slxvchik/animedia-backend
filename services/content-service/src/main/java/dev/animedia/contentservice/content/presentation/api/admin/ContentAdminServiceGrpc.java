package dev.animedia.contentservice.content.presentation.api.admin;

import dev.animedia.contentservice.content.application.dto.content.request.CreateContentDto;
import dev.animedia.contentservice.content.application.dto.content.request.UpdateContentDto;
import dev.animedia.contentservice.content.application.dto.content.response.ContentDto;
import dev.animedia.contentservice.content.application.usecase.admin.CreateContentUseCase;
import dev.animedia.contentservice.content.application.usecase.admin.DeleteContentUseCase;
import dev.animedia.contentservice.content.application.usecase.admin.GetContentDetailUseCase;
import dev.animedia.contentservice.content.application.usecase.admin.UpdateContentUseCase;
import dev.animedia.contentservice.content.presentation.mapper.admin.request.ContentCommandAdminMapperGrpc;
import dev.animedia.contentservice.content.presentation.mapper.admin.response.ContentResponseAdminMapperGrpc;
import dev.animedia.grpc.common.CommonProto;
import dev.animedia.grpc.content.admin.v1.ContentAdminProto;
import dev.animedia.grpc.content.admin.v1.ContentAdminProtoApi;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

import java.util.UUID;

@GrpcService
public class ContentAdminServiceGrpc extends dev.animedia.grpc.content.admin.v1.ContentAdminServiceGrpc.ContentAdminServiceImplBase {
    private final ContentResponseAdminMapperGrpc contentResponseAdminMapperGrpc;
    private final ContentCommandAdminMapperGrpc contentCommandAdminMapperGrpc;

    private final GetContentDetailUseCase getContentDetailUseCase;
    private final CreateContentUseCase createContentUseCase;
    private final UpdateContentUseCase updateContentUseCase;
    private final DeleteContentUseCase deleteContentUseCase;

    @Autowired
	public ContentAdminServiceGrpc(
		ContentResponseAdminMapperGrpc contentResponseAdminMapperGrpc,
		ContentCommandAdminMapperGrpc contentCommandAdminMapperGrpc,

		GetContentDetailUseCase getContentDetailUseCase,
		CreateContentUseCase createContentUseCase,
		UpdateContentUseCase updateContentUseCase,
		DeleteContentUseCase deleteContentUseCase
	) {
		this.contentResponseAdminMapperGrpc = contentResponseAdminMapperGrpc;
		this.contentCommandAdminMapperGrpc = contentCommandAdminMapperGrpc;

		this.getContentDetailUseCase = getContentDetailUseCase;
		this.createContentUseCase = createContentUseCase;
		this.updateContentUseCase = updateContentUseCase;
		this.deleteContentUseCase = deleteContentUseCase;
	}

	@Override
	public void get(
		ContentAdminProtoApi.GetContentRequest request,
		StreamObserver<ContentAdminProto.ContentResponse> responseObserver
	) {
		ContentDto contentDtoDto = getContentDetailUseCase.get(
			UUID.fromString(request.getId())
		);
		responseObserver.onNext(
			contentResponseAdminMapperGrpc.toContentResponseGrpc(contentDtoDto)
		);
		responseObserver.onCompleted();
	}

	@Override
	public void create(
		ContentAdminProtoApi.CreateContentRequest request,
		StreamObserver<ContentAdminProtoApi.CreateContentResponse> responseObserver
	) {
		CreateContentDto createDto = contentCommandAdminMapperGrpc.toContentRequestDto(request);
		UUID createdId = createContentUseCase.create(createDto);
		responseObserver.onNext(
			ContentAdminProtoApi.CreateContentResponse.newBuilder()
				.setId(createdId.toString())
				.build()
		);
		responseObserver.onCompleted();
	}

	@Override
	public void update(
		ContentAdminProtoApi.UpdateContentRequest request,
		StreamObserver<CommonProto.EmptyResponse> responseObserver
	) {
		UpdateContentDto updateDto = contentCommandAdminMapperGrpc.toContentRequestDto(request);
		updateContentUseCase.update(updateDto);
		responseObserver.onNext(
			CommonProto.EmptyResponse.newBuilder().build()
		);
		responseObserver.onCompleted();
	}

	@Override
	public void delete(
		ContentAdminProtoApi.DeleteContentRequest request,
		StreamObserver<CommonProto.EmptyResponse> responseObserver
	) {
		deleteContentUseCase.delete(
			UUID.fromString(request.getId())
		);
		responseObserver.onNext(
			CommonProto.EmptyResponse.newBuilder().build()
		);
		responseObserver.onCompleted();
	}
}
