package dev.animedia.contentservice.genre.api;

import dev.animedia.contentservice.app.FieldValidator;
import dev.animedia.contentservice.app.PaginationMapper;
import dev.animedia.contentservice.genre.dto.request.GenreRequestDto;
import dev.animedia.contentservice.genre.mapper.GrpcGenreMapper;
import dev.animedia.contentservice.genre.service.GenreCommandService;
import dev.animedia.contentservice.genre.service.GenrePageService;
import dev.animedia.grpc.common.CommonProto;
import dev.animedia.grpc.genre.GenreCommonProto;
import dev.animedia.grpc.genre.PrivateGenreProto;
import dev.animedia.grpc.genre.PrivateGenreServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

import java.util.List;

@GrpcService
public class PrivateGenreGrpcService extends PrivateGenreServiceGrpc.PrivateGenreServiceImplBase {

    private final GenrePageService genrePageService;
    private final GenreCommandService genreCommandService;
    private final GrpcGenreMapper grpcGenreMapper;
    private final PaginationMapper paginationMapper;
    private final FieldValidator fieldValidator;

    @Autowired
    public PrivateGenreGrpcService(
        GenrePageService genrePageService,
        GenreCommandService genreCommandService,
        GrpcGenreMapper grpcGenreMapper,
        PaginationMapper paginationMapper,
        FieldValidator fieldValidator
    ) {
        this.genrePageService = genrePageService;
        this.genreCommandService = genreCommandService;
        this.grpcGenreMapper = grpcGenreMapper;
        this.paginationMapper = paginationMapper;
        this.fieldValidator = fieldValidator;
    }

    @Override
    public void search(PrivateGenreProto.PrivateSearchRequest request, StreamObserver<PrivateGenreProto.PrivateSearchResponse> responseObserver) {
        var pageable = paginationMapper.toPageable(request.getPagination());
        var genresWithTranslations = genrePageService.search(request.getAliasList(), request.getNamesList(), request.getLanguageCodesList(), pageable);

        var protoPagination = paginationMapper.toProtoPaginationResponse(genresWithTranslations);
        PrivateGenreProto.PrivateSearchResponse response = grpcGenreMapper.toPrivateSearchResponse(genresWithTranslations.getContent(), protoPagination);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void create(PrivateGenreProto.PrivateCreateRequest request, StreamObserver<GenreCommonProto.GenreResponse> responseObserver) {
        GenreRequestDto genreRequestDto = grpcGenreMapper.toGenreRequestDto(request);
        fieldValidator.validate(genreRequestDto);
        var genreResponseDto = genreCommandService.create(genreRequestDto);

        var response = grpcGenreMapper.toProtoGenre(genreResponseDto);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void createBatch(PrivateGenreProto.PrivateCreateBatchRequest request, StreamObserver<GenreCommonProto.GenreResponseList> responseObserver) {
        List<GenreRequestDto> genresRequestDto = grpcGenreMapper.toGenresRequestDto(request);
        fieldValidator.validate(genresRequestDto);
        var genresResponseDto = genreCommandService.create(genresRequestDto);

        var response = grpcGenreMapper.toProtoGenres(genresResponseDto);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void update(PrivateGenreProto.PrivateUpdateRequest request, StreamObserver<GenreCommonProto.GenreResponse> responseObserver) {
        GenreRequestDto genreRequestDto = grpcGenreMapper.toGenreRequestDto(request);
        var genreResponseDto = genreCommandService.update(request.getId(), genreRequestDto);

        var response = grpcGenreMapper.toProtoGenre(genreResponseDto);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void delete(PrivateGenreProto.PrivateDeleteRequest request, StreamObserver<CommonProto.EmptyResponse> responseObserver) {
        genreCommandService.delete(request.getId());
        responseObserver.onCompleted();
    }

    @Override
    public void deleteBatch(PrivateGenreProto.PrivateDeleteBatchRequest request, StreamObserver<CommonProto.EmptyResponse> responseObserver) {
        genreCommandService.delete(request.getIdsList());
        responseObserver.onCompleted();
    }
}
