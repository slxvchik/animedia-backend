package dev.animedia.contentservice.presentation.grpc.status.api;

import dev.animedia.contentservice.presentation.grpc.shared.mapper.PaginationMapper;
import dev.animedia.grpc.common.CommonProto;
import dev.animedia.grpc.status.ContentStatusCommonProto;
import dev.animedia.grpc.status.PrivateContentStatusProto;
import dev.animedia.grpc.status.PrivateContentStatusServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class PrivateContentStatusGrpcService extends PrivateContentStatusServiceGrpc.PrivateContentStatusServiceImplBase {
	private final PaginationMapper paginationMapper;

	@Autowired
	public PrivateContentStatusGrpcService(
		PaginationMapper paginationMapper
	) {
		this.paginationMapper = paginationMapper;
	}

	@Override
	public void search(
		PrivateContentStatusProto.PrivateSearchStatusRequest request,
		StreamObserver<ContentStatusCommonProto.SearchStatusResponse> responseObserver
	) {
		super.search(request, responseObserver);
	}

	@Override
	public void create(
		PrivateContentStatusProto.CreateStatusRequest request,
		StreamObserver<ContentStatusCommonProto.StatusResponse> responseObserver
	) {
		super.create(request, responseObserver);
	}

	@Override
	public void update(
		PrivateContentStatusProto.UpdateStatusRequest request,
		StreamObserver<ContentStatusCommonProto.StatusResponse> responseObserver
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
