package dev.animedia.contentservice.status.presentation.api.admin;

import dev.animedia.contentservice.status.application.dto.StatusDto;
import dev.animedia.contentservice.status.application.usecase.IndexAllStatusUseCase;
import dev.animedia.contentservice.shared.domain.pagination.Page;
import dev.animedia.contentservice.shared.domain.pagination.Pageable;
import dev.animedia.contentservice.shared.presentation.grpc.config.LanguageInterceptor;
import dev.animedia.contentservice.shared.presentation.grpc.mapper.ProtoPaginationMapper;
import dev.animedia.contentservice.status.presentation.mapper.admin.StatusAdminMapperGrpc;
import dev.animedia.contentservice.status.application.usecase.admin.CreateStatusUseCase;
import dev.animedia.contentservice.status.application.usecase.admin.DeleteStatusUseCase;
import dev.animedia.contentservice.status.application.usecase.admin.GetStatusDetailUseCase;
import dev.animedia.contentservice.status.application.usecase.admin.UpdateStatusUseCase;
import dev.animedia.grpc.common.CommonProto.EmptyResponse;
import dev.animedia.grpc.common.CommonProto.PaginationRequest;
import dev.animedia.grpc.status.admin.v1.StatusAdminProto;
import dev.animedia.grpc.status.admin.v1.StatusAdminProtoApi;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

import java.util.UUID;

@GrpcService
public class StatusAdminServiceGrpc extends dev.animedia.grpc.status.admin.v1.StatusAdminServiceGrpc.StatusAdminServiceImplBase {
	private final ProtoPaginationMapper protoPaginationMapper;
	private final StatusAdminMapperGrpc statusAdminMapperGrpc;

	private final IndexAllStatusUseCase indexAllStatusUseCase;
	private final GetStatusDetailUseCase getStatusDetailUseCase;
	private final CreateStatusUseCase createStatusUseCase;
	private final UpdateStatusUseCase updateStatusUseCase;
	private final DeleteStatusUseCase deleteStatusUseCase;

	@Autowired
	public StatusAdminServiceGrpc(
		ProtoPaginationMapper protoPaginationMapper,
		StatusAdminMapperGrpc statusAdminMapperGrpc,
		IndexAllStatusUseCase indexAllStatusUseCase,
		GetStatusDetailUseCase getStatusDetailUseCase,
		CreateStatusUseCase createStatusUseCase,
		UpdateStatusUseCase updateStatusUseCase,
		DeleteStatusUseCase deleteStatusUseCase
	) {
		this.protoPaginationMapper = protoPaginationMapper;
		this.statusAdminMapperGrpc = statusAdminMapperGrpc;
		this.indexAllStatusUseCase = indexAllStatusUseCase;
		this.getStatusDetailUseCase = getStatusDetailUseCase;
		this.createStatusUseCase = createStatusUseCase;
		this.updateStatusUseCase = updateStatusUseCase;
		this.deleteStatusUseCase = deleteStatusUseCase;
	}

	@Override
	public void getAll(
		PaginationRequest request,
		StreamObserver<StatusAdminProtoApi.GetAllStatusResponse> responseObserver
	) {
		Pageable domainPageable = protoPaginationMapper.toDomainPageable(request);
		Page<StatusDto> domainStatusDtoPage = indexAllStatusUseCase.index(domainPageable);

		var responseContent = domainStatusDtoPage.content()
			.stream()
			.map(statusAdminMapperGrpc::toStatusResponseGrpc)
			.toList();
		var responsePagination = protoPaginationMapper.toProtoPaginationResponse(domainStatusDtoPage);
		responseObserver.onNext(
			StatusAdminProtoApi.GetAllStatusResponse.newBuilder()
				.addAllStatuses(responseContent)
				.setPagination(responsePagination)
				.build()
		);
		responseObserver.onCompleted();
	}

	@Override
	public void get(
		StatusAdminProtoApi.GetStatusRequest request,
		StreamObserver<StatusAdminProto.StatusResponse> responseObserver
	) {
		StatusDto statusDto = getStatusDetailUseCase.get(
			UUID.fromString(request.getId()),
			LanguageInterceptor.getLanguageCode()
		);
		responseObserver.onNext(
			statusAdminMapperGrpc.toStatusResponseGrpc(statusDto)
		);
		responseObserver.onCompleted();
	}

	@Override
	public void create(
		StatusAdminProtoApi.CreateStatusRequest request,
		StreamObserver<StatusAdminProtoApi.CreateStatusResponse> responseObserver
	) {
		StatusDto createDto = statusAdminMapperGrpc.toStatusDto(request);
		UUID createdId = createStatusUseCase.create(createDto);
		responseObserver.onNext(
			StatusAdminProtoApi.CreateStatusResponse.newBuilder()
				.setId(createdId.toString())
				.build()
		);
		super.create(request, responseObserver);
	}

	@Override
	public void update(
		StatusAdminProtoApi.UpdateStatusRequest request,
		StreamObserver<EmptyResponse> responseObserver
	) {
		StatusDto updateDto = statusAdminMapperGrpc.toStatusDto(request);
		updateStatusUseCase.update(updateDto);
		responseObserver.onNext(
			EmptyResponse.newBuilder().build()
		);
		responseObserver.onCompleted();
	}

	@Override
	public void delete(
		StatusAdminProtoApi.DeleteStatusRequest request,
		StreamObserver<EmptyResponse> responseObserver
	) {
		deleteStatusUseCase.delete(
			UUID.fromString(request.getId())
		);
		responseObserver.onNext(
			EmptyResponse.newBuilder().build()
		);
		responseObserver.onCompleted();
	}
}
