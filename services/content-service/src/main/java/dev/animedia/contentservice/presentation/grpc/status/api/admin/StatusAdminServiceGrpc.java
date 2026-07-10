package dev.animedia.contentservice.presentation.grpc.status.api.admin;

import dev.animedia.contentservice.application.status.dto.StatusDto;
import dev.animedia.contentservice.application.status.dto.StatusSearchDto;
import dev.animedia.contentservice.application.status.usecase.admin.*;
import dev.animedia.contentservice.domain.shared.pagination.Page;
import dev.animedia.contentservice.domain.shared.pagination.Pageable;
import dev.animedia.contentservice.presentation.grpc.shared.mapper.ProtoPaginationMapper;
import dev.animedia.contentservice.presentation.grpc.status.mapper.StatusAdminMapperGrpc;
import dev.animedia.grpc.common.CommonProto;
import dev.animedia.grpc.common.CommonProto.EmptyResponse;
import dev.animedia.grpc.common.CommonProto.PaginationRequest;
import dev.animedia.grpc.common.CommonProto.PaginationResponse;
import dev.animedia.grpc.status.PrivateContentStatusProto.*;
import dev.animedia.grpc.status.admin.v1.StatusAdminProto;
import dev.animedia.grpc.status.admin.v1.StatusAdminProtoApi;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@GrpcService
public class StatusAdminServiceGrpc extends dev.animedia.grpc.status.admin.v1.StatusAdminServiceGrpc.StatusAdminServiceImplBase {
	private final ProtoPaginationMapper protoPaginationMapper;
	private final StatusAdminMapperGrpc statusAdminMapperGrpc;

	private final GetAllStatusUseCase getAllStatusUseCase;
	private final GetStatusUseCase getStatusUseCase;
	private final CreateStatusUseCase createStatusUseCase;
	private final UpdateStatusUseCase updateStatusUseCase;
	private final DeleteStatusUseCase deleteStatusUseCase;

	@Autowired
	public StatusAdminServiceGrpc(
		ProtoPaginationMapper protoPaginationMapper,
		StatusAdminMapperGrpc statusAdminMapperGrpc,
		GetAllStatusUseCase getAllStatusUseCase,
		GetStatusUseCase getStatusUseCase,
		CreateStatusUseCase createStatusUseCase,
		UpdateStatusUseCase updateStatusUseCase,
		DeleteStatusUseCase deleteStatusUseCase
	) {
		this.protoPaginationMapper = protoPaginationMapper;
		this.statusAdminMapperGrpc = statusAdminMapperGrpc;
		this.getAllStatusUseCase = getAllStatusUseCase;
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
		StatusSearchDto statusSearchDto = statusAdminMapperGrpc.toPrivateStatusSearchDto(request);

		Page<StatusDto> statusDtoPage = searchStatusUseCase.search(statusSearchDto, pageableRequest);

		PaginationResponse paginationResponse = protoPaginationMapper.toProtoPaginationResponse(statusDtoPage);
		List<PrivateStatusResponse> statusResponseList = statusDtoPage.content() != null
			? statusDtoPage.content()
				.stream()
				.map(statusAdminMapperGrpc::toPrivateStatusResponse)
				.toList()
			: List.of();

		responseObserver.onNext(
			statusAdminMapperGrpc.toPrivateSearchStatusResponse(statusResponseList, paginationResponse)
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
			statusAdminMapperGrpc.toPrivateStatusResponse(statusDto)
		);
		responseObserver.onCompleted();
	}

	@Override
	public void create(
		CreateStatusRequest request,
		StreamObserver<PrivateStatusResponse> responseObserver
	) {
		StatusDto statusDto = statusAdminMapperGrpc.toStatusDto(request);
		StatusDto created = createStatusUseCase.create(statusDto);

		responseObserver.onNext(
			statusAdminMapperGrpc.toPrivateStatusResponse(created)
		);
		responseObserver.onCompleted();
	}

	@Override
	public void update(
		UpdateStatusRequest request,
		StreamObserver<PrivateStatusResponse> responseObserver
	) {
		StatusDto statusDto = statusAdminMapperGrpc.toStatusDto(request);
		StatusDto updated = updateStatusUseCase.update(statusDto);

		responseObserver.onNext(
			statusAdminMapperGrpc.toPrivateStatusResponse(updated)
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

	@Override
	public void getAll(
		PaginationRequest request,
		StreamObserver<StatusAdminProtoApi.GetAllStatusResponse> responseObserver
	) {
		Pageable domainPageable = protoPaginationMapper.toDomainPageable(request);
		Page<StatusDto> domainStatusPage = getAllStatusUseCase.get(domainPageable);

		var responseContent
		var responsePagination = protoPaginationMapper.toProtoPaginationResponse(domainStatusPage);
		responseObserver.onNext(
			statusAdminMapperGrpc.
		);
		super.getAll(request, responseObserver);
	}

	@Override
	public void get(
		StatusAdminProtoApi.GetStatusRequest request,
		StreamObserver<StatusAdminProto.StatusResponse> responseObserver
	) {
		super.get(request, responseObserver);
	}

	@Override
	public void create(
		StatusAdminProtoApi.CreateStatusRequest request,
		StreamObserver<StatusAdminProto.StatusResponse> responseObserver
	) {
		super.create(request, responseObserver);
	}

	@Override
	public void update(
		StatusAdminProtoApi.UpdateStatusRequest request,
		StreamObserver<StatusAdminProto.StatusResponse> responseObserver
	) {
		super.update(request, responseObserver);
	}

	@Override
	public void delete(
		StatusAdminProtoApi.DeleteStatusRequest request,
		StreamObserver<EmptyResponse> responseObserver
	) {
		super.delete(request, responseObserver);
	}
}
