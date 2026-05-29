package dev.animedia.contentservice.presentation.grpc.status.api;

import dev.animedia.contentservice.application.status.dto.StatusDto;
import dev.animedia.contentservice.application.status.dto.StatusSearchDto;
import dev.animedia.contentservice.application.status.usecase.*;
import dev.animedia.contentservice.domain.shared.pagination.Page;
import dev.animedia.contentservice.domain.shared.pagination.Pageable;
import dev.animedia.contentservice.presentation.grpc.shared.mapper.ProtoPaginationMapper;
import dev.animedia.contentservice.presentation.grpc.status.mapper.PrivateStatusGrpcMapper;
import dev.animedia.grpc.common.CommonProto;
import dev.animedia.grpc.common.CommonProto.*;
import dev.animedia.grpc.status.PrivateContentStatusProto.*;
import dev.animedia.grpc.status.PrivateContentStatusServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@GrpcService
public class PrivateStatusGrpcService extends PrivateContentStatusServiceGrpc.PrivateContentStatusServiceImplBase {
	private final ProtoPaginationMapper protoPaginationMapper;
	private final PrivateStatusGrpcMapper privateStatusGrpcMapper;

	private final SearchStatusUseCase searchStatusUseCase;
	private final GetStatusUseCase getStatusUseCase;
	private final CreateStatusUseCase createStatusUseCase;
	private final UpdateStatusUseCase updateStatusUseCase;
	private final DeleteStatusUseCase deleteStatusUseCase;

	@Autowired
	public PrivateStatusGrpcService(
		ProtoPaginationMapper protoPaginationMapper,
		PrivateStatusGrpcMapper privateStatusGrpcMapper,
		SearchStatusUseCase searchStatusUseCase,
		GetStatusUseCase getStatusUseCase,
		CreateStatusUseCase createStatusUseCase,
		UpdateStatusUseCase updateStatusUseCase,
		DeleteStatusUseCase deleteStatusUseCase
	) {
		this.protoPaginationMapper = protoPaginationMapper;
		this.privateStatusGrpcMapper = privateStatusGrpcMapper;
		this.searchStatusUseCase = searchStatusUseCase;
		this.getStatusUseCase = getStatusUseCase;
		this.createStatusUseCase = createStatusUseCase;
		this.updateStatusUseCase = updateStatusUseCase;
		this.deleteStatusUseCase = deleteStatusUseCase;
	}

	@Override
	public void search(
		PrivateSearchStatusRequest request,
		StreamObserver<PrivateSearchStatusResponse> responseObserver
	) {
		Pageable pageableRequest = protoPaginationMapper.toDomainPageable(
			request.getPagination(),
			Set.of("alias", "sortOrder", "active", "translations.name", "translations.languageCode")
		);
		StatusSearchDto statusSearchDto = privateStatusGrpcMapper.toPrivateStatusSearchDto(request);

		Page<StatusDto> statusDtoPage = searchStatusUseCase.search(statusSearchDto, pageableRequest);

		PaginationResponse paginationResponse = protoPaginationMapper.toProtoPaginationResponse(statusDtoPage);
		List<PrivateStatusResponse> statusResponseList = statusDtoPage.content() != null
			? statusDtoPage.content()
				.stream()
				.map(privateStatusGrpcMapper::toPrivateStatusResponse)
				.toList()
			: List.of();

		responseObserver.onNext(
			privateStatusGrpcMapper.toPrivateSearchStatusResponse(statusResponseList, paginationResponse)
		);
		responseObserver.onCompleted();
	}

	@Override
	public void get(
		GetStatusRequest request,
		StreamObserver<PrivateStatusResponse> responseObserver
	) {
		StatusDto statusDto = getStatusUseCase.get(UUID.fromString(request.getUuid()), null, null);
		responseObserver.onNext(
			privateStatusGrpcMapper.toPrivateStatusResponse(statusDto)
		);
		responseObserver.onCompleted();
	}

	@Override
	public void create(
		CreateStatusRequest request,
		StreamObserver<PrivateStatusResponse> responseObserver
	) {
		StatusDto statusDto = privateStatusGrpcMapper.toStatusDto(request);
		StatusDto created = createStatusUseCase.create(statusDto);

		responseObserver.onNext(
			privateStatusGrpcMapper.toPrivateStatusResponse(created)
		);
		responseObserver.onCompleted();
	}

	@Override
	public void update(
		UpdateStatusRequest request,
		StreamObserver<PrivateStatusResponse> responseObserver
	) {
		StatusDto statusDto = privateStatusGrpcMapper.toStatusDto(request);
		StatusDto updated = updateStatusUseCase.update(statusDto);

		responseObserver.onNext(
			privateStatusGrpcMapper.toPrivateStatusResponse(updated)
		);
		responseObserver.onCompleted();
	}

	@Override
	public void delete(
		DeleteStatusRequest request,
		StreamObserver<EmptyResponse> responseObserver
	) {
		deleteStatusUseCase.delete(UUID.fromString(request.getUuid()));
		responseObserver.onNext(
			CommonProto.EmptyResponse.newBuilder().build()
		);
		responseObserver.onCompleted();
	}
}
