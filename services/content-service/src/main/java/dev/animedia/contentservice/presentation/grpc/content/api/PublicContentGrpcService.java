package dev.animedia.contentservice.presentation.grpc.content.api;

import dev.animedia.contentservice.presentation.grpc.shared.mapper.ProtoPaginationMapper;
import dev.animedia.grpc.core.PublicContentProto;
import dev.animedia.grpc.core.PublicContentServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class PublicContentGrpcService extends PublicContentServiceGrpc.PublicContentServiceImplBase {

    private final ProtoPaginationMapper protoPaginationMapper;

    @Autowired
	public PublicContentGrpcService(
        ProtoPaginationMapper protoPaginationMapper
    ) {
        this.protoPaginationMapper = protoPaginationMapper;
    }

    @Override
    public void search(
        PublicContentProto.PublicSearchRequest request,
        StreamObserver<PublicContentProto.PublicSearchResponse> responseObserver
    ) {
        super.search(request, responseObserver);
    }

    @Override
    public void get(
        PublicContentProto.GetContentByDetailsRequest request,
        StreamObserver<PublicContentProto.PublicContentResponse> responseObserver
    ) {
        super.get(request, responseObserver);
    }
}
