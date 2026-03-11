package dev.animedia.contentservice.content.api;

import dev.animedia.grpc.common.CommonProto;
import dev.animedia.grpc.core.ContentCommonProto;
import dev.animedia.grpc.core.PrivateContentTranslationProto;
import dev.animedia.grpc.core.PrivateContentTranslationServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class PrivateContentTranslationGrpcService extends PrivateContentTranslationServiceGrpc.PrivateContentTranslationServiceImplBase {
    @Override
    public void search(PrivateContentTranslationProto.PrivateTranslationSearchRequest request, StreamObserver<PrivateContentTranslationProto.PrivateTranslationSearchResponse> responseObserver) {
        super.search(request, responseObserver);
    }

    @Override
    public void create(PrivateContentTranslationProto.PrivateTranslationCreateRequest request, StreamObserver<ContentCommonProto.ContentTranslationResponse> responseObserver) {
        super.create(request, responseObserver);
    }

    @Override
    public void update(PrivateContentTranslationProto.PrivateTranslationUpdateRequest request, StreamObserver<ContentCommonProto.ContentTranslationResponse> responseObserver) {
        super.update(request, responseObserver);
    }

    @Override
    public void delete(PrivateContentTranslationProto.PrivateTranslationDeleteRequest request, StreamObserver<CommonProto.EmptyResponse> responseObserver) {
        super.delete(request, responseObserver);
    }
}
