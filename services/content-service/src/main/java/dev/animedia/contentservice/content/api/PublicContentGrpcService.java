package dev.animedia.contentservice.content.api;

import dev.animedia.contentservice.content.dto.request.PublicSearchRequestDto;
import dev.animedia.contentservice.content.mapper.GrpcContentMapper;
import dev.animedia.contentservice.content.service.ContentSearchService;
import dev.animedia.grpc.core.PublicContentProto;
import dev.animedia.grpc.core.PublicContentServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class PublicContentGrpcService extends PublicContentServiceGrpc.PublicContentServiceImplBase {

    private final ContentSearchService contentSearchService;
    private final GrpcContentMapper grpcContentMapper;

    @Autowired
	public PublicContentGrpcService(
        ContentSearchService contentSearchService,
        GrpcContentMapper grpcContentMapper
    ) {
		this.contentSearchService = contentSearchService;
	    this.grpcContentMapper = grpcContentMapper;
    }

	@Override
    public void search(PublicContentProto.PublicSearchRequest request, StreamObserver<PublicContentProto.PublicSearchResponse> responseObserver) {
        PublicSearchRequestDto searchRequestDto = grpcContentMapper.toPublicSearchRequestDto(request);
        contentSearchService.search(searchRequestDto);
        super.search(request, responseObserver);
    }

    @Override
    public void get(PublicContentProto.PublicGetRequest request, StreamObserver<PublicContentProto.PublicContentResponseWithTranslation> responseObserver) {
        super.get(request, responseObserver);
    }
}
