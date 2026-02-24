package dev.animedia.contentservice.genre.api;

import dev.animedia.contentservice.app.FieldValidator;
import dev.animedia.contentservice.app.PaginationMapper;
import dev.animedia.contentservice.genre.mapper.GrpcGenreMapper;
import dev.animedia.contentservice.genre.service.GenreTranslationCommandService;
import dev.animedia.contentservice.genre.service.GenreTranslationPageService;
import dev.animedia.grpc.common.CommonProto;
import dev.animedia.grpc.genre.*;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class GrpcGenreTranslationPrivateService extends GenreTranslationPrivateServiceGrpc.GenreTranslationPrivateServiceImplBase {

	private final GenreTranslationCommandService genreTranslationCommandService;
	private final GenreTranslationPageService genreTranslationPageService;
	private final PaginationMapper paginationMapper;
	private final GrpcGenreMapper grpcGenreMapper;
	private final FieldValidator fieldValidator;

	@Autowired
	public GrpcGenreTranslationPrivateService(
		GenreTranslationCommandService genreTranslationCommandService,
		GenreTranslationPageService genreTranslationPageService,
		PaginationMapper paginationMapper,
		GrpcGenreMapper grpcGenreMapper,
		FieldValidator fieldValidator
	) {
		this.genreTranslationCommandService = genreTranslationCommandService;
		this.genreTranslationPageService = genreTranslationPageService;
		this.paginationMapper = paginationMapper;
		this.grpcGenreMapper = grpcGenreMapper;
		this.fieldValidator = fieldValidator;
	}

	@Override
	public void search(
		GenreTranslationPrivateProto.SearchRequest request,
		StreamObserver<GenreTranslationPrivateProto.SearchResponse> responseObserver
	) {
		var paginationRequest = paginationMapper.toPageable(request.getPagination());
		var genreTranslationsResponseDto = genreTranslationPageService.search(
			request.hasGenreId() ? request.getGenreId() : null,
			request.getNamesList(),
			request.getLanguageCodesList(),
			paginationRequest
		);

		var pagination = paginationMapper.toProtoPaginationResponse(genreTranslationsResponseDto);
		var genreTranslationsResponse = grpcGenreMapper.toProtoGenreTranslations(genreTranslationsResponseDto.getContent());

		var response = GenreTranslationPrivateProto.SearchResponse.newBuilder()
			.addAllTranslations(genreTranslationsResponse)
			.setPagination(pagination)
			.build();

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public void create(
		GenreTranslationPrivateProto.CreateRequest request,
		StreamObserver<GenreCommonProto.GenreTranslationResponse> responseObserver
	) {
		var genreTranslationRequestDto = grpcGenreMapper.toGenreTranslationRequestDto(request);
		fieldValidator.validate(genreTranslationRequestDto);
		var createdGenreTranslationResponseDto = genreTranslationCommandService.create(genreTranslationRequestDto);

		var response = grpcGenreMapper.toProtoGenreTranslation(createdGenreTranslationResponseDto);

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public void update(
		GenreTranslationPrivateProto.UpdateRequest request,
		StreamObserver<GenreCommonProto.GenreTranslationResponse> responseObserver
	) {
		var genreTranslationRequestDto = grpcGenreMapper.toGenreTranslationRequestDto(request);
		fieldValidator.validate(genreTranslationRequestDto);
		var updatedGenreTranslationResponseDto = genreTranslationCommandService.update(request.getId(), genreTranslationRequestDto);

		var response = grpcGenreMapper.toProtoGenreTranslation(updatedGenreTranslationResponseDto);

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public void delete(
		GenreTranslationPrivateProto.DeleteRequest request,
		StreamObserver<CommonProto.EmptyResponse> responseObserver
	) {
		genreTranslationCommandService.delete(request.getId());
		responseObserver.onCompleted();
	}

	@Override
	public void deleteBatch(
		GenreTranslationPrivateProto.DeleteBatchRequest request,
		StreamObserver<CommonProto.EmptyResponse> responseObserver
	) {
		genreTranslationCommandService.delete(request.getIdsList());
		responseObserver.onCompleted();
	}
}