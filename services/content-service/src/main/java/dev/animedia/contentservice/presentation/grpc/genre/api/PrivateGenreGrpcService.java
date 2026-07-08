package dev.animedia.contentservice.presentation.grpc.genre.api;

import dev.animedia.contentservice.application.genre.dto.GenreDto;
import dev.animedia.contentservice.application.genre.dto.GenreSearchDto;
import dev.animedia.contentservice.application.genre.usecase.admin.CreateGenreUseCase;
import dev.animedia.contentservice.application.genre.usecase.admin.DeleteGenreUseCase;
import dev.animedia.contentservice.application.genre.usecase.admin.GetGenreUseCase;
import dev.animedia.contentservice.application.genre.usecase.admin.UpdateGenreUseCase;
import dev.animedia.contentservice.domain.shared.pagination.Page;
import dev.animedia.contentservice.domain.shared.pagination.Pageable;
import dev.animedia.contentservice.presentation.grpc.genre.mapper.PrivateGenreGrpcMapper;
import dev.animedia.contentservice.presentation.grpc.shared.mapper.ProtoPaginationMapper;
import dev.animedia.grpc.common.CommonProto;
import dev.animedia.grpc.common.CommonProto.EmptyResponse;
import dev.animedia.grpc.common.CommonProto.PaginationResponse;
import dev.animedia.grpc.genre.PrivateGenreProto.*;
import dev.animedia.grpc.genre.PrivateGenreServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@GrpcService
public class PrivateGenreGrpcService extends PrivateGenreServiceGrpc.PrivateGenreServiceImplBase {
    private final PrivateGenreGrpcMapper privateGenreGrpcMapper;
    private final ProtoPaginationMapper protoPaginationMapper;

    private final SearchGenreUseCase searchGenreUseCase;
    private final GetGenreUseCase getGenreUseCase;
    private final CreateGenreUseCase createGenreUseCase;
    private final UpdateGenreUseCase updateGenreUseCase;
    private final DeleteGenreUseCase deleteGenreUseCase;

    @Autowired
    public PrivateGenreGrpcService(
	    PrivateGenreGrpcMapper privateGenreGrpcMapper,
        ProtoPaginationMapper protoPaginationMapper,
	    SearchGenreUseCase searchGenreUseCase,
	    GetGenreUseCase getGenreUseCase,
	    CreateGenreUseCase createGenreUseCase,
	    UpdateGenreUseCase updateGenreUseCase,
	    DeleteGenreUseCase deleteGenreUseCase
    ) {
	    this.privateGenreGrpcMapper = privateGenreGrpcMapper;
	    this.protoPaginationMapper = protoPaginationMapper;
	    this.searchGenreUseCase = searchGenreUseCase;
	    this.getGenreUseCase = getGenreUseCase;
	    this.createGenreUseCase = createGenreUseCase;
	    this.updateGenreUseCase = updateGenreUseCase;
	    this.deleteGenreUseCase = deleteGenreUseCase;
    }

    @Override
    public void search(
        PrivateSearchGenreRequest request,
        StreamObserver<PrivateSearchGenreResponse> responseObserver
    ) {
        Pageable pageableRequest = protoPaginationMapper.toDomainPageable(
            request.getPagination(),
            Set.of("alias", "sortOrder", "active", "translations.name", "translations.languageCode")
        );
        GenreSearchDto genreSearchDto = privateGenreGrpcMapper.toGenreSearchDto(request);

        Page<GenreDto> genreDtoPage = searchGenreUseCase.search(genreSearchDto, pageableRequest);

        PaginationResponse paginationResponse = protoPaginationMapper.toProtoPaginationResponse(genreDtoPage);
        List<PrivateGenreResponse> genreResponseList = genreDtoPage.content() != null
            ? genreDtoPage.content()
                .stream()
                .map(privateGenreGrpcMapper::toPrivateGenreResponse)
                .toList()
            : List.of();

        responseObserver.onNext(
	        privateGenreGrpcMapper.toPrivateSearchGenreResponse(
				genreResponseList,
		        paginationResponse
	        )
        );
        responseObserver.onCompleted();
    }

    @Override
    public void get(
        GetGenreRequest request,
        StreamObserver<PrivateGenreResponse> responseObserver
    ) {
        GenreDto genreDto = getGenreUseCase.get(UUID.fromString(request.getUuid()), null, null);
        responseObserver.onNext(
            privateGenreGrpcMapper.toPrivateGenreResponse(genreDto)
        );
        responseObserver.onCompleted();
    }

    @Override
    public void create(
        CreateGenreRequest request,
        StreamObserver<PrivateGenreResponse> responseObserver
    ) {
		GenreDto genreDto = privateGenreGrpcMapper.toGenreDto(request);
		GenreDto created = createGenreUseCase.create(genreDto);
		responseObserver.onNext(
			privateGenreGrpcMapper.toPrivateGenreResponse(created)
		);
		responseObserver.onCompleted();
    }

    @Override
    public void update(
        UpdateGenreRequest request,
        StreamObserver<PrivateGenreResponse> responseObserver
    ) {
		GenreDto genreDto = privateGenreGrpcMapper.toGenreDto(request);
		GenreDto updated = updateGenreUseCase.update(genreDto);
		responseObserver.onNext(
			privateGenreGrpcMapper.toPrivateGenreResponse(updated)
		);
		responseObserver.onCompleted();
    }

    @Override
    public void delete(
        DeleteGenreRequest request,
        StreamObserver<EmptyResponse> responseObserver
    ) {
        deleteGenreUseCase.delete(UUID.fromString(request.getUuid()));
	    responseObserver.onNext(
		    CommonProto.EmptyResponse.newBuilder().build()
	    );
		responseObserver.onCompleted();
    }
}
