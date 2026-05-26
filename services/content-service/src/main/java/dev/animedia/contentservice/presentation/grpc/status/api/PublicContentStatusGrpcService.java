package dev.animedia.contentservice.presentation.grpc.status.api;

import dev.animedia.contentservice.application.status.dto.StatusDto;
import dev.animedia.contentservice.application.status.dto.StatusSearchDto;
import dev.animedia.contentservice.application.status.usecase.SearchStatusUseCase;
import dev.animedia.contentservice.presentation.grpc.config.LanguageInterceptor;
import dev.animedia.contentservice.presentation.grpc.shared.mapper.PaginationMapper;
import dev.animedia.contentservice.presentation.grpc.status.mapper.StatusGrpcMapper;
import dev.animedia.grpc.status.ContentStatusCommonProto;
import dev.animedia.grpc.status.PublicContentStatusProto;
import dev.animedia.grpc.status.PublicContentStatusServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.grpc.server.service.GrpcService;

import java.util.HashSet;
import java.util.Set;

@GrpcService
public class PublicContentStatusGrpcService extends PublicContentStatusServiceGrpc.PublicContentStatusServiceImplBase {
	private final PaginationMapper paginationMapper;
	private final SearchStatusUseCase searchStatusUseCase;
	private final StatusGrpcMapper statusGrpcMapper;

	@Autowired
	public PublicContentStatusGrpcService(
		PaginationMapper paginationMapper,
		SearchStatusUseCase searchStatusUseCase,
		StatusGrpcMapper statusGrpcMapper
	) {
		this.paginationMapper = paginationMapper;
		this.searchStatusUseCase = searchStatusUseCase;
		this.statusGrpcMapper = statusGrpcMapper;
	}

	public void search(
		PublicContentStatusProto.PublicSearchRequest request,
		StreamObserver<PublicContentStatusProto.PublicSearchResponse> responseObserver
	) {
		var paginationRequest = paginationMapper.toPageable(request.getPagination());
		String languageCode = LanguageInterceptor.getLanguageCode();

		var contentStatuses = contentStatusPageService.search(languageCode, request.hasAlias() ? request.getAlias() : null, request.hasName() ? request.getName() : null, paginationRequest);
		var paginationResponse = paginationMapper.toProtoPaginationResponse(contentStatuses);

		var response = grpcContentStatusMapper.toPublicSearchResponse(contentStatuses, paginationResponse);

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public void search(
		PublicContentStatusProto.PublicSearchStatusRequest request,
		StreamObserver<ContentStatusCommonProto.SearchStatusResponse> responseObserver
	) {
		String languageCode = LanguageInterceptor.getLanguageCode();
		StatusSearchDto statusSearchDto = statusGrpcMapper.toPublicStatusSearchDto(request, languageCode);
		Pageable springPageable = paginationMapper.toPageable(request.getPagination(), Set.of("alias", "name"));
		Page<StatusDto> statusDtoPage = searchStatusUseCase.search(statusSearchDto, );

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
}
