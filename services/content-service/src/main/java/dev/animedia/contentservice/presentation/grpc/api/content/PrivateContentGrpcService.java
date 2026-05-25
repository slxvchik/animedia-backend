package dev.animedia.contentservice.presentation.grpc.api.content;

import dev.animedia.contentservice.old.app.FieldValidator;
import dev.animedia.contentservice.presentation.grpc.config.LanguageInterceptor;
import dev.animedia.contentservice.presentation.grpc.mapper.PaginationMapper;
import dev.animedia.contentservice.old.content.dto.request.ContentRequestDto;
import dev.animedia.contentservice.old.content.dto.request.PrivateSearchRequestDto;
import dev.animedia.contentservice.old.content.mapper.ContentSortMapper;
import dev.animedia.contentservice.old.content.mapper.GrpcContentMapper;
import dev.animedia.contentservice.old.content.service.ContentCommandService;
import dev.animedia.contentservice.old.content.service.ContentSearchService;
import dev.animedia.grpc.common.CommonProto;
import dev.animedia.grpc.core.PrivateContentProto;
import dev.animedia.grpc.core.PrivateContentServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.grpc.server.service.GrpcService;

import java.util.UUID;

@GrpcService
public class PrivateContentGrpcService extends PrivateContentServiceGrpc.PrivateContentServiceImplBase {

    private final ContentCommandService contentCommandService;
    private final ContentSearchService contentSearchService;
    private final PaginationMapper paginationMapper;
    private final ContentSortMapper contentSortMapper;
    private final GrpcContentMapper grpcContentMapper;
    private final FieldValidator fieldValidator;

    @Autowired
    public PrivateContentGrpcService(
        ContentCommandService contentCommandService,
        ContentSearchService contentSearchService,
        PaginationMapper paginationMapper,
        ContentSortMapper contentSortMapper,
        GrpcContentMapper grpcContentMapper,
	    FieldValidator fieldValidator
    ) {
        this.contentCommandService = contentCommandService;
        this.contentSearchService = contentSearchService;
        this.paginationMapper = paginationMapper;
        this.contentSortMapper = contentSortMapper;
        this.grpcContentMapper = grpcContentMapper;
	    this.fieldValidator = fieldValidator;
    }

    @Override
    public void search(PrivateContentProto.PrivateSearchRequest request, StreamObserver<PrivateContentProto.PrivateSearchResponse> responseObserver) {
        String languageCode = LanguageInterceptor.getLanguageCode();
        PrivateSearchRequestDto searchRequestDto = grpcContentMapper.toPrivateSearchRequestDto(request);
        Pageable pageable = request.hasSort()
            ? paginationMapper.toPageableWithSort(request.getPagination(), contentSortMapper.toSort(request.getSort()))
            : paginationMapper.toPageable(request.getPagination());
        var contentsWithTranslations = contentSearchService.search(searchRequestDto, languageCode, pageable);
        var pagination = paginationMapper.toProtoPaginationResponse(contentsWithTranslations);
        responseObserver.onNext(grpcContentMapper.toPrivateSearchResponse(contentsWithTranslations.getContent(), pagination));
        responseObserver.onCompleted();
    }

    @Override
    public void create(PrivateContentProto.PrivateCreateRequest request, StreamObserver<PrivateContentProto.PrivateContentResponse> responseObserver) {
        ContentRequestDto contentRequestDto = grpcContentMapper.toContentRequestDto(request);
        fieldValidator.validate(contentRequestDto);
        String languageCode = LanguageInterceptor.getLanguageCode();
        var createdContent = contentCommandService.create(contentRequestDto, languageCode);
        responseObserver.onNext(grpcContentMapper.toPrivateContentResponse(createdContent));
        responseObserver.onCompleted();
    }

    @Override
    public void update(PrivateContentProto.PrivateUpdateRequest request, StreamObserver<PrivateContentProto.PrivateContentResponse> responseObserver) {
        ContentRequestDto contentRequestDto = grpcContentMapper.toContentRequestDto(request);
        fieldValidator.validate(contentRequestDto);
        String languageCode = LanguageInterceptor.getLanguageCode();
        var updatedContent = contentCommandService.update(UUID.fromString(request.getUuid()), contentRequestDto, languageCode);
        responseObserver.onNext(grpcContentMapper.toPrivateContentResponse(updatedContent));
        responseObserver.onCompleted();
    }

    @Override
    public void delete(PrivateContentProto.PrivateDeleteRequest request, StreamObserver<CommonProto.EmptyResponse> responseObserver) {
        super.delete(request, responseObserver);
    }
}
