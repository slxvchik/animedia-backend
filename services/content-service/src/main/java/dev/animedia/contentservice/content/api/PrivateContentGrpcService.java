package dev.animedia.contentservice.content.api;

import dev.animedia.grpc.common.CommonProto;
import dev.animedia.grpc.core.PrivateContentProto;
import dev.animedia.grpc.core.PrivateContentServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class PrivateContentGrpcService extends PrivateContentServiceGrpc.PrivateContentServiceImplBase {
    @Override
    public void search(PrivateContentProto.PrivateSearchRequest request, StreamObserver<PrivateContentProto.PrivateSearchResponse> responseObserver) {
        super.search(request, responseObserver);
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
