package dev.animedia.contentservice.content.api;

import dev.animedia.contentservice.app.config.LanguageInterceptor;
import dev.animedia.contentservice.app.mapper.PaginationMapper;
import dev.animedia.contentservice.content.dto.request.PrivateSearchRequestDto;
import dev.animedia.contentservice.content.mapper.ContentSortMapper;
import dev.animedia.contentservice.content.mapper.GrpcContentMapper;
import dev.animedia.contentservice.content.service.ContentCommandService;
import dev.animedia.contentservice.content.service.ContentSearchService;
import dev.animedia.grpc.common.CommonProto;
import dev.animedia.grpc.core.PrivateContentProto;
import dev.animedia.grpc.core.PrivateContentServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class PrivateContentGrpcService extends PrivateContentServiceGrpc.PrivateContentServiceImplBase {

    private final ContentCommandService contentCommandService;
    private final ContentSearchService contentSearchService;
    private final PaginationMapper paginationMapper;
    private final ContentSortMapper contentSortMapper;
    private final GrpcContentMapper grpcContentMapper;

    @Autowired
    public PrivateContentGrpcService(ContentCommandService contentCommandService, ContentSearchService contentSearchService, PaginationMapper paginationMapper, ContentSortMapper contentSortMapper, GrpcContentMapper grpcContentMapper) {
        this.contentCommandService = contentCommandService;
        this.contentSearchService = contentSearchService;
        this.paginationMapper = paginationMapper;
        this.contentSortMapper = contentSortMapper;
        this.grpcContentMapper = grpcContentMapper;
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
        super.create(request, responseObserver);
    }

    @Override
    public void update(PrivateContentProto.PrivateUpdateRequest request, StreamObserver<PrivateContentProto.PrivateContentResponse> responseObserver) {
        super.update(request, responseObserver);
    }

    @Override
    public void delete(PrivateContentProto.PrivateDeleteRequest request, StreamObserver<CommonProto.EmptyResponse> responseObserver) {
        super.delete(request, responseObserver);
    }
}
