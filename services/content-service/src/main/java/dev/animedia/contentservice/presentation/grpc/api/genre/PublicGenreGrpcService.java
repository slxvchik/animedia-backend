package dev.animedia.contentservice.presentation.grpc.api.genre;

import dev.animedia.contentservice.presentation.grpc.mapper.PaginationMapper;
import dev.animedia.contentservice.presentation.grpc.config.LanguageInterceptor;
import dev.animedia.contentservice.old.genre.mapper.GrpcGenreMapper;
import dev.animedia.contentservice.old.genre.service.GenrePageService;
import dev.animedia.grpc.genre.PublicGenreProto;
import dev.animedia.grpc.genre.PublicGenreServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class PublicGenreGrpcService extends PublicGenreServiceGrpc.PublicGenreServiceImplBase {

    private final GenrePageService genrePageService;
    private final PaginationMapper paginationMapper;
    private final GrpcGenreMapper grpcGenreMapper;

    @Autowired
    public PublicGenreGrpcService(GenrePageService genrePageService, PaginationMapper paginationMapper,
                                  GrpcGenreMapper grpcGenreMapper
    ) {
        this.genrePageService = genrePageService;
        this.paginationMapper = paginationMapper;
        this.grpcGenreMapper = grpcGenreMapper;
    }

    @Override
    public void search(PublicGenreProto.PublicSearchRequest request, StreamObserver<PublicGenreProto.PublicSearchResponse> responseObserver) {
        String languageCode = LanguageInterceptor.getLanguageCode();
        var pagination = paginationMapper.toPageable(request.getPagination());
        var genresWithTranslationResponseDto = genrePageService.search(request.getAliasList(), request.getNameList(), languageCode, pagination);

        var paginationResponse = paginationMapper.toProtoPaginationResponse(genresWithTranslationResponseDto);
        var response = grpcGenreMapper.toPublicSearchResponse(genresWithTranslationResponseDto.getContent(), paginationResponse);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
