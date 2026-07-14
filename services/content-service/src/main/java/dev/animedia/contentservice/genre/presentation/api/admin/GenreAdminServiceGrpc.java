package dev.animedia.contentservice.genre.presentation.api.admin;

import dev.animedia.contentservice.genre.application.dto.request.CreateGenreDto;
import dev.animedia.contentservice.genre.application.dto.request.UpdateGenreDto;
import dev.animedia.contentservice.genre.application.dto.response.GenreDto;
import dev.animedia.contentservice.genre.application.usecase.IndexAllGenreUseCase;
import dev.animedia.contentservice.shared.domain.pagination.Page;
import dev.animedia.contentservice.shared.domain.pagination.Pageable;
import dev.animedia.contentservice.genre.application.usecase.admin.CreateGenreUseCase;
import dev.animedia.contentservice.genre.application.usecase.admin.DeleteGenreUseCase;
import dev.animedia.contentservice.genre.application.usecase.admin.GetGenreDetailUseCase;
import dev.animedia.contentservice.genre.application.usecase.admin.UpdateGenreUseCase;
import dev.animedia.contentservice.shared.presentation.grpc.config.LanguageInterceptor;
import dev.animedia.contentservice.genre.presentation.mapper.admin.GenreAdminMapperGrpc;
import dev.animedia.contentservice.shared.presentation.grpc.mapper.ProtoPaginationMapper;
import dev.animedia.grpc.common.CommonProto;
import dev.animedia.grpc.genre.admin.v1.GenreAdminProto;
import dev.animedia.grpc.genre.admin.v1.GenreAdminProtoApi;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

import java.util.UUID;

@GrpcService
public class GenreAdminServiceGrpc extends dev.animedia.grpc.genre.admin.v1.GenreAdminServiceGrpc.GenreAdminServiceImplBase {
    private final GenreAdminMapperGrpc genreAdminMapperGrpc;
    private final ProtoPaginationMapper protoPaginationMapper;

    private final IndexAllGenreUseCase indexAllGenreUseCase;
    private final GetGenreDetailUseCase getGenreDetailUseCase;
    private final CreateGenreUseCase createGenreUseCase;
    private final UpdateGenreUseCase updateGenreUseCase;
    private final DeleteGenreUseCase deleteGenreUseCase;

    @Autowired
    public GenreAdminServiceGrpc(
	    GenreAdminMapperGrpc genreAdminMapperGrpc,
        ProtoPaginationMapper protoPaginationMapper,
	    IndexAllGenreUseCase indexAllGenreUseCase,
	    GetGenreDetailUseCase getGenreDetailUseCase,
	    CreateGenreUseCase createGenreUseCase,
	    UpdateGenreUseCase updateGenreUseCase,
	    DeleteGenreUseCase deleteGenreUseCase
    ) {
	    this.genreAdminMapperGrpc = genreAdminMapperGrpc;
	    this.protoPaginationMapper = protoPaginationMapper;
	    this.indexAllGenreUseCase = indexAllGenreUseCase;
	    this.getGenreDetailUseCase = getGenreDetailUseCase;
	    this.createGenreUseCase = createGenreUseCase;
	    this.updateGenreUseCase = updateGenreUseCase;
	    this.deleteGenreUseCase = deleteGenreUseCase;
    }

	@Override
	public void getAll(
		CommonProto.PaginationRequest request,
		StreamObserver<GenreAdminProtoApi.GetAllGenreResponse> responseObserver
	) {
		Pageable domainPageable = protoPaginationMapper.toDomainPageable(request);
		Page<GenreDto> genreDtoPage = indexAllGenreUseCase.index(domainPageable);
		var responseContent = genreDtoPage.content().stream()
			.map(genreAdminMapperGrpc::toGenreResponseGrpc)
			.toList();
		var responsePagination = protoPaginationMapper.toProtoPaginationResponse(genreDtoPage);
		responseObserver.onNext(
			GenreAdminProtoApi.GetAllGenreResponse.newBuilder()
				.addAllGenres(responseContent)
				.setPagination(responsePagination)
				.build()
		);
		responseObserver.onCompleted();
	}

	@Override
	public void get(
		GenreAdminProtoApi.GetGenreRequest request,
		StreamObserver<GenreAdminProto.GenreResponse> responseObserver
	) {
		GenreDto genreDto = getGenreDetailUseCase.get(
			UUID.fromString(request.getId()),
			LanguageInterceptor.getLanguageCode()
		);
		responseObserver.onNext(
			genreAdminMapperGrpc.toGenreResponseGrpc(genreDto)
		);
		responseObserver.onCompleted();
	}

	@Override
	public void create(
		GenreAdminProtoApi.CreateGenreRequest request,
		StreamObserver<GenreAdminProtoApi.CreateGenreResponse> responseObserver
	) {
		CreateGenreDto createDto = genreAdminMapperGrpc.toGenreDto(request);
		UUID createdId = createGenreUseCase.create(createDto);
		responseObserver.onNext(
			GenreAdminProtoApi.CreateGenreResponse.newBuilder()
				.setId(createdId.toString())
				.build()
		);
		responseObserver.onCompleted();
	}

	@Override
	public void update(
		GenreAdminProtoApi.UpdateGenreRequest request,
		StreamObserver<CommonProto.EmptyResponse> responseObserver
	) {
		UpdateGenreDto updateDto = genreAdminMapperGrpc.toGenreDto(request);
		updateGenreUseCase.update(updateDto);responseObserver.onNext(
			CommonProto.EmptyResponse.newBuilder().build()
		);
		responseObserver.onCompleted();
	}

	@Override
	public void delete(
		GenreAdminProtoApi.DeleteGenreRequest request,
		StreamObserver<CommonProto.EmptyResponse> responseObserver
	) {
		deleteGenreUseCase.delete(
			UUID.fromString(request.getId())
		);
		responseObserver.onNext(
			CommonProto.EmptyResponse.newBuilder().build()
		);
		responseObserver.onCompleted();
	}
}
