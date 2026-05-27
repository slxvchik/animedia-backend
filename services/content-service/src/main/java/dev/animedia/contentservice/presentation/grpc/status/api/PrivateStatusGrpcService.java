package dev.animedia.contentservice.presentation.grpc.status.api;

import dev.animedia.contentservice.application.status.dto.StatusDto;
import dev.animedia.contentservice.application.status.dto.StatusSearchDto;
import dev.animedia.contentservice.application.status.usecase.*;
import dev.animedia.contentservice.domain.shared.model.Page;
import dev.animedia.contentservice.domain.shared.model.Pageable;
import dev.animedia.contentservice.presentation.grpc.shared.mapper.ProtoPaginationMapper;
import dev.animedia.contentservice.presentation.grpc.status.mapper.PrivateStatusGrpcMapper;
import dev.animedia.grpc.common.CommonProto;
import dev.animedia.grpc.status.PrivateContentStatusProto;
import dev.animedia.grpc.status.PrivateContentStatusServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

import java.util.List;
import java.util.Set;

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
		PrivateContentStatusProto.PrivateSearchStatusRequest request,
		StreamObserver<PrivateContentStatusProto.PrivateSearchStatusResponse> responseObserver
	) {
		Pageable pageableRequest = protoPaginationMapper.toDomainPageable(request.getPagination(), Set.of("alias", "name"));
		StatusSearchDto statusSearchDto = privateStatusGrpcMapper.toPrivateStatusSearchDto(request);

		Page<StatusDto> statusDtoPage = searchStatusUseCase.search(statusSearchDto, pageableRequest);

		CommonProto.PaginationResponse paginationResponse = protoPaginationMapper.toProtoPaginationResponse(statusDtoPage);
		List<PrivateContentStatusProto.PrivateStatusResponse> statusResponseList = statusDtoPage.content() != null
			? statusDtoPage.content()
				.stream()
				.map(privateStatusGrpcMapper::toPrivateStatusResponse)
				.toList()
			: List.of();

		responseObserver.onNext(
			PrivateContentStatusProto.PrivateSearchStatusResponse
				.newBuilder()
				.addAllStatuses(statusResponseList)
				.setPagination(paginationResponse)
				.build()
		);
		responseObserver.onCompleted();
	}

	@Override
	public void get(
		PrivateContentStatusProto.GetStatusRequest request,
		StreamObserver<PrivateContentStatusProto.PrivateStatusResponse> responseObserver
	) {
		StatusDto statusDto = getStatusUseCase.get(request.getId(), null, null);
		responseObserver.onNext(
			privateStatusGrpcMapper.toPrivateStatusResponse(statusDto)
		);
		responseObserver.onCompleted();
	}

	@Override
	public void create(
		PrivateContentStatusProto.CreateStatusRequest request,
		StreamObserver<PrivateContentStatusProto.PrivateStatusResponse> responseObserver
	) {
		createStatusUseCase.create();
		super.create(request, responseObserver);
	}

	@Override
	public void update(
		PrivateContentStatusProto.UpdateStatusRequest request,
		StreamObserver<PrivateContentStatusProto.PrivateStatusResponse> responseObserver
	) {
		super.update(request, responseObserver);
	}

	@Override
	public void delete(
		PrivateContentStatusProto.DeleteStatusRequest request,
		StreamObserver<CommonProto.EmptyResponse> responseObserver
	) {
		super.delete(request, responseObserver);
	}
}
