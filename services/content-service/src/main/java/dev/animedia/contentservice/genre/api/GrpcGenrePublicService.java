package dev.animedia.contentservice.genre.api;

import dev.animedia.contentservice.genre.service.GenrePageService;
import dev.animedia.grpc.genre.GenrePublicProto;
import dev.animedia.grpc.genre.GenrePublicServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class GrpcGenrePublicService extends GenrePublicServiceGrpc.GenrePublicServiceImplBase {

    private final GenrePageService genrePageService;

    @Autowired
    public GrpcGenrePublicService(GenrePageService genrePageService) {
        this.genrePageService = genrePageService;
    }

    @Override
    public void search(GenrePublicProto.SearchRequest request, StreamObserver<GenrePublicProto.SearchRequest> responseObserver) {
        super.search(request, responseObserver);
        genrePageService.search(request.getAliasList(), request.getNameList(), )
    }
}
