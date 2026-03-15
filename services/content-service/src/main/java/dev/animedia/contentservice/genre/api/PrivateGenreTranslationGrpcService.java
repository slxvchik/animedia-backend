package dev.animedia.contentservice.genre.api;

import dev.animedia.contentservice.app.FieldValidator;
import dev.animedia.contentservice.app.mapper.PaginationMapper;
import dev.animedia.contentservice.genre.mapper.GrpcGenreMapper;
import dev.animedia.contentservice.genre.service.GenreTranslationCommandService;
import dev.animedia.contentservice.genre.service.GenreTranslationPageService;
import dev.animedia.grpc.common.CommonProto;
import dev.animedia.grpc.genre.GenreCommonProto;
import dev.animedia.grpc.genre.PrivateGenreTranslationServiceGrpc;
import dev.animedia.grpc.genre.PrivateGenreTranslationProto;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class PrivateGenreTranslationGrpcService extends PrivateGenreTranslationServiceGrpc.PrivateGenreTranslationServiceImplBase {

	private final GenreTranslationCommandService genreTranslationCommandService;
	private final GenreTranslationPageService genreTranslationPageService;
	private final PaginationMapper paginationMapper;
	private final GrpcGenreMapper grpcGenreMapper;
	private final FieldValidator fieldValidator;

	@Autowired
	public PrivateGenreTranslationGrpcService(
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
		PrivateGenreTranslationProto.PrivateTranslationSearchRequest request,
		StreamObserver<PrivateGenreTranslationProto.PrivateTranslationSearchResponse> responseObserver
	) {
		var paginationRequest = paginationMapper.toPageable(request.getPagination());
		var genreTranslationsResponseDto = genreTranslationPageService.search(
			request.hasGenreId() ? request.getGenreId() : null,
			request.getNamesList(),
			request.getLanguageCodesList(),
			paginationRequest
		);

		var pagination = paginationMapper.toProtoPaginationResponse(genreTranslationsResponseDto);
		var genreTranslationsResponse = grpcGenreMapper.toProtoGenreTranslationList(genreTranslationsResponseDto.getContent());

		var response = PrivateGenreTranslationProto.PrivateTranslationSearchResponse.newBuilder()
			.addAllTranslations(genreTranslationsResponse)
			.setPagination(pagination)
			.build();

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public void create(
		PrivateGenreTranslationProto.PrivateTranslationCreateRequest request,
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
			PrivateGenreTranslationProto.PrivateTranslationUpdateRequest request,
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
		PrivateGenreTranslationProto.PrivateTranslationDeleteRequest request,
		StreamObserver<CommonProto.EmptyResponse> responseObserver
	) {
		genreTranslationCommandService.delete(request.getId());
		responseObserver.onCompleted();
	}

	@Override
	public void deleteBatch(
		PrivateGenreTranslationProto.PrivateTranslationDeleteBatchRequest request,
		StreamObserver<CommonProto.EmptyResponse> responseObserver
	) {
		genreTranslationCommandService.delete(request.getIdsList());
		responseObserver.onCompleted();
	}
}