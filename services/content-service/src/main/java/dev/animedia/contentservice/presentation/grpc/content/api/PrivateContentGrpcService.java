package dev.animedia.contentservice.presentation.grpc.content.api;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import dev.animedia.contentservice.application.content.dto.ContentSearchDto;
import dev.animedia.contentservice.application.content.usecase.admin.*;
import dev.animedia.contentservice.domain.shared.pagination.Page;
import dev.animedia.contentservice.domain.shared.pagination.Pageable;
import dev.animedia.contentservice.presentation.grpc.content.mapper.PrivateContentCommandGrpcMapper;
import dev.animedia.contentservice.presentation.grpc.content.mapper.PrivateContentCommonGrpcMapper;
import dev.animedia.contentservice.presentation.grpc.content.mapper.PrivateContentQueryGrpcMapper;
import dev.animedia.contentservice.presentation.grpc.shared.mapper.ProtoPaginationMapper;
import dev.animedia.grpc.common.CommonProto;
import dev.animedia.grpc.common.CommonProto.PaginationResponse;
import dev.animedia.grpc.core.PrivateContentProto;
import dev.animedia.grpc.core.PrivateContentProto.PrivateContentResponse;
import dev.animedia.grpc.core.PrivateContentProto.PrivateSearchContentRequest;
import dev.animedia.grpc.core.PrivateContentProto.PrivateSearchContentResponse;
import dev.animedia.grpc.core.PrivateContentServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@GrpcService
public class PrivateContentGrpcService extends PrivateContentServiceGrpc.PrivateContentServiceImplBase {
    private final ProtoPaginationMapper protoPaginationMapper;
    private final PrivateContentCommonGrpcMapper privateContentCommonGrpcMapper;
    private final PrivateContentQueryGrpcMapper privateContentQueryGrpcMapper;
    private final PrivateContentCommandGrpcMapper privateContentCommandGrpcMapper;

    private final GetAllContentUseCase getAllContentUseCase;
    private final GetContentByIdUseCase getContentByIdUseCase;
    private final CreateContentUseCase createContentUseCase;
    private final UpdateContentUseCase updateContentUseCase;
    private final DeleteContentUseCase deleteContentUseCase;

    @Autowired
	public PrivateContentGrpcService(
		ProtoPaginationMapper protoPaginationMapper,
		PrivateContentCommonGrpcMapper privateContentCommonGrpcMapper,
		PrivateContentQueryGrpcMapper privateContentQueryGrpcMapper,
		PrivateContentCommandGrpcMapper privateContentCommandGrpcMapper,

		GetAllContentUseCase getAllContentUseCase,
		GetContentByIdUseCase getContentByIdUseCase,
		CreateContentUseCase createContentUseCase,
		UpdateContentUseCase updateContentUseCase,
		DeleteContentUseCase deleteContentUseCase
	) {
		this.protoPaginationMapper = protoPaginationMapper;
		this.privateContentCommonGrpcMapper = privateContentCommonGrpcMapper;
		this.privateContentQueryGrpcMapper = privateContentQueryGrpcMapper;
		this.privateContentCommandGrpcMapper = privateContentCommandGrpcMapper;

		this.getAllContentUseCase = getAllContentUseCase;
		this.getContentByIdUseCase = getContentByIdUseCase;
		this.createContentUseCase = createContentUseCase;
		this.updateContentUseCase = updateContentUseCase;
		this.deleteContentUseCase = deleteContentUseCase;
	}


	@Override
    public void search(
        PrivateSearchContentRequest request,
        StreamObserver<PrivateSearchContentResponse> responseObserver
    ) {
        ContentSearchDto contentSearchDto = privateContentQueryGrpcMapper.toContentSearchDto(
            request
        );
        Pageable domainPageable = protoPaginationMapper.toDomainPageable(
            request.getPagination(),
            Set.of("alias", "season", "releaseDate", "createdAt", "updatedAt", "active", "sortOrder", "translations.title")
        );

        Page<ContentDto> contentDtoPage = getAllContentUseCase.get(domainPageable);

        PaginationResponse paginationResponse = protoPaginationMapper.toProtoPaginationResponse(contentDtoPage);
        List<PrivateContentResponse> contentResponseList = contentDtoPage.content()
            .stream()
            .map(privateContentCommonGrpcMapper::toPrivateContentResponse)
            .toList();

        responseObserver.onNext(
            privateContentQueryGrpcMapper.toPrivateSearchContentResponse(
                contentResponseList,
                paginationResponse
            )
        );
        responseObserver.onCompleted();
    }

    @Override
    public void get(
        PrivateContentProto.GetContentRequest request,
        StreamObserver<PrivateContentProto.PrivateContentResponse> responseObserver
    ) {
        ContentDto contentDto = getContentByIdUseCase.get(
            UUID.fromString(request.getUuid()),
            null,
            null
        );
        responseObserver.onNext(
            privateContentCommonGrpcMapper.toPrivateContentResponse(contentDto)
        );
        responseObserver.onCompleted();
    }

    @Override
    public void create(
        PrivateContentProto.CreateContentRequest request,
        StreamObserver<PrivateContentProto.PrivateContentResponse> responseObserver
    ) {
		ContentDto contentDto = privateContentCommandGrpcMapper.toContentDto(request);
		ContentDto created = createContentUseCase.create(contentDto);
		responseObserver.onNext(
			privateContentCommonGrpcMapper.toPrivateContentResponse(created)
		);
		responseObserver.onCompleted();
    }

    @Override
    public void update(
        PrivateContentProto.UpdateContentRequest request,
        StreamObserver<PrivateContentProto.PrivateContentResponse> responseObserver
    ) {
	    ContentDto contentDto = privateContentCommandGrpcMapper.toContentDto(request);
	    ContentDto updated = updateContentUseCase.update(contentDto);
	    responseObserver.onNext(
		    privateContentCommonGrpcMapper.toPrivateContentResponse(updated)
	    );
	    responseObserver.onCompleted();
    }

    @Override
    public void delete(
        PrivateContentProto.DeleteContentRequest request,
        StreamObserver<CommonProto.EmptyResponse> responseObserver
    ) {
        deleteContentUseCase.delete(
            UUID.fromString(request.getUuid())
        );
        responseObserver.onNext(
            CommonProto.EmptyResponse.newBuilder().build()
        );
        responseObserver.onCompleted();
    }
}
