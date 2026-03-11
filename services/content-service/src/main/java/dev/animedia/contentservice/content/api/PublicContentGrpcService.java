package dev.animedia.contentservice.content.api;

import dev.animedia.grpc.core.PublicContentProto;
import dev.animedia.grpc.core.PublicContentServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class PublicContentGrpcService extends PublicContentServiceGrpc.PublicContentServiceImplBase {
    @Override
    public void search(PublicContentProto.PublicSearchRequest request, StreamObserver<PublicContentProto.PublicSearchResponse> responseObserver) {
        super.search(request, responseObserver);
    }

    @Override
    public void get(PublicContentProto.PublicGetRequest request, StreamObserver<PublicContentProto.PublicContentResponseWithTranslation> responseObserver) {
        super.get(request, responseObserver);
    }
}
