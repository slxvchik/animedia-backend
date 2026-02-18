package dev.animedia.languageservice.language;

import dev.animedia.grpc.common.CommonProto;
import dev.animedia.grpc.language.LanguageProto;
import dev.animedia.grpc.language.LanguageServiceGrpc;
import dev.animedia.languageservice.language.service.LanguageCommandService;
import dev.animedia.languageservice.language.service.LanguagePageService;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class GrpcLanguageService extends LanguageServiceGrpc.LanguageServiceImplBase {

	private final LanguagePageService languagePageService;
	private final LanguageCommandService languageCommandService;

	@Autowired
	public GrpcLanguageService(LanguagePageService languagePageService,
		LanguageCommandService languageCommandService
	) {
		this.languagePageService = languagePageService;
		this.languageCommandService = languageCommandService;
	}

	@Override
	public void get(CommonProto.AppRequest request, StreamObserver<CommonProto.AppResponse> responseObserver) {
		String languageCode = request.getLanguageCode();
		if (request.getData().is(LanguageProto.GetLanguageRequest.class)) {

		} else {
			CommonProto.AppResponse response = CommonProto.AppResponse.newBuilder()
				.setSuccess(false)
				.addErrors("")
				.build();
			var statusRuntimeException = Status.INVALID_ARGUMENT.withDescription("").asException();
			responseObserver.onError(statusRuntimeException);
		}
	}

	@Override
	public void search(CommonProto.AppRequest request, StreamObserver<CommonProto.AppResponse> responseObserver) {
		super.search(request, responseObserver);
	}

	@Override
	public void create(CommonProto.AppRequest request, StreamObserver<CommonProto.AppResponse> responseObserver) {
		super.create(request, responseObserver);
	}

	@Override
	public void update(CommonProto.AppRequest request, StreamObserver<CommonProto.AppResponse> responseObserver) {
		super.update(request, responseObserver);
	}

	@Override
	public void delete(CommonProto.AppRequest request, StreamObserver<CommonProto.AppResponse> responseObserver) {
		super.delete(request, responseObserver);
	}


//	public ResponseEntity<AppResponseDto<PagedResponse<LanguageResponseDto>>> search(
//		@PageableDefault
//		Pageable pageable,
//		@RequestParam(required = false)
//		@Size(max = 100, message = AppExceptionConstants.BATCH_SIZE_LIMIT_MESSAGE)
//		List<String> languagesCodes,
//		@RequestParam(required = false)
//		@Size(max = 100, message = AppExceptionConstants.BATCH_SIZE_LIMIT_MESSAGE)
//		List<String> names
//	) {
//		var appR = CommonProto.AppResponse.newBuilder();
//
//		Page<LanguageResponseDto> languagesResponseDto = languagePageService.search(languagesCodes, names, pageable);
//		return ResponseEntity.ok(
//			AppResponseDto.success(
//				PagedResponse.getPagedResponse(languagesResponseDto)
//			)
//		);
//	}
//
//	@PostMapping
//	public ResponseEntity<AppResponseDto<ContentResponse<LanguageResponseDto>>> create(
//		@Validated
//		LanguageRequestDto languageRequestDto
//	) {
//		var createdGenre = languageCommandService.create(languageRequestDto);
//		return ResponseEntity.ok(
//			AppResponseDto.success(
//				ContentResponse.content(createdGenre)
//			)
//		);
//	}
//
//	@PutMapping
//	public ResponseEntity<AppResponseDto<ContentResponse<LanguageResponseDto>>> update(
//		@Validated
//		LanguageRequestDto languageRequestDto
//	) {
//		var updatedGenre = languageCommandService.update(languageRequestDto);
//		return ResponseEntity.ok(
//			AppResponseDto.success(
//				ContentResponse.content(updatedGenre)
//			)
//		);
//	}
//
//	@DeleteMapping("/{id}")
//	public ResponseEntity<AppResponseDto<ContentResponse<LanguageResponseDto>>> delete(
//		@PathVariable
//		String id
//	) {
//		languageCommandService.delete(id);
//		return ResponseEntity.ok(
//			AppResponseDto.success(
//				ContentResponse.content()
//			)
//		);
//	}
}
