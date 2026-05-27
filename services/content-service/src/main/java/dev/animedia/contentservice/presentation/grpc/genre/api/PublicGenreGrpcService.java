package dev.animedia.contentservice.presentation.grpc.genre.api;

import dev.animedia.contentservice.presentation.grpc.shared.mapper.ProtoPaginationMapper;
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
    private final ProtoPaginationMapper protoPaginationMapper;
    private final GrpcGenreMapper grpcGenreMapper;

    @Autowired
    public PublicGenreGrpcService(GenrePageService genrePageService, ProtoPaginationMapper protoPaginationMapper,
                                  GrpcGenreMapper grpcGenreMapper
    ) {
        this.genrePageService = genrePageService;
        this.protoPaginationMapper = protoPaginationMapper;
        this.grpcGenreMapper = grpcGenreMapper;
    }

    @Override
    public void search(PublicGenreProto.PublicSearchRequest request, StreamObserver<PublicGenreProto.PublicSearchResponse> responseObserver) {
        String languageCode = LanguageInterceptor.getLanguageCode();
        var pagination = protoPaginationMapper.toPageable(request.getPagination());
        var genresWithTranslationResponseDto = genrePageService.search(request.getAliasList(), request.getNameList(), languageCode, pagination);

        var paginationResponse = protoPaginationMapper.toProtoPaginationResponse(genresWithTranslationResponseDto);
        var response = grpcGenreMapper.toPublicSearchResponse(genresWithTranslationResponseDto.getContent(), paginationResponse);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
