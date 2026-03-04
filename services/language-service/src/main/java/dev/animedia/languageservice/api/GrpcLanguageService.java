package dev.animedia.languageservice.api;

import dev.animedia.grpc.common.CommonProto;
import dev.animedia.grpc.language.LanguageProto;
import dev.animedia.grpc.language.LanguageServiceGrpc;
import dev.animedia.languageservice.exception.FieldValidator;
import dev.animedia.languageservice.mapper.LanguageMapper;
import dev.animedia.languageservice.mapper.PaginationMapper;
import dev.animedia.languageservice.service.LanguageCommandService;
import dev.animedia.languageservice.service.LanguagePageService;
import dev.animedia.languageservice.service.LanguageQueryService;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;


@GrpcService
public class GrpcLanguageService extends LanguageServiceGrpc.LanguageServiceImplBase {

	private final LanguagePageService languagePageService;
	private final LanguageCommandService languageCommandService;
	private final LanguageMapper languageMapper;
	private final LanguageQueryService languageQueryService;
	private final PaginationMapper paginationMapper;
	private final FieldValidator fieldValidator;

	@Autowired
	public GrpcLanguageService(LanguagePageService languagePageService,
		LanguageCommandService languageCommandService,
		LanguageMapper languageMapper,
		LanguageQueryService languageQueryService,
		PaginationMapper paginationMapper, FieldValidator fieldValidator
	) {
		this.languagePageService = languagePageService;
		this.languageCommandService = languageCommandService;
		this.languageMapper = languageMapper;
		this.languageQueryService = languageQueryService;
		this.paginationMapper = paginationMapper;
		this.fieldValidator = fieldValidator;
	}

	@Override
	public void get(
		LanguageProto.GetLanguageRequest request,
		StreamObserver<LanguageProto.LanguageResponse> responseObserver
	) {
		String languageCode = request.getCode();
		var languageResponseDto = languageQueryService.findByCode(languageCode);

		var response = languageMapper.toProtoResponse(languageResponseDto);

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public void search(
		LanguageProto.SearchLanguageRequest request,
		StreamObserver<LanguageProto.LanguageListResponse> responseObserver
	) {
		var pageable = paginationMapper.toPageable(request.getPagination());
		var languagesResponseDto = languagePageService.search(request.getCodesList(), request.getNativeNamesList(), request.hasIsActive() ? request.getIsActive() : null, pageable);

		var paginationResponse = paginationMapper.toProtoPaginationResponse(languagesResponseDto);

		var response = languageMapper.toProtoListResponse(languagesResponseDto.getContent(), paginationResponse);

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public void create(
		LanguageProto.CreateLanguageRequest request,
		StreamObserver<LanguageProto.LanguageResponse> responseObserver
	) {
		var languageRequestDto = languageMapper.toLanguageRequestDto(request);
		fieldValidator.validate(languageRequestDto);
		var languageResponseDto = languageCommandService.create(languageRequestDto);
		var response = languageMapper.toProtoResponse(languageResponseDto);

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public void update(
		LanguageProto.UpdateLanguageRequest request,
		StreamObserver<LanguageProto.LanguageResponse> responseObserver
	) {
		var languageRequestDto = languageMapper.toLanguageRequestDto(request);
		fieldValidator.validate(languageRequestDto);
		var languageResponseDto = languageCommandService.update(languageRequestDto);
		var response = languageMapper.toProtoResponse(languageResponseDto);

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public void delete(
		LanguageProto.DeleteLanguageRequest request,
		StreamObserver<CommonProto.EmptyResponse> responseObserver
	) {
		String languageCode = request.getCode();
		languageCommandService.delete(languageCode);

		responseObserver.onCompleted();
	}
}
