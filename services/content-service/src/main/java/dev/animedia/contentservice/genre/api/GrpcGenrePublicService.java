package dev.animedia.contentservice.genre.api;

import dev.animedia.contentservice.app.PaginationMapper;
import dev.animedia.contentservice.app.config.LanguageInterceptor;
import dev.animedia.contentservice.genre.mapper.GrpcGenreMapper;
import dev.animedia.contentservice.genre.service.GenrePageService;
import dev.animedia.grpc.genre.GenrePublicProto;
import dev.animedia.grpc.genre.GenrePublicServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class GrpcGenrePublicService extends GenrePublicServiceGrpc.GenrePublicServiceImplBase {

    private final GenrePageService genrePageService;
    private final PaginationMapper paginationMapper;
    private final GrpcGenreMapper grpcGenreMapper;

    @Autowired
    public GrpcGenrePublicService(GenrePageService genrePageService, PaginationMapper paginationMapper,
        GrpcGenreMapper grpcGenreMapper
    ) {
        this.genrePageService = genrePageService;
        this.paginationMapper = paginationMapper;
        this.grpcGenreMapper = grpcGenreMapper;
    }

    @Override
    public void search(GenrePublicProto.SearchRequest request, StreamObserver<GenrePublicProto.SearchResponse> responseObserver) {
        String languageCode = LanguageInterceptor.getLanguageCode();
        var pagination = paginationMapper.toPageable(request.getPagination());
        var genresWithTranslationResponseDto = genrePageService.search(request.getAliasList(), request.getNameList(), languageCode, pagination);

        var paginationResponse = paginationMapper.toProtoPaginationResponse(genresWithTranslationResponseDto);
        var response = grpcGenreMapper.toPublicSearchResponse(genresWithTranslationResponseDto.getContent(), paginationResponse);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
