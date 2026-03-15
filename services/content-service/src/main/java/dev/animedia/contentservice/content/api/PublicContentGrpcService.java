package dev.animedia.contentservice.content.api;

import dev.animedia.contentservice.app.config.LanguageInterceptor;
import dev.animedia.contentservice.app.mapper.PaginationMapper;
import dev.animedia.contentservice.content.dto.request.PublicSearchRequestDto;
import dev.animedia.contentservice.content.mapper.ContentSortMapper;
import dev.animedia.contentservice.content.mapper.GrpcContentMapper;
import dev.animedia.contentservice.content.service.ContentQueryService;
import dev.animedia.contentservice.content.service.ContentSearchService;
import dev.animedia.grpc.core.PublicContentProto;
import dev.animedia.grpc.core.PublicContentServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class PublicContentGrpcService extends PublicContentServiceGrpc.PublicContentServiceImplBase {

    private final ContentSearchService contentSearchService;
    private final GrpcContentMapper grpcContentMapper;
    private final PaginationMapper paginationMapper;
    private final ContentSortMapper contentSortMapper;
    private final ContentQueryService contentQueryService;

    @Autowired
	public PublicContentGrpcService(
        ContentSearchService contentSearchService,
        GrpcContentMapper grpcContentMapper,
        PaginationMapper paginationMapper,
        ContentSortMapper contentSortMapper,
        ContentQueryService contentQueryService
    ) {
		this.contentSearchService = contentSearchService;
	    this.grpcContentMapper = grpcContentMapper;
        this.paginationMapper = paginationMapper;
        this.contentSortMapper = contentSortMapper;
        this.contentQueryService = contentQueryService;
    }

	@Override
    public void search(PublicContentProto.PublicSearchRequest request, StreamObserver<PublicContentProto.PublicSearchResponse> responseObserver) {
        String languageCode = LanguageInterceptor.getLanguageCode();
        PublicSearchRequestDto searchRequestDto = grpcContentMapper.toPublicSearchRequestDto(request);
        Pageable pageable = request.hasSort()
            ? paginationMapper.toPageableWithSort(request.getPagination(), contentSortMapper.toSort(request.getSort()))
            : paginationMapper.toPageable(request.getPagination());
        var contentsWithTranslation = contentSearchService.search(searchRequestDto, languageCode, pageable);
        var pagination = paginationMapper.toProtoPaginationResponse(contentsWithTranslation);
        responseObserver.onNext(grpcContentMapper.toPublicSearchResponse(contentsWithTranslation.getContent(), pagination));
        responseObserver.onCompleted();
    }

    @Override
    public void get(PublicContentProto.PublicGetRequest request, StreamObserver<PublicContentProto.PublicContentResponseWithTranslation> responseObserver) {
        String languageCode = LanguageInterceptor.getLanguageCode();
        var content = contentQueryService.findByAlias(request.getAlias(), languageCode);
        responseObserver.onNext(grpcContentMapper.toPublicContentResponseWithTranslation(content));
        responseObserver.onCompleted();
    }
}
