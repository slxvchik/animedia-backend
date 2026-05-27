package dev.animedia.contentservice.presentation.grpc.content.api;

import dev.animedia.contentservice.presentation.grpc.shared.mapper.ProtoPaginationMapper;
import dev.animedia.grpc.common.CommonProto;
import dev.animedia.grpc.core.PrivateContentProto;
import dev.animedia.grpc.core.PrivateContentServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class PrivateContentGrpcService extends PrivateContentServiceGrpc.PrivateContentServiceImplBase {

    private final ProtoPaginationMapper protoPaginationMapper;

    @Autowired
    public PrivateContentGrpcService(
        ProtoPaginationMapper protoPaginationMapper
    ) {
        this.protoPaginationMapper = protoPaginationMapper;
    }

    @Override
    public void search(
        PrivateContentProto.PrivateSearchContentRequest request,
        StreamObserver<PrivateContentProto.PrivateSearchContentResponse> responseObserver
    ) {
        super.search(request, responseObserver);
    }

    @Override
    public void get(
        PrivateContentProto.GetContentRequest request,
        StreamObserver<PrivateContentProto.PrivateContentResponse> responseObserver
    ) {
        super.get(request, responseObserver);
    }

    @Override
    public void create(
        PrivateContentProto.CreateContentRequest request,
        StreamObserver<PrivateContentProto.PrivateContentResponse> responseObserver
    ) {
        super.create(request, responseObserver);
    }

    @Override
    public void update(
        PrivateContentProto.UpdateContentRequest request,
        StreamObserver<PrivateContentProto.PrivateContentResponse> responseObserver
    ) {
        super.update(request, responseObserver);
    }

    @Override
    public void delete(
        PrivateContentProto.DeleteContentRequest request,
        StreamObserver<CommonProto.EmptyResponse> responseObserver
    ) {
        super.delete(request, responseObserver);
    }
}
