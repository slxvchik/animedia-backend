package dev.animedia.contentservice.content.presentation.api.user;

import dev.animedia.contentservice.content.application.dto.content.ContentResponseDto;
import dev.animedia.contentservice.content.application.usecase.user.GetContentDetailUseCase;
import dev.animedia.contentservice.content.application.usecase.user.GetContentListUseCase;
import dev.animedia.contentservice.shared.presentation.grpc.config.LanguageInterceptor;
import dev.animedia.contentservice.content.presentation.mapper.ContentTypeMapperGrpc;
import dev.animedia.contentservice.content.presentation.mapper.user.response.ContentResponseUserMapperGrpc;
import dev.animedia.grpc.content.user.v1.ContentUserProto;
import dev.animedia.grpc.content.user.v1.ContentUserProtoApi;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

import java.util.List;
import java.util.UUID;

@GrpcService
public class ContentUserServiceGrpc extends dev.animedia.grpc.content.user.v1.ContentUserServiceGrpc.ContentUserServiceImplBase {
	private final ContentResponseUserMapperGrpc contentResponseUserMapperGrpc;
	private final ContentTypeMapperGrpc contentTypeMapperGrpc;

    private final GetContentListUseCase getContentListUseCase;
    private final GetContentDetailUseCase getContentDetailUseCase;

	@Autowired
	public ContentUserServiceGrpc(
	    ContentResponseUserMapperGrpc contentResponseUserMapperGrpc,
		ContentTypeMapperGrpc contentTypeMapperGrpc,
		GetContentListUseCase getContentListUseCase,
	    GetContentDetailUseCase getContentDetailUseCase
	) {
	    this.contentResponseUserMapperGrpc = contentResponseUserMapperGrpc;
		this.contentTypeMapperGrpc = contentTypeMapperGrpc;
		this.getContentListUseCase = getContentListUseCase;
	    this.getContentDetailUseCase = getContentDetailUseCase;
	}

	@Override
	public void getDetail(
		ContentUserProtoApi.GetDetailRequest request,
		StreamObserver<ContentUserProtoApi.GetDetailResponse> responseObserver
	) {
		ContentResponseDto contentResponseDto = getContentDetailUseCase.get(
			request.getAlias(),
			contentTypeMapperGrpc.toContentType(request.getType()),
			request.getSeason(),
			LanguageInterceptor.getLanguageCode()
		);
		ContentUserProto.ContentResponse contentResponse = contentResponseUserMapperGrpc.toContentResponseGrpc(contentResponseDto);
		responseObserver.onNext(
			ContentUserProtoApi.GetDetailResponse.newBuilder()
				.setContent(contentResponse)
				.build()
		);
		responseObserver.onCompleted();
	}

	@Override
	public void getList(
		ContentUserProtoApi.GetListRequest request,
		StreamObserver<ContentUserProtoApi.GetListResponse> responseObserver
	) {
		List<ContentResponseDto> contentResponseDtoList = getContentListUseCase.get(
			request.getIdsList().stream().map(UUID::fromString).toList(),
			LanguageInterceptor.getLanguageCode()
		);
		List<ContentUserProto.ContentResponse> contentResponseList = contentResponseDtoList.stream()
			.map(contentResponseUserMapperGrpc::toContentResponseGrpc)
			.toList();
		responseObserver.onNext(
			ContentUserProtoApi.GetListResponse.newBuilder()
				.addAllContents(contentResponseList)
				.build()
		);
		responseObserver.onCompleted();
	}
}
