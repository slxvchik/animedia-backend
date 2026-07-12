package dev.animedia.contentservice.content.presentation.api.admin;

import dev.animedia.contentservice.content.application.dto.ContentDto;
import dev.animedia.contentservice.content.application.usecase.IndexAllContentUseCase;
import dev.animedia.contentservice.content.application.usecase.admin.CreateContentUseCase;
import dev.animedia.contentservice.content.application.usecase.admin.DeleteContentUseCase;
import dev.animedia.contentservice.content.application.usecase.admin.GetContentDetailUseCase;
import dev.animedia.contentservice.content.application.usecase.admin.UpdateContentUseCase;
import dev.animedia.contentservice.shared.domain.pagination.Page;
import dev.animedia.contentservice.shared.domain.pagination.Pageable;
import dev.animedia.contentservice.content.presentation.mapper.admin.ContentCommandAdminMapperGrpc;
import dev.animedia.contentservice.content.presentation.mapper.admin.ContentResponseAdminMapperGrpc;
import dev.animedia.contentservice.shared.presentation.grpc.mapper.ProtoPaginationMapper;
import dev.animedia.grpc.common.CommonProto;
import dev.animedia.grpc.content.admin.v1.ContentAdminProto;
import dev.animedia.grpc.content.admin.v1.ContentAdminProtoApi;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

import java.util.UUID;

@GrpcService
public class ContentAdminServiceGrpc extends dev.animedia.grpc.content.admin.v1.ContentAdminServiceGrpc.ContentAdminServiceImplBase {
    private final ProtoPaginationMapper protoPaginationMapper;
    private final ContentResponseAdminMapperGrpc contentResponseAdminMapperGrpc;
    private final ContentCommandAdminMapperGrpc contentCommandAdminMapperGrpc;

    private final IndexAllContentUseCase indexAllContentUseCase;
    private final GetContentDetailUseCase getContentDetailUseCase;
    private final CreateContentUseCase createContentUseCase;
    private final UpdateContentUseCase updateContentUseCase;
    private final DeleteContentUseCase deleteContentUseCase;

    @Autowired
	public ContentAdminServiceGrpc(
		ProtoPaginationMapper protoPaginationMapper,
		ContentResponseAdminMapperGrpc contentResponseAdminMapperGrpc,
		ContentCommandAdminMapperGrpc contentCommandAdminMapperGrpc,

		IndexAllContentUseCase indexAllContentUseCase,
		GetContentDetailUseCase getContentDetailUseCase,
		CreateContentUseCase createContentUseCase,
		UpdateContentUseCase updateContentUseCase,
		DeleteContentUseCase deleteContentUseCase
	) {
		this.protoPaginationMapper = protoPaginationMapper;
		this.contentResponseAdminMapperGrpc = contentResponseAdminMapperGrpc;
		this.contentCommandAdminMapperGrpc = contentCommandAdminMapperGrpc;

		this.indexAllContentUseCase = indexAllContentUseCase;
		this.getContentDetailUseCase = getContentDetailUseCase;
		this.createContentUseCase = createContentUseCase;
		this.updateContentUseCase = updateContentUseCase;
		this.deleteContentUseCase = deleteContentUseCase;
	}

	@Override
	public void getAll(
		CommonProto.PaginationRequest request,
		StreamObserver<ContentAdminProtoApi.GetAllContentResponse> responseObserver
	) {
		Pageable domainPageable = protoPaginationMapper.toDomainPageable(request);
		Page<ContentDto> contentDtoDomainPage = indexAllContentUseCase.index(domainPageable);
		var responseContent = contentDtoDomainPage.content().stream()
			.map(contentResponseAdminMapperGrpc::toContentGrpcResponse)
			.toList();
		var responsePagination = protoPaginationMapper.toProtoPaginationResponse(contentDtoDomainPage);
		responseObserver.onNext(
			ContentAdminProtoApi.GetAllContentResponse.newBuilder()
				.addAllContents(responseContent)
				.setPagination(responsePagination)
				.build()
		);
		responseObserver.onCompleted();
	}

	@Override
	public void get(
		ContentAdminProtoApi.GetContentRequest request,
		StreamObserver<ContentAdminProto.ContentResponse> responseObserver
	) {
		ContentDto contentDto = getContentDetailUseCase.get(
			UUID.fromString(request.getId())
		);
		responseObserver.onNext(
			contentResponseAdminMapperGrpc.toContentGrpcResponse(contentDto)
		);
		responseObserver.onCompleted();
	}

	@Override
	public void create(
		ContentAdminProtoApi.CreateContentRequest request,
		StreamObserver<ContentAdminProtoApi.CreateContentResponse> responseObserver
	) {
		ContentDto createDto = contentCommandAdminMapperGrpc.toContentDto(request);
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
		ContentDto updateDto = contentCommandAdminMapperGrpc.toContentDto(request);
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
