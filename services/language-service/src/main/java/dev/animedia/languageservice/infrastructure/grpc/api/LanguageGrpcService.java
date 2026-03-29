package dev.animedia.languageservice.infrastructure.grpc.api;

import dev.animedia.grpc.common.CommonProto;
import dev.animedia.grpc.language.LanguageProto;
import dev.animedia.grpc.language.LanguageServiceGrpc;
import dev.animedia.languageservice.application.usecase.*;
import dev.animedia.languageservice.infrastructure.grpc.mapper.LanguageGrpcMapper;
import dev.animedia.languageservice.infrastructure.grpc.mapper.PaginationGrpcMapper;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;


@GrpcService
public class LanguageGrpcService extends LanguageServiceGrpc.LanguageServiceImplBase {
	private final LanguageGrpcMapper languageGrpcMapper;
	private final PaginationGrpcMapper paginationGrpcMapper;

	private final GetLanguageUseCase getLanguageUseCase;
	private final SearchLanguageUseCase searchLanguageUseCase;
	private final CreateLanguageUseCase createLanguageUseCase;
	private final UpdateLanguageUseCase updateLanguageUseCase;
	private final DeleteLanguageUseCase deleteLanguageUseCase;

	@Autowired
	public LanguageGrpcService(
		LanguageGrpcMapper languageGrpcMapper,
		PaginationGrpcMapper paginationGrpcMapper,

		GetLanguageUseCase getLanguageUseCase,
		SearchLanguageUseCase searchLanguageUseCase,
		CreateLanguageUseCase createLanguageUseCase,
		UpdateLanguageUseCase updateLanguageUseCase,
		DeleteLanguageUseCase deleteLanguageUseCase
	) {
        this.languageGrpcMapper = languageGrpcMapper;

        this.getLanguageUseCase = getLanguageUseCase;
        this.searchLanguageUseCase = searchLanguageUseCase;
        this.createLanguageUseCase = createLanguageUseCase;
        this.updateLanguageUseCase = updateLanguageUseCase;
        this.deleteLanguageUseCase = deleteLanguageUseCase;
		this.paginationGrpcMapper = paginationGrpcMapper;
	}

	@Override
	public void get(
		LanguageProto.GetLanguageRequest request,
		StreamObserver<LanguageProto.LanguageResponse> responseObserver
	) {
		String languageCode = request.getCode();
		var languageDto = getLanguageUseCase.getByCode(languageCode);

		var response = languageGrpcMapper.toProtoResponse(languageDto);

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public void search(
		LanguageProto.SearchLanguageRequest request,
		StreamObserver<CommonProto.PaginationResponse> responseObserver
	) {
		var searchLanguageDto = languageGrpcMapper.toSearchLanguageDto(request);
		var pageLanguageDto = searchLanguageUseCase.search(searchLanguageDto);

		var response = paginationGrpcMapper.toProtoPaginationResponse(pageLanguageDto, languageGrpcMapper::toProtoResponse);

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public void create(
		LanguageProto.LanguageRequest request,
		StreamObserver<LanguageProto.LanguageResponse> responseObserver
	) {
		var languageDto = languageGrpcMapper.toLanguageDto(request);
		var created = createLanguageUseCase.create(languageDto);

		var response = languageGrpcMapper.toProtoResponse(created);

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public void update(
		LanguageProto.LanguageRequest request,
		StreamObserver<LanguageProto.LanguageResponse> responseObserver
	) {
		var languageDto = languageGrpcMapper.toLanguageDto(request);
		var updated = updateLanguageUseCase.update(languageDto);

		var response = languageGrpcMapper.toProtoResponse(updated);

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public void delete(
		LanguageProto.DeleteLanguageRequest request,
		StreamObserver<CommonProto.EmptyResponse> responseObserver
	) {
		String languageCode = request.getCode();
		deleteLanguageUseCase.delete(languageCode);

		responseObserver.onCompleted();
	}
}
